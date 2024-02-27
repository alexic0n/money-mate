package com.alexic0n.moneymate.transactionapp.processing.transaction.model.split;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UnequalPercentageTransactionSplit extends AbstractTransactionSplit {
    private Map<String, Long> userAmountMap;

    @Override
    public List<String> getUserIdsForVerification() {
        return userAmountMap.keySet().stream().toList();
    }
}
