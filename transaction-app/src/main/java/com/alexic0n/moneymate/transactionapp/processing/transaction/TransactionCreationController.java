package com.alexic0n.moneymate.transactionapp.processing.transaction;

import com.alexic0n.moneymate.transactionapp.processing.transaction.model.TransactionCreationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static java.lang.String.format;

@RestController
@RequestMapping("/create")
public class TransactionCreationController {

    private final TransactionCreationService transactionCreationService;

    public TransactionCreationController(TransactionCreationService transactionCreationService) {
        this.transactionCreationService = transactionCreationService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionCreationRequest request) {
        return ResponseEntity.created(
                URI.create(format("/transactions/%s", transactionCreationService.createTransaction(request).getId()))
        ).build();
    }
}
