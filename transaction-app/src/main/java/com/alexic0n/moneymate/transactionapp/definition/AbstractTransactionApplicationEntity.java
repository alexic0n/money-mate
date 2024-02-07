package com.alexic0n.moneymate.transactionapp.definition;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
public abstract class AbstractTransactionApplicationEntity {

    @MongoId
    @JsonSerialize(using= ToStringSerializer.class)
    protected ObjectId id;

    public ZonedDateTime getCreatedAt() {
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(id.getTimestamp()), ZoneId.systemDefault());
    }
}
