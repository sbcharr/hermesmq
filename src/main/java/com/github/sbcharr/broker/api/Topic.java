package com.github.sbcharr.broker.api;

import com.github.sbcharr.broker.core.Partition;
import com.github.sbcharr.common.BaseCommon;

import java.util.Objects;

public class Topic extends BaseCommon {
    private final String name;
    private final String description;
    private final int partitionCount;
    private final Partition[] partitions;

    public Topic(String name, String description, int partitionCount) {
        this.name = name;
        this.description = description;
        this.partitionCount = partitionCount;
        this.partitions = new Partition[partitionCount];

        for (int i = 0; i < partitionCount; i++) {
            partitions[i] = new Partition(); // Initialize each partition
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPartitionCount() {
        return partitionCount;
    }

    public Partition getPartition(int index) {
        if (index < 0 || index >= partitionCount) {
            return null; // Invalid partition index
        }

        return partitions[index];
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return partitionCount == topic.partitionCount && Objects.equals(name, topic.name) && Objects.equals(description, topic.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, partitionCount);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", partitionCount=" + partitionCount + '}';
    }
}