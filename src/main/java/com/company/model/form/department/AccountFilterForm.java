package com.company.model.form.department;

import com.company.model.entity.Account.Role;
import com.company.model.entity.Account.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AccountFilterForm {

    @JsonProperty("q")
    private String q;

    @PastOrPresent
    private Date minCreatedDate;

    private Date maxCreatedDate;

    private Role role;

    private Status status;
}
