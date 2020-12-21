package com.pillopl.acc.ui.rabbit;

import com.pillopl.acc.application.WithdrawalProcess;
import com.pillopl.acc.ui.WithdrawalRequest;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class RabbitListener {

    private final WithdrawalProcess withdrawalProcess;

    public RabbitListener(WithdrawalProcess withdrawalProcess) {
        this.withdrawalProcess = withdrawalProcess;
    }

    @StreamListener(value = Sink.INPUT, condition = "headers['type'] == 'wr'")
    public void handle(WithdrawalRequest withdrawalRequest) {
        withdrawalProcess.withdraw(withdrawalRequest.getAmount(), withdrawalRequest.getCardRefNo());

    }
}
