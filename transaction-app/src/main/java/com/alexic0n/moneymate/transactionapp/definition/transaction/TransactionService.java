package com.alexic0n.moneymate.transactionapp.definition.transaction;

import com.alexic0n.moneymate.transactionapp.definition.AbstractTransactionApplicationService;
import com.alexic0n.moneymate.transactionapp.definition.transaction.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends AbstractTransactionApplicationService<TransactionRepository, Transaction> {
    public TransactionService(TransactionRepository repository) {
        super(repository, "transaction");
    }


}
