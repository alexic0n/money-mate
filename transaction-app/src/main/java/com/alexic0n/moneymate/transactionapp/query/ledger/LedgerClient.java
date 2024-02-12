package com.alexic0n.moneymate.transactionapp.query.ledger;

import com.alexic0n.moneymate.transactionapp.query.ledger.model.Ledger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.reactive.function.client.WebClient;

import static java.lang.String.format;

@Service
public class LedgerClient {

    private final WebClient webClient;

    private final String entityName = "ledger";

    @Value("${money-mate.ledger-application.url}")
    private String ledgerApplicationUrl;

    public LedgerClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Ledger getLedgerById(String ledgerId) {
        return webClient.get()
                .uri(ledgerApplicationUrl + "/ledgers/" + ledgerId)
                .retrieve()
                .onStatus(
                        httpStatusCode -> httpStatusCode.equals(HttpStatus.NOT_FOUND),
                        clientResponse -> {throw notFoundException(ledgerId, null);}
                )
                .onStatus(
                        httpStatusCode -> httpStatusCode.is4xxClientError() || httpStatusCode.is5xxServerError(),
                        clientResponse -> {throw badRequestException("Bad request", null);}
                )
                .bodyToMono(Ledger.class)
                .block();
    }

    protected ErrorResponseException notFoundException(String id, Throwable cause) {
        return new ErrorResponseException(
                HttpStatus.NOT_FOUND,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        format("%s with id %s not found", entityName, id)
                ),
                cause
        );
    }

    protected ErrorResponseException badRequestException(String message, Throwable cause) {
        return new ErrorResponseException(
                HttpStatus.BAD_REQUEST,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.BAD_REQUEST,
                        message
                ),
                cause
        );
    }
}
