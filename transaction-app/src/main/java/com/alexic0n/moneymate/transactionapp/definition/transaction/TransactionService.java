package com.alexic0n.moneymate.transactionapp.definition.transaction;

import com.alexic0n.moneymate.transactionapp.definition.AbstractTransactionDefinitionService;
import com.alexic0n.moneymate.transactionapp.definition.transaction.model.Transaction;
import com.alexic0n.moneymate.transactionapp.query.ledger.LedgerClientService;
import com.alexic0n.moneymate.transactionapp.query.ledger.model.Ledger;
import com.alexic0n.moneymate.transactionapp.query.user.UserClientService;
import com.alexic0n.moneymate.transactionapp.query.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends AbstractTransactionDefinitionService<TransactionRepository, Transaction> {

    private final LedgerClientService ledgerClientService;

    private final UserClientService userClientService;

    public TransactionService(
            TransactionRepository repository,
            LedgerClientService ledgerClientService,
            UserClientService userClientService
        ) {
        super(repository, "transaction");
        this.ledgerClientService = ledgerClientService;
        this.userClientService = userClientService;
    }


    public Page<Transaction> getPageOfEntitiesByLedgerId(String ledgerId, Pageable pageable) {
        return repository.findAllByLedgerId(ledgerId, pageable);
    }

    @Override
    public void validateCreateEntity(Transaction entity) {
        super.validateCreateEntity(entity);
        ledgerClientService.getEntityById(entity.getLedgerId(), Ledger.class);
        userClientService.getEntityById(entity.getUserId(), User.class);
    }

}
