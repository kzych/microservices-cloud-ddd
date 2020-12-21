package com.pillopl.acc.infrastructure.mongo;

import com.pillopl.acc.domain.VirtualCreditCard;
import com.pillopl.acc.domain.CreditCardRepository;

//@Repository
//@Primary
public class MongoAdapter implements CreditCardRepository {

    private final CreditCardMongoRepository creditCardMongoRepository;

    public MongoAdapter(CreditCardMongoRepository creditCardMongoRepository) {
        this.creditCardMongoRepository = creditCardMongoRepository;
    }

    @Override
    public VirtualCreditCard findById(String refNo) {
        return creditCardMongoRepository
                .findByRefNo(refNo)
                .orElseThrow(IllegalStateException::new)
                .translate();
    }

    @Override
    public void save(VirtualCreditCard card) {
//        CreditCardDocument cardDoc = creditCardMongoRepository
//                .findByRefNo(card.getRefNo()).orElseGet(() ->
//                creditCardMongoRepository
//                        .save(new CreditCardDocument(card.getRefNo())));
//        cardDoc.mutateTo(card);
//        creditCardMongoRepository.save(cardDoc);
    }
}
