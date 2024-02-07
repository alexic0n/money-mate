package com.alexic0n.moneymate.ledgerapp.definition.currency;

import com.alexic0n.moneymate.ledgerapp.definition.currency.model.Currency;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, ObjectId> {

    Boolean existsByCode(String code);

    Boolean existsByCodeAndIdNot(String code, ObjectId id);
}
