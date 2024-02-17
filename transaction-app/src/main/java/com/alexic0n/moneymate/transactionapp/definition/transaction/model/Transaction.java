package com.alexic0n.moneymate.transactionapp.definition.transaction.model;

import com.alexic0n.moneymate.transactionapp.definition.AbstractTransactionDefinitionEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Transaction extends AbstractTransactionDefinitionEntity {

    private String ledgerId;

    private String description;

    private String userId;

    private Long amount;

    public Transaction(
            String ledgerId,
            String description,
            String userId,
            Long amount
    ) {
        this.ledgerId = ledgerId;
        this.description = description;
        this.userId = userId;
        this.amount = amount;
    }
}
