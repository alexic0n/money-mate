package com.alexic0n.moneymate.userapp.definition.user.model;

import com.alexic0n.moneymate.userapp.definition.AbstractUserApplicationEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User extends AbstractUserApplicationEntity {

    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String title;

    public User(
            String username,
            String email,
            String firstName,
            String lastName,
            String title
    ) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }
}
