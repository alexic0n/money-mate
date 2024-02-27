package com.alexic0n.moneymate.transactionapp.processing.transaction.model.split;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "split_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EqualTransactionSplit.class, name = "EQUAL_AMOUNT"),
        @JsonSubTypes.Type(value = UnequalAmountTransactionSplit.class, name = "UNEQUAL_AMOUNT"),
        @JsonSubTypes.Type(value = UnequalPercentageTransactionSplit.class, name = "UNEQUAL_PERCENTAGE")
})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class AbstractTransactionSplit {

    public List<String> getUserIdsForVerification() {
        return new ArrayList<>();
    }
}
