package com.github.sbcharr.broker.core;

import com.github.sbcharr.broker.api.Message;
import com.github.sbcharr.broker.api.Topic;
import com.github.sbcharr.util.Util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TopicManager {
    private static volatile TopicManager INSTANCE;
    private final Map<String, Topic> topics;

    private TopicManager() {
        this.topics = new ConcurrentHashMap<>();
    }

    public static TopicManager getInstance() {
        if (INSTANCE == null) {
            synchronized (TopicManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TopicManager();
                }
            }
        }

        return INSTANCE;
    }

    Topic createTopic(Topic topic) {
        if (topic == null || Util.isNullOrEmpty(topic.getName())) {
            return null; // Invalid topic
        }
        // topic name is case-insensitive. TODO: consider regex on topic name to ensure it meets certain criteria
        return topics.putIfAbsent(topic.getName().toLowerCase(), topic);
    }

    // TODO: delete topic

    MessageNode addMessageToTopic(String topicName, Message message) {
        if (Util.isNullOrEmpty(topicName)) {
            return null; // Invalid topic name
        }

        if (message == null) {
            return null; // Invalid message
        }

        Topic topic = topics.get(topicName.toLowerCase());
        if (topic == null) {
            return null; // Topic does not exist
        }

        int partitionIndex = (message.getKey() % topic.getPartitionCount() + topic.getPartitionCount()) % topic.getPartitionCount(); // Ensure partition index is non-negative and within bounds
        Partition topicPartition = topic.getPartition(partitionIndex);

        return topicPartition.append(message);
    }


}
