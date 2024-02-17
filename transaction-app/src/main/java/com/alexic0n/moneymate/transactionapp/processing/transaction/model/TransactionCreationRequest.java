package com.alexic0n.moneymate.transactionapp.processing.transaction.model;

import com.alexic0n.moneymate.transactionapp.processing.transaction.model.split.AbstractTransactionSplit;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionCreationRequest {
    private String ledgerId;
    private String description;
    private String userId;
    private AbstractTransactionSplit split;
    private Long amount;
}
