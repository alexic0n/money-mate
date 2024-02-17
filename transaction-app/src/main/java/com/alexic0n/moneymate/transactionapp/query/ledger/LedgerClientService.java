package com.alexic0n.moneymate.transactionapp.query.ledger;

import com.alexic0n.moneymate.transactionapp.query.AbstractTransactionClientService;
import com.alexic0n.moneymate.transactionapp.query.ledger.model.Ledger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LedgerClientService extends AbstractTransactionClientService<Ledger> {

    public LedgerClientService(
            @Value("${money-mate.ledger-application.url}") String ledgerApplicationUrl,
            WebClient webClient
    ) {
        super(
                ledgerApplicationUrl + "/ledgers",
                "ledger",
                webClient
        );
    }
}
