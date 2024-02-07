package com.alexic0n.moneymate.ledgerapp.definition.transaction.model;

import com.alexic0n.moneymate.ledgerapp.definition.AbstractLedgerApplicationEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Transaction extends AbstractLedgerApplicationEntity {

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
