package com.alexic0n.moneymate.transactionapp.query.ledger.model;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Ledger {
    String id;
    String name;
    String description;
    String currencyId;
    ZonedDateTime createdAt;
}
