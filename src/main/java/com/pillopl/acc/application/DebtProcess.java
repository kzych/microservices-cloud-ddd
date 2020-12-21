package com.pillopl.acc.application;

import org.springframework.stereotype.Service;

@Service
public class DebtProcess {


//    public void handleDebt(Long userId, Long cardId) {
//        this.sth(userId, cardId);
//        communicationFacade.notifyTextMessage(userId);
//
//    }
//
//    @Transactional
//    public void sth(Long userId, Long cardId) {
//        cardFacade.deactive(cardId);
//        blackListService.blackList(userId);
//    }

}


//
//interface FeeModifier {
//    Fee modify(Fee fee);
//}
//
//class PercantageBase implements FeeModifier {
//
//    private final BigDecimal percent = new BigDecimal(5);
//
//    @Override
//    public Fee modify(Fee fee) {
//        return fee.modifyTo(fee * percent);
//    }
//}

//class ConstantModifier implements FeeModifier {
//
//    private final BigDecimal constantFee = new BigDecimal(5);
//
//    @Override
//    public Fee modify(Fee fee) {
//        return fee.modifyTo(constantFee);
//    }
//}
//
//class SuperCoolConfigrableModifier implements FeeModifier {
//
//    Validator validator;
//    Applyier applyier;
//    Guardian guardian;
//
//
//    @Override
//    public Fee modify(Fee fee) {
//        if(validator.passes(fee)) {
//            Fee newFee = applier.applyTo(fee);
//            if(guardian.passes(newFee)) {
//                return newFee;
//            }
//            return fee;
//        }
//    }
//}
//
//
//
//
//
//class Fee {
//    final BigDecimal feeAmount;
//    final BigDecimal withdrawalAmount;
//
//    Fee(BigDecimal feeAmount, BigDecimal withdrawalAmount) {
//        this.feeAmount = feeAmount;
//        this.withdrawalAmount = withdrawalAmount;
//    }
//
//
//    Fee modifyTo(BigDecimal feeAmount) {
//        return new Fee(withdrawalAmount, feeAmount);
//    }
