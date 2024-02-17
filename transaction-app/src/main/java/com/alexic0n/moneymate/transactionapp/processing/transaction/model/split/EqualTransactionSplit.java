package com.alexic0n.moneymate.transactionapp.processing.transaction.model.split;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EqualTransactionSplit extends AbstractTransactionSplit{
    List<String> userIds;
}
