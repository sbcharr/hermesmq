package com.github.sbcharr.broker.core;

import com.github.sbcharr.broker.api.Message;
import com.github.sbcharr.broker.api.Topic;
import com.github.sbcharr.util.Util;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TopicManager {
    private final Map<String, Topic> topics;

    TopicManager() {
        this.topics = new ConcurrentHashMap<>();
    }

    Topic createTopic(Topic topic) {
        if (topic == null || Util.isNullOrEmpty(topic.getName())) {
            return null; // Invalid topic
        }
        // topic name is case-insensitive. TODO: consider regex on topic name to ensure it meets certain criteria
        return topics.putIfAbsent(topic.getName().toLowerCase(), topic);
    }

    @Nullable List<Topic> getAllTopics() {
        if (topics.isEmpty()) {
            return null; // No topics available
        }

        return new ArrayList<>(topics.values());

    }

    void deleteTopic() {}

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
