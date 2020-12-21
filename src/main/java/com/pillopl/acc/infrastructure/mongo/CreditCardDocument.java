package com.pillopl.acc.infrastructure.mongo;

import com.pillopl.acc.domain.VirtualCreditCard;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

class CreditCardDocument {

    @Id  private String refNo;
    private BigDecimal limit;
    private BigDecimal usedLimit = BigDecimal.ZERO;
    private int withdrawals;

    CreditCardDocument(String refNo) {
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
