package com.alexic0n.moneymate.ledgerapp.definition.ledger.model;

import com.alexic0n.moneymate.ledgerapp.definition.AbstractLedgerApplicationEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Ledger extends AbstractLedgerApplicationEntity {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String currencyId;

    public Ledger(
            String name,
            String description,
            String currencyId
    ) {
        this.name = name;
        this.description = description;
        this.currencyId = currencyId;
    }

}
