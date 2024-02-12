package com.alexic0n.moneymate.transactionapp.definition.transaction;

import com.alexic0n.moneymate.transactionapp.definition.transaction.model.Transaction;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, ObjectId> {

    Page<Transaction> findAllByLedgerId(String ledgerId, Pageable pageable);

}
