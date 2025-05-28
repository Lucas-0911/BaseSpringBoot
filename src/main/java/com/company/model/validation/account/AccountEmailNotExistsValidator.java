package com.company.model.validation.account;

import com.company.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class AccountEmailNotExistsValidator implements ConstraintValidator<AccountEmailNotExists, String> {

    @Autowired
    private AccountService service;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if (StringUtils.isEmpty(email)) {
            return true;
        }

        return !service.isAccountExistsByEmail(email);
    }
}