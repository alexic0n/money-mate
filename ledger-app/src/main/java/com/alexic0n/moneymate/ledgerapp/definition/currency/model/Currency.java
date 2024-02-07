package com.alexic0n.moneymate.ledgerapp.definition.currency.model;

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
public class Currency extends AbstractLedgerApplicationEntity {

    @NotEmpty
    private String name;
    @NotEmpty
    private String code;
    @NotEmpty
    private String description;
    @NotEmpty
    private String countryCode;

    public Currency(
            String name,
            String code,
            String description,
            String countryCode
    ) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.countryCode = countryCode;
    }
}
