package com.alexic0n.moneymate.ledgerapp.definition.ledger;

import com.alexic0n.moneymate.ledgerapp.definition.ledger.model.Ledger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LedgerRepository extends MongoRepository<Ledger, ObjectId> {

    Boolean existsByCurrencyId(String currencyId);
}
