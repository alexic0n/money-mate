package com.alexic0n.moneymate.ledgerapp.definition.currency;

import com.alexic0n.moneymate.ledgerapp.definition.AbstractLedgerApplicationService;
import com.alexic0n.moneymate.ledgerapp.definition.currency.model.Currency;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class CurrencyService extends AbstractLedgerApplicationService<CurrencyRepository, Currency> {

    private static final String CODE_ALREADY_EXISTS = "Currency with currencyCode=%s already exists.";

    public CurrencyService(CurrencyRepository repository) {
        super(repository, "currency");
    }

    @Override
    public void validateCreateEntity(Currency currency) {
        if (repository.existsByCode(currency.getCode())) {
            throw badRequestException(format(CODE_ALREADY_EXISTS, currency.getCode()), null);
        }
    }

    @Override
    public void validateUpdateEntity(Currency currency) {
        if (repository.existsByCodeAndIdNot(currency.getCode(), currency.getId())) {
            throw badRequestException(format(CODE_ALREADY_EXISTS, currency.getCode()), null);
        }
    }
}
