package com.company.model.validation.account;

import com.company.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class AccountUsernameExistsValidator implements ConstraintValidator<AccountUsernameExists, String> {

    @Autowired
    private AccountService service;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        if (StringUtils.isEmpty(username)) {
            return true;
        }

        return service.isAccountExistsByUsername(username);
    }
}