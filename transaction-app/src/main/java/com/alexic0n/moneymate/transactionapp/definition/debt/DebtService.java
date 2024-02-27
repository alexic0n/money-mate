package com.alexic0n.moneymate.transactionapp.definition.debt;

import com.alexic0n.moneymate.transactionapp.definition.AbstractTransactionDefinitionService;
import com.alexic0n.moneymate.transactionapp.definition.debt.model.Debt;
import com.alexic0n.moneymate.transactionapp.definition.transaction.TransactionService;
import com.alexic0n.moneymate.transactionapp.query.user.UserClientService;
import com.alexic0n.moneymate.transactionapp.query.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DebtService extends AbstractTransactionDefinitionService<DebtRepository, Debt> {

    private final TransactionService transactionService;

    private final UserClientService userClientService;

    public DebtService(
            DebtRepository repository,
            TransactionService transactionService,
            UserClientService userClientService
    ) {
        super(repository, "debt");
        this.transactionService = transactionService;
        this.userClientService = userClientService;
    }

    public Page<Debt> getPageOfEntitiesByTransactionId(String transactionId, Pageable pageable) {
        return repository.findAllByTransactionId(transactionId, pageable);
    }

    @Override
    public void validateCreateEntity(Debt entity) {
        super.validateCreateEntity(entity);
        transactionService.validateEntityExistsById(entity.getTransactionId());
        userClientService.getEntityById(entity.getUserId(), User.class);
    }
}
