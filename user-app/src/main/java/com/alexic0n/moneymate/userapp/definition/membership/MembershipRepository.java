package com.alexic0n.moneymate.userapp.definition.membership;

import com.alexic0n.moneymate.userapp.definition.membership.model.Membership;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends MongoRepository<Membership, ObjectId> {
}
