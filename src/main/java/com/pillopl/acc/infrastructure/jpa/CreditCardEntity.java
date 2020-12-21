package com.pillopl.acc.infrastructure.jpa;

import com.pillopl.acc.domain.VirtualCreditCard;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@NoArgsConstructor
@Entity
class CreditCardEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String refNo;
    private BigDecimal limit;
    private BigDecimal usedLimit = BigDecimal.ZERO;
    private int withdrawals;

    CreditCardEntity(String refNo) {
        this.refNo = refNo;
    }

    VirtualCreditCard translate() {
        return new VirtualCreditCard(refNo, limit, usedLimit, withdrawals);
    }

    void mutateTo(VirtualCreditCard card) {
        limit = card.getLimit().getLimit();
        usedLimit = card.getLimit().getUsedLimit();
        withdrawals = card.getWithdrawals();
    }
}
