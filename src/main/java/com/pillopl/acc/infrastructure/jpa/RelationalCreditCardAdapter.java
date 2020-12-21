package com.pillopl.acc.infrastructure.jpa;

import com.pillopl.acc.domain.VirtualCreditCard;
import com.pillopl.acc.domain.CreditCardRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Primary
public class RelationalCreditCardAdapter implements CreditCardRepository {

    private final CreditCardJpaRepository creditCardJpaRepository;

    public RelationalCreditCardAdapter(CreditCardJpaRepository creditCardJpaRepository) {
        this.creditCardJpaRepository = creditCardJpaRepository;
    }

    @Override
    public VirtualCreditCard findById(String refNo) {
       return creditCardJpaRepository
               .findByRefNo(refNo)
               .orElseThrow(IllegalStateException::new)
               .translate();
    }

    @Override
    @Transactional
    public void save(VirtualCreditCard card) {
        CreditCardEntity entity = creditCardJpaRepository.findByRefNo(card.getRefNo())
                .orElseGet(() -> creditCardJpaRepository.save(new CreditCardEntity(card.getRefNo())));
        entity.mutateTo(card);
    }
}
