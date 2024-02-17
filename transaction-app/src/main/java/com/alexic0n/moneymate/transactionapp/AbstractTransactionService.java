package com.alexic0n.moneymate.transactionapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;

public class AbstractTransactionService {

    protected final String entityName;

    public AbstractTransactionService(String entityName){
        this.entityName = entityName;
    }

    protected ErrorResponseException notFoundException(String id) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                format("%s with id %s not found",this.entityName, id)
        );
    }

    protected ErrorResponseException badRequestException(String message) {
        return new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                format(message)
        );
    }
}
