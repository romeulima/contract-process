package com.romeulima.processcontract.services;

import com.romeulima.processcontract.domain.contract.Contract;
import com.romeulima.processcontract.domain.installment.Installment;

import java.time.LocalDate;

public class ContractService {

    private final OnlinePaymentService paymentService;

    public ContractService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processContract(Contract contract, Integer installmentsNumber){
        double partialValueInstallments = contract.getTotalValue() / installmentsNumber;

        for (int i = 1; i <= installmentsNumber; i++) {

            Double interest = paymentService.interest(partialValueInstallments, i);

            Double fee = paymentService.paymentFee(partialValueInstallments + interest);

            Double amount = partialValueInstallments + interest + fee;

            LocalDate dueDate = contract.getDate().plusMonths(i);

            contract.getInstallments().add(new Installment(dueDate, amount));
        }
    }
}
