package com.pillopl.acc.domain

import spock.lang.Specification

import java.time.Instant

class VirtualCreditCardTest extends Specification {

    VirtualCreditCard card = new VirtualCreditCard("refNo")

    def 'can assign limit'() {
        when:
            card.assignLimit(50)
        then:
            card.availableLimit() == 50
    }

    def 'can withdraw'() {
        given:
            card.assignLimit(100)
        when:
            card.withdraw(50)
        then:
            card.availableLimit() == 50
    }

    def 'cannot withdraw when not enough money'() {
        given:
            card.assignLimit(100)
        when:
            card.withdraw(501)
        then:
            thrown(IllegalStateException)
    }

    def 'cannot withdraw when too many withdrawals in cycle'() {
        given:
            card.assignLimit(100)
        and:
            45.times {card.withdraw(1)}
        when:
            card.withdraw(1)
        then:
            thrown(IllegalStateException)
    }

    def 'can repay'() {
        given:
            card.assignLimit(100)
        and:
            card.withdraw(10)
        when:
            card.repay(5)
        then:
            card.availableLimit() == 95
    }

    def 'can withdraw in next cycle'() {
        given:
            card.assignLimit(100)
        and:
            45.times {card.withdraw(1)}
        and:
            card.cycleClosed()
        when:
            card.withdraw(1)
        then:
            card.availableLimit() == 54
    }


    def 'can recreate'() {
        given:
            LimitAssigned limitAssigned =
                    new LimitAssigned("ref", 100.00, Instant.now())
        and:
            CardWithdrawn cardWithdrawn =
                    new CardWithdrawn("ref", 10.00, Instant.now())
        and:
            CardRepaid cardRepaid =
                    new CardRepaid("ref", 5.00, Instant.now())
        when:
            VirtualCreditCard card =
                    VirtualCreditCard.recreate("ref", [limitAssigned, cardWithdrawn, cardRepaid])
        then:
            card.availableLimit() == 95.00
    }

    def 'can recreate entire cycle'() {
        given:
        LimitAssigned limitAssigned =
                new LimitAssigned("ref", 100.00, Instant.now())
        and:
        CardWithdrawn cardWithdrawn =
                new CardWithdrawn("ref", 10.00, Instant.now())
        and:
        CardRepaid cardRepaid =
                new CardRepaid("ref", 5.00, Instant.now())
        and:
        CycleClosed cycleClosed =
                new CycleClosed("ref", Instant.now())
        and:
        CardWithdrawn cardWithdrawn2 =
                new CardWithdrawn("ref", 10.00, Instant.now())
        when:
        VirtualCreditCard card =
                VirtualCreditCard.recreate("ref", [limitAssigned, cardWithdrawn, cardRepaid, cardWithdrawn2, cycleClosed])
        then:
        card.availableLimit() == 85.00
        and:
        card.getPendingEvents().size() == 0
    }

    //TODO : add 2nd test with different events
}











