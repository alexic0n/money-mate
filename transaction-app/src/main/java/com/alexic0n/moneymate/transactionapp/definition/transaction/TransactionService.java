package com.alexic0n.moneymate.transactionapp.definition.transaction;

import com.alexic0n.moneymate.transactionapp.definition.AbstractTransactionDefinitionService;
import com.alexic0n.moneymate.transactionapp.definition.transaction.model.Transaction;
import com.alexic0n.moneymate.transactionapp.query.ledger.LedgerClientService;
import com.alexic0n.moneymate.transactionapp.query.ledger.model.Ledger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends AbstractTransactionDefinitionService<TransactionRepository, Transaction> {

    private final LedgerClientService ledgerClientService;

    public TransactionService(
            TransactionRepository repository,
            LedgerClientService ledgerClientService
        ) {
        super(repository, "transaction");
        this.ledgerClientService = ledgerClientService;
    }


    public Page<Transaction> getPageOfEntitiesByLedgerId(String ledgerId, Pageable pageable) {
        return repository.findAllByLedgerId(ledgerId, pageable);
    }

    @Override
    public void validateCreateEntity(Transaction entity) {
        super.validateCreateEntity(entity);
        Ledger ledger = ledgerClientService.getEntityById(entity.getLedgerId());
    }

}
