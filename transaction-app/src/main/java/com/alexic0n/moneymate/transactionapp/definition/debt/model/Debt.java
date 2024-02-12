package com.alexic0n.moneymate.transactionapp.definition.debt.model;

import com.alexic0n.moneymate.transactionapp.definition.AbstractTransactionApplicationEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Debt extends AbstractTransactionApplicationEntity {

    private final Long amount;

    private final String userId;

    private final String transactionId;

    public Debt(
            Long amount,
            String userId,
            String transactionId
    ) {
        this.amount = amount;
        this.userId = userId;
        this.transactionId = transactionId;
    }
}
