package com.github.sbcharr.broker.core;

import com.github.sbcharr.broker.api.Message;
import com.github.sbcharr.common.BaseCommon;

import java.util.Objects;

public class MessageNode extends BaseCommon {
    private final Message message;
    private final long offset;

    public MessageNode(Message message, long offset) {
        this.message = message;
        this.offset = offset;
    }

    public Message getMessage() {
        return message;
    }

    public long getOffset() {
        return offset;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MessageNode that = (MessageNode) o;
        return offset == that.offset && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, offset);
    }

    @Override
    public String toString() {
        return "MessageNode{" +
                "message=" + message +
                ", offset=" + offset +
                '}';
    }
}
