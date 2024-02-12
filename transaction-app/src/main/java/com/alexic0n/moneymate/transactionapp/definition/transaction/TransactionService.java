package com.alexic0n.moneymate.transactionapp.definition.transaction;

import com.alexic0n.moneymate.transactionapp.definition.AbstractTransactionApplicationService;
import com.alexic0n.moneymate.transactionapp.definition.transaction.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends AbstractTransactionApplicationService<TransactionRepository, Transaction> {

    private final WebClient webClient;

    public TransactionService(TransactionRepository repository) {
        super(repository, "transaction");
    }


    public Page<Transaction> getPageOfEntitiesByLedgerId(String ledgerId, Pageable pageable) {
        return repository.findAllByLedgerId(ledgerId, pageable);
    }

    @Override
    public void validateCreateEntity(Transaction entity) {
        super.validateCreateEntity(entity);

    }

}
