package com.alexic0n.moneymate.transactionapp.definition.debt;

import com.alexic0n.moneymate.transactionapp.definition.debt.model.Debt;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtRepository extends MongoRepository<Debt, ObjectId> {

    Page<Debt> findAllByTransactionId(String transactionId);
}
