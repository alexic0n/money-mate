package com.alexic0n.moneymate.transactionapp.processing.transaction;

import com.alexic0n.moneymate.transactionapp.AbstractTransactionService;
import com.alexic0n.moneymate.transactionapp.definition.debt.DebtService;
import com.alexic0n.moneymate.transactionapp.definition.debt.model.Debt;
import com.alexic0n.moneymate.transactionapp.definition.transaction.TransactionService;
import com.alexic0n.moneymate.transactionapp.definition.transaction.model.Transaction;
import com.alexic0n.moneymate.transactionapp.processing.transaction.model.TransactionCreationRequest;
import com.alexic0n.moneymate.transactionapp.processing.transaction.model.split.AbstractTransactionSplit;
import com.alexic0n.moneymate.transactionapp.processing.transaction.model.split.EqualTransactionSplit;
import com.alexic0n.moneymate.transactionapp.processing.transaction.model.split.UnequalAmountTransactionSplit;
import com.alexic0n.moneymate.transactionapp.processing.transaction.model.split.UnequalPercentageTransactionSplit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionCreationService extends AbstractTransactionService {

    private final TransactionService transactionService;

    private final DebtService debtService;

    public TransactionCreationService(
            TransactionService transactionService,
            DebtService debtService
    ) {
        super("createTransaction");
        this.transactionService = transactionService;
        this.debtService = debtService;
    }

    public Transaction createTransaction(TransactionCreationRequest request) {
        Transaction transaction = transactionService.createEntity(new Transaction(
                request.getLedgerId(),
                request.getDescription(),
                request.getUserId(),
                request.getAmount()
        ));
        List<Debt> debts = createDebts(request.getAmount(), request.getSplit(), transaction.getId().toString());
        debts.forEach(debtService::createEntity);
        return transaction;
    }

    private List<Debt> createDebts(Long amount, AbstractTransactionSplit split, String transactionId) {
        return switch (split) {
            case EqualTransactionSplit equalTransactionSplit ->
                    createDebtsFromEqualSplit(amount, equalTransactionSplit, transactionId);
            case UnequalPercentageTransactionSplit unequalPercentageTransactionSplit ->
                    createDebtsFromUnequalPercentageSplit(amount, unequalPercentageTransactionSplit, transactionId);
            case UnequalAmountTransactionSplit unequalAmountTransactionSplit ->
                    createDebtsFromUnequalAmountSplit(amount, unequalAmountTransactionSplit, transactionId);
            case null, default ->
                    throw new IllegalArgumentException("Unknown split type");
        };
    }

    private List<Debt> createDebtsFromEqualSplit(Long amount, EqualTransactionSplit split, String transactionId) {
        List<Debt> debts = new ArrayList<>();
        List<String> userIds = split.getUserIds();
        long remainder = amount % userIds.size();
        long amountPerUser = (amount-remainder) / userIds.size();
        for(int i = 0; i < userIds.size(); i++) {
            debts.add(new Debt(
                    amountPerUser + (i < remainder ? 1 : 0),
                    userIds.get(i),
                    transactionId
            ));
        }
        return debts;
    }

    private List<Debt> createDebtsFromUnequalPercentageSplit(Long amount, UnequalPercentageTransactionSplit split, String transactionId) {
        return null;
    }

    private List<Debt> createDebtsFromUnequalAmountSplit(Long amount, UnequalAmountTransactionSplit split, String transactionId) {
        List<Debt> debts = new ArrayList<>();
        if(split.getUserAmountMap().keySet().stream().mapToLong(split.getUserAmountMap()::get).sum() != amount) {
            throw badRequestException("Sum of user amounts does not match transaction amount");
        }
        split.getUserAmountMap().forEach((userId, userAmount) -> {
            debts.add(new Debt(
                    userAmount,
                    userId,
                    transactionId
            ));
        });
        return debts;
    }
}
