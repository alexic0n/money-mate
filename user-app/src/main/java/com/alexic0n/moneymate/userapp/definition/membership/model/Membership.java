package com.alexic0n.moneymate.userapp.definition.membership.model;

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
public class Membership extends AbstractUserApplicationEntity {

    @NotBlank
    private String userId;
    @NotBlank
    private String ledgerId;

}
