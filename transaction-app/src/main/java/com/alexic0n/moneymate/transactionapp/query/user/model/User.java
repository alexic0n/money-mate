package com.alexic0n.moneymate.transactionapp.query.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class User {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String title;
}
