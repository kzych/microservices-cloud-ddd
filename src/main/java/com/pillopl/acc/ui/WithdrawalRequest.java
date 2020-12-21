package com.pillopl.acc.ui;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class WithdrawalRequest {

    BigDecimal amount;
    String cardRefNo;
}
