package com.alexic0n.moneymate.transactionapp.definition.transaction;

import com.alexic0n.moneymate.transactionapp.definition.AbstractTransactionApplicationService;
import com.alexic0n.moneymate.transactionapp.definition.transaction.model.Transaction;
import com.alexic0n.moneymate.transactionapp.query.ledger.LedgerClient;
import com.alexic0n.moneymate.transactionapp.query.ledger.model.Ledger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TransactionService extends AbstractTransactionApplicationService<TransactionRepository, Transaction> {

    private final LedgerClient ledgerClient;

    public TransactionService(
            TransactionRepository repository,
            LedgerClient ledgerClient
        ) {
        super(repository, "transaction");
        this.ledgerClient = ledgerClient;
    }


    public Page<Transaction> getPageOfEntitiesByLedgerId(String ledgerId, Pageable pageable) {
        return repository.findAllByLedgerId(ledgerId, pageable);
    }

    @Override
    public void validateCreateEntity(Transaction entity) {
        Ledger ledger = ledgerClient.getLedgerById(entity.getLedgerId());
        super.validateCreateEntity(entity);
    }

}
