package com.pillopl.acc.application;

import com.pillopl.acc.domain.VirtualCreditCard;
import com.pillopl.acc.domain.CreditCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class WithdrawalProcess {

    private final CreditCardRepository creditCardRepository;

    public WithdrawalProcess(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    //transaction
    //security
    //logging
    public void withdraw(BigDecimal amount, String cardRefNo) {
        log.info("withdrawing money");
        VirtualCreditCard virtualCreditCard = creditCardRepository.findById(cardRefNo);

        virtualCreditCard.withdraw(amount);

        creditCardRepository.save(virtualCreditCard);
    }
}
