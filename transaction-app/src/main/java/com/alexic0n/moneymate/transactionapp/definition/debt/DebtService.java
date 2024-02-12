package com.alexic0n.moneymate.transactionapp.definition.debt;

import com.alexic0n.moneymate.transactionapp.definition.AbstractTransactionApplicationService;
import com.alexic0n.moneymate.transactionapp.definition.debt.model.Debt;
import org.springframework.stereotype.Service;

@Service
public class DebtService extends AbstractTransactionApplicationService<DebtRepository, Debt> {

    public DebtService(DebtRepository repository) {
        super(repository, "debt");
    }
}
