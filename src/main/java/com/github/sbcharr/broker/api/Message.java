package com.github.sbcharr.broker.api;

import com.github.sbcharr.common.BaseCommon;

import java.util.Objects;

public class Message extends BaseCommon {
    private final int key;
    private final String value;

    public Message(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return key == message.key && Objects.equals(value, message.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "Message{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
