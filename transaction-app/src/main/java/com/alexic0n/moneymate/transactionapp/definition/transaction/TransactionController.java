package com.alexic0n.moneymate.transactionapp.definition.transaction;

import com.alexic0n.moneymate.transactionapp.definition.transaction.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<Page<Transaction>> getPageOfAllTransactions(
            Pageable pageable,
            @RequestParam Optional<String> ledgerId
    ){
        if(ledgerId.isPresent()){
            return ResponseEntity.ok(transactionService.getPageOfEntitiesByLedgerId(ledgerId.get(), pageable));
        }
        return ResponseEntity.ok(transactionService.getPageOfAllEntities(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id){
        return ResponseEntity.ok(transactionService.getEntityById(id));
    }

}
