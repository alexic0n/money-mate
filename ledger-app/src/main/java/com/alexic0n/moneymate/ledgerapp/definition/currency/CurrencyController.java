package com.alexic0n.moneymate.ledgerapp.definition.currency;

import com.alexic0n.moneymate.ledgerapp.definition.currency.model.Currency;
import com.alexic0n.moneymate.ledgerapp.definition.ledger.LedgerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.lang.String.format;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    private final LedgerService ledgerService;

    public CurrencyController(
            CurrencyService currencyService,
            LedgerService ledgerService
    ) {
        this.currencyService = currencyService;
        this.ledgerService = ledgerService;
    }

    @GetMapping
    public ResponseEntity<Page<Currency>> getPageOfAllCurrencies(Pageable pageable){
        return ResponseEntity.ok(currencyService.getPageOfAllEntities(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable String id){
        return ResponseEntity.ok(currencyService.getEntityById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createCurrency(@RequestBody Currency currency){
        return ResponseEntity.created(
                URI.create(format("/money-mate/ledger-app/currencies/%s", currencyService.createEntity(currency).getId().toString()))
        ).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCurrency(@PathVariable String id, @RequestBody Currency currency){
        currencyService.updateEntity(id, currency);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable String id){
        Currency currency = currencyService.getEntityById(id);
        if (ledgerService.existsLedgerWithCurrencyId(currency.getId().toString())){
            throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    ProblemDetail.forStatusAndDetail(
                            HttpStatus.BAD_REQUEST,
                            "Currency is used in existing ledgers"
                    ),
                    null
            );
        }
        currencyService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }
}
