package com.pillopl.acc;

import com.pillopl.acc.domain.CreditCardRepository;
import com.pillopl.acc.domain.VirtualCreditCard;
import com.pillopl.acc.ui.WithdrawalRequest;
import com.pillopl.acc.ui.rest.WithdrawalController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WithdrawalsTest {

    @Autowired
    WithdrawalController controller;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    Sink sink;

    @Before
    public void setup() {
        VirtualCreditCard virtualCreditCard = new VirtualCreditCard("ref");
        virtualCreditCard.assignLimit(new BigDecimal(100));
        creditCardRepository.save(virtualCreditCard);
    }

    @Test
    public void canWithdrawFromAdapterRest() {
        //when
        controller.withdraw(new WithdrawalRequest(BigDecimal.TEN, "ref"));

        //then
        assertThat(creditCardRepository.findById("ref").availableLimit())
                .isEqualByComparingTo(new BigDecimal(90));
    }

    @Test
    public void canWithdrawFromAdapterRabbit() {
        //when
        sink.input().send(new GenericMessage<>(new WithdrawalRequest(BigDecimal.TEN, "ref"), headers()));

        //then
        assertThat(creditCardRepository.findById("ref").availableLimit())
                .isEqualByComparingTo(new BigDecimal(90));
    }

    private Map<String, Object> headers() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("type","wr");
        return headers;
    }


}
