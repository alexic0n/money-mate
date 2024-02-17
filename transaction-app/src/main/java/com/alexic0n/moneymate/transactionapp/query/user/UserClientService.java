package com.alexic0n.moneymate.transactionapp.query.user;

import com.alexic0n.moneymate.transactionapp.query.AbstractTransactionClientService;
import com.alexic0n.moneymate.transactionapp.query.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserClientService extends AbstractTransactionClientService<User> {

    public UserClientService(
            @Value("${money-mate.user-application.url}") String userApplicationUrl,
            WebClient webClient
    ) {
        super(
                userApplicationUrl + "/users",
                "user",
                webClient
        );
    }
}
