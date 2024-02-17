package com.alexic0n.moneymate.transactionapp.definition.debt;

import com.alexic0n.moneymate.transactionapp.definition.AbstractTransactionDefinitionService;
import com.alexic0n.moneymate.transactionapp.definition.debt.model.Debt;
import com.alexic0n.moneymate.transactionapp.definition.transaction.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class DebtService extends AbstractTransactionDefinitionService<DebtRepository, Debt> {

    private final TransactionService transactionService;

    public DebtService(
            DebtRepository repository,
            TransactionService transactionService
    ) {
        super(repository, "debt");
        this.transactionService = transactionService;
    }

    public Page<Debt> getPageOfEntitiesByTransactionId(String transactionId) {
        return repository.findAllByTransactionId(transactionId);
    }

    @Override
    public void validateCreateEntity(Debt entity) {
        super.validateCreateEntity(entity);
        transactionService.validateEntityExistsById(entity.getTransactionId());
    }
}
