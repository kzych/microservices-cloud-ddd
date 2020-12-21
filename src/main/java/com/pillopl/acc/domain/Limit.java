package com.pillopl.acc.domain;


import lombok.Value;

import java.math.BigDecimal;

@Value
public class Limit {

    BigDecimal usedLimit;
    BigDecimal limit;


    static Limit of(BigDecimal limit) {
        return new Limit(limit);
    }

    static Limit withUsed(BigDecimal limit, BigDecimal usedLimit) {
        return new Limit(limit, usedLimit);
    }

    private Limit(BigDecimal limit) {
        this.limit = limit;
        this.usedLimit = BigDecimal.ZERO;
    }

    private Limit(BigDecimal limit, BigDecimal usedLimit) {
        this.limit = limit;
        this.usedLimit = usedLimit;
    }

    Limit add(BigDecimal amount) {
        return new Limit(limit, usedLimit.subtract(amount));
    }

    BigDecimal available() {
        return limit.subtract(usedLimit);
    }

    Limit subtract(BigDecimal amount) {
        return Limit.withUsed(limit, usedLimit.add(amount));
    }


}
