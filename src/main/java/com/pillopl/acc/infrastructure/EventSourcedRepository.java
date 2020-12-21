package com.pillopl.acc.infrastructure;

import com.pillopl.acc.domain.CreditCardRepository;
import com.pillopl.acc.domain.DomainEvent;
import com.pillopl.acc.domain.VirtualCreditCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventSourcedRepository implements CreditCardRepository {

    private final Map<String, List<DomainEvent>>
            eventStreams = new HashMap<>();

    @Override
    public void save(VirtualCreditCard card) {
        List<DomainEvent> currentStream = eventStreams
                .getOrDefault(card.getRefNo(), new ArrayList<>());
        currentStream.addAll(card.getPendingEvents());
        eventStreams.put(card.getRefNo(), currentStream);
        card.flushEvents();
    }


    @Override
    public VirtualCreditCard findById(String refNo) {
        return VirtualCreditCard.recreate(refNo,
                eventStreams.getOrDefault(refNo, new ArrayList<>()));
    }


}
