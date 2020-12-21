package com.pillopl.acc.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class VirtualCreditCard {

    private final String refNo;
    private Limit limit;
    private int withdrawals;

    private List<DomainEvent> pendingEvents = new ArrayList<>();

    public VirtualCreditCard(String ref) {
        this.refNo = ref;
    }

    public VirtualCreditCard(String refNo, BigDecimal limit, BigDecimal usedLimit, int withdrawals) {
        this.refNo = refNo;
        this.limit = Limit.withUsed(limit, usedLimit);
        this.withdrawals = withdrawals;
    }


    public void assignLimit(BigDecimal amount) { //niebieskie
        limitAssigned(new LimitAssigned(refNo, amount, Instant.now()));
    }

    private VirtualCreditCard limitAssigned(LimitAssigned event) {
        this.limit = Limit.of(event.getAmount()); //pomaraczona
        pendingEvents.add(event);
        return this;
    }


    public void withdraw(BigDecimal amount) {
        if (notEnoughMoneyToWithdraw(amount)) { //zolte
            throw new IllegalStateException();
        }
        if (tooManyWithdrawalsInCycle()) {
            throw new IllegalStateException();
        }
        cardWithdrawn(new CardWithdrawn(refNo, amount, Instant.now()));

    }

    private VirtualCreditCard cardWithdrawn(CardWithdrawn event) {
        this.limit = limit.subtract(event.getAmount());
        this.withdrawals++;
        pendingEvents.add(event);
        return this;
    }

    private boolean tooManyWithdrawalsInCycle() {
        return withdrawals >= 45;
    }

    private boolean notEnoughMoneyToWithdraw(BigDecimal amount) {
        return availableLimit().compareTo(amount) < 0;
    }

    public BigDecimal availableLimit() {
        return limit.available();
    }

    public void repay(BigDecimal amount) {
        cardRepaid(new CardRepaid(refNo, amount, Instant.now()));
    }

    private VirtualCreditCard cardRepaid(CardRepaid event) {
        this.limit = limit.add(event.getAmount());
        pendingEvents.add(event);
        return this;
    }

    public void cycleClosed() {
        billingCycleClosed(new CycleClosed(refNo, Instant.now()));
    }

    private VirtualCreditCard billingCycleClosed(CycleClosed event) {
        this.withdrawals = 0;
        pendingEvents.add(event);
        return this;

    }

    public List<DomainEvent> getPendingEvents() {
        return pendingEvents;
    }

    public void flushEvents() {
        pendingEvents.clear();
    }

    public static VirtualCreditCard recreate(String refNo, List<DomainEvent> events) {
        VirtualCreditCard vcc = new VirtualCreditCard(refNo);
        events.forEach( evt -> {
            switch (evt.getType()){
                case "card-withdrawn":
                    vcc.cardWithdrawn((CardWithdrawn) evt);
                    break;
                case "limit-assigned":
                    vcc.limitAssigned((LimitAssigned) evt);
                    break;
                case "card-repaid":
                    vcc.cardRepaid((CardRepaid) evt);
                    break;
                case "cycle-closed":
                    vcc.billingCycleClosed((CycleClosed) evt);
                    break;
            }
        });
        vcc.flushEvents();
        return vcc;
    }



}




