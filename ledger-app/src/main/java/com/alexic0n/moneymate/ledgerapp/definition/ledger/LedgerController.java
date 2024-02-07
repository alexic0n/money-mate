package com.alexic0n.moneymate.ledgerapp.definition.ledger;


import com.alexic0n.moneymate.ledgerapp.definition.ledger.model.Ledger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.lang.String.format;

@RestController
@RequestMapping("/ledgers")
public class LedgerController {

    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @GetMapping
    public ResponseEntity<Page<Ledger>> getPageOfAllLedgers(Pageable pageable){
        return ResponseEntity.ok(ledgerService.getPageOfAllEntities(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ledger> getLedgerById(@PathVariable String id){
        return ResponseEntity.ok(ledgerService.getEntityById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createLedger(@RequestBody Ledger ledger){
        return ResponseEntity.created(
                URI.create(format("/money-mate/ledger-app/ledgers/%s", ledgerService.createEntity(ledger).getId().toString()))
        ).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateLedger(@PathVariable String id, @RequestBody Ledger ledger){
        ledgerService.updateEntity(id, ledger);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLedger(@PathVariable String id){
        ledgerService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }
}
