package com.pillopl.acc.domain;

import java.time.Instant;

public class CycleClosed implements DomainEvent {

    private final String cardRef;
    private final Instant timestamp;

    public CycleClosed(String cardRef, Instant timestamp) {
        this.cardRef = cardRef;
        this.timestamp = timestamp;
    }

    public String getCardNo() {
        return cardRef;
    }

    public Instant getTimestamp() {
        return timestamp;
    }


    @Override
    public String getType() {
        return "cycle-closed";
    }
}
