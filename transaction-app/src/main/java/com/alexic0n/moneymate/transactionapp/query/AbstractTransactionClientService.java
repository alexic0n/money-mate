package com.alexic0n.moneymate.transactionapp.query;

import com.alexic0n.moneymate.transactionapp.AbstractTransactionService;
import com.alexic0n.moneymate.transactionapp.query.ledger.model.Ledger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;

import static java.lang.String.format;

public abstract class AbstractTransactionClientService<T> extends AbstractTransactionService {

    protected final String targetUrl;

    protected final WebClient webClient;

    private static final String BAD_REQUEST_MESSAGE = "Bad request when receiving %s with id:%s";

    public AbstractTransactionClientService(String targetUrl, String entityName, WebClient webClient) {
        super(entityName);
        this.targetUrl = targetUrl;
        this.webClient = webClient;
    }

    public T getEntityById(String id, Class<T> responseBodyType) {
        return webClient.get()
                .uri(targetUrl + "/" + id)
                .retrieve()
                .onStatus(
                        httpStatusCode -> httpStatusCode.equals(HttpStatus.NOT_FOUND),
                        clientResponse -> {throw notFoundException(id);}
                )
                .onStatus(
                        httpStatusCode -> httpStatusCode.is4xxClientError() || httpStatusCode.is5xxServerError(),
                        clientResponse -> {throw badRequestException(format(BAD_REQUEST_MESSAGE, this.entityName, id));}
                )
                .bodyToMono(responseBodyType)
                .block();
    }
}
