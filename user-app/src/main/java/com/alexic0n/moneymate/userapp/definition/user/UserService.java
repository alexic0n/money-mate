package com.alexic0n.moneymate.userapp.definition.user;

import com.alexic0n.moneymate.userapp.definition.AbstractUserApplicationService;
import com.alexic0n.moneymate.userapp.definition.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractUserApplicationService<UserRepository, User> {
    public UserService(UserRepository repository) {
        super(repository, "user");
    }
}
