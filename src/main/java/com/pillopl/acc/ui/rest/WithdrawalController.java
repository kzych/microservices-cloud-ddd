package com.pillopl.acc.ui.rest;


import com.pillopl.acc.application.WithdrawalProcess;
import com.pillopl.acc.ui.WithdrawalRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawalController {

    private final WithdrawalProcess withdrawalProcess;

    public WithdrawalController(WithdrawalProcess withdrawalProcess) {
        this.withdrawalProcess = withdrawalProcess;
    }

    @PostMapping("/withdrawals")
    public ResponseEntity withdraw(@RequestBody WithdrawalRequest wr) {
        withdrawalProcess.withdraw(wr.getAmount(), wr.getCardRefNo());
        return ResponseEntity.ok().build();
    }
}
