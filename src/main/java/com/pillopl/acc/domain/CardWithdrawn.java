package com.pillopl.acc.domain;

import java.math.BigDecimal;
import java.time.Instant;

public class CardWithdrawn implements DomainEvent {

    private final String cardRef;
    private final BigDecimal amount;
    private final Instant timestamp;

    public CardWithdrawn(String cardRef, BigDecimal amount, Instant timestamp) {
        this.cardRef = cardRef;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getCardNo() {
        return cardRef;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }


    @Override
    public String getType() {
        return "card-withdrawn";
    }
}
