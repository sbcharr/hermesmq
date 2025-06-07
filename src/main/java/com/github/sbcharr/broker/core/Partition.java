package com.github.sbcharr.broker.core;

import com.github.sbcharr.broker.api.Message;
import com.github.sbcharr.common.BaseCommon;

import javax.annotation.Nullable;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;


public class Partition extends BaseCommon {
    private final Deque<MessageNode> messageNodes; // A partition data structure
    private final Map<Long, MessageNode> nodeMap; // In a partition, it keeps track of message nodes by their offsets
    private final AtomicInteger nextOffset = new AtomicInteger(0); // Used to generate the next offset for new messages

    public Partition() {
        this.messageNodes = new ConcurrentLinkedDeque<>();
        this.nodeMap = new ConcurrentHashMap<>();
    }

    public MessageNode append(Message message) {
        long offset = nextOffset.getAndIncrement();
        MessageNode messageNode = new MessageNode(message, offset);

        boolean isElementAdded = messageNodes.add(messageNode);
        if (isElementAdded) {
            nodeMap.put(messageNode.getOffset(), messageNode);
        }

        return messageNode;
    }

    public MessageNode getLatest() {
        return messageNodes.peekLast();
    }

    public void removeOldest() {
        MessageNode removedNode = messageNodes.pollFirst();
        if (removedNode != null) {
            nodeMap.remove(removedNode.getOffset());
        }
    }

    public boolean isEmpty() {
        return messageNodes.isEmpty();
    }

    public int size() {
        return messageNodes.size();
    }

    public @Nullable MessageNode getByOffset(long offset) {
        return nodeMap.get(offset);
    }

    public long getLatestOffset() {
        return messageNodes.peekLast() == null ? 0L : messageNodes.peekLast().getOffset(); // Return the next offset for new messages
    }
}
