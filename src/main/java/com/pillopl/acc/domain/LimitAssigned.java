package com.pillopl.acc.domain;

import java.math.BigDecimal;
import java.time.Instant;

public class LimitAssigned implements DomainEvent {

    private final String cardRef;
    private final BigDecimal amount;
    private final Instant timestamp;

    public LimitAssigned(String cardRef, BigDecimal amount, Instant timestamp) {
        this.cardRef = cardRef;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getCardNo() {
        return cardRef;
    }

    @Override
    public String getType() {
        return "limit-assigned";
    }
}
