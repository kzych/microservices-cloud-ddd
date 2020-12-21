package com.pillopl.acc.domain;

public interface CreditCardRepository {

    VirtualCreditCard findById(String refNo);

    void save(VirtualCreditCard card);

}
