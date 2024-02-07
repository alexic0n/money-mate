package com.alexic0n.moneymate.ledgerapp.definition.transaction;

import com.alexic0n.moneymate.ledgerapp.definition.AbstractLedgerApplicationService;
import com.alexic0n.moneymate.ledgerapp.definition.transaction.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends AbstractLedgerApplicationService<TransactionRepository, Transaction> {
    public TransactionService(TransactionRepository repository) {
        super(repository, "transaction");
    }


}
