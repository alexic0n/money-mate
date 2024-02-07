package com.alexic0n.moneymate.ledgerapp.definition.ledger;

import com.alexic0n.moneymate.ledgerapp.definition.AbstractLedgerApplicationService;
import com.alexic0n.moneymate.ledgerapp.definition.currency.CurrencyService;
import com.alexic0n.moneymate.ledgerapp.definition.ledger.model.Ledger;
import org.springframework.stereotype.Service;

@Service
public class LedgerService extends AbstractLedgerApplicationService<LedgerRepository, Ledger> {


    private final CurrencyService currencyService;

    public LedgerService(
            LedgerRepository repository,
            CurrencyService currencyService
    ) {
        super(repository, "ledger");
        this.currencyService = currencyService;
    }

    public Boolean existsLedgerWithCurrencyId(String currencyId) {
        return repository.existsByCurrencyId(currencyId);
    }

    @Override
    protected void validateCreateEntity(Ledger ledger) {
        currencyService.validateEntityExistsById(ledger.getCurrencyId());
    }

    @Override
    protected void validateUpdateEntity(Ledger ledger) {
        currencyService.validateEntityExistsById(ledger.getCurrencyId());
    }
}
