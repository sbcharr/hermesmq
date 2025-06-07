package com.github.sbcharr.broker.core;

import com.github.sbcharr.broker.api.Topic;

import java.util.List;

public class InMemoryBroker {
    private static boolean isActive = false;
    private TopicManager topicManager;
    // singleton InMemoryBroker
    private static volatile InMemoryBroker INSTANCE;

    private InMemoryBroker() {}

    public static InMemoryBroker getInstance() {
        if (INSTANCE == null) {
            synchronized (TopicManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new InMemoryBroker();
                }
            }
        }

        return INSTANCE;
    }


    // TODO: Topic management
    public Topic createTopic(Topic topic) {
        return topicManager.createTopic(topic);
    }

    // List topics
    public List<Topic> listTopics() {
        // Return a list of all topics managed by the broker
        // This should return a copy of the internal topic list to avoid external modifications

        return topicManager.getAllTopics();
    }

    public void deleteTopic(String topicName) {
        // Delete the topic by name
        // This should remove the topic from the topic manager and clean up any associated resources

        // topicManager.deleteTopic(topicName);
    }

    // Broker management


    public void start() {
        this.topicManager = new TopicManager();
        isActive = true;
        while (isActive) {
            // Main loop for the broker
            // Handle incoming requests, process messages, etc.
        }
    }

    public void stop() {
        // Stop the broker
        isActive = false;
    }



    // TODO: Producer API

    public void registerProducer(String topicName) {
        // Register a producer for the specified topic, meaning keep a track of registered producers in-memory
        // Validate topic existence, etc.
    }

    // TODO: Consumer API

    // TODO: Message storage management

    // TODO: Consumer-group offset management
}
