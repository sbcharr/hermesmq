package com.github.sbcharr.common;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class BaseCommon implements Serializable {

    private static final long serialVersionUID = 1L;
    private final UUID id;
    private final Instant createdAt;

    public BaseCommon() {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
