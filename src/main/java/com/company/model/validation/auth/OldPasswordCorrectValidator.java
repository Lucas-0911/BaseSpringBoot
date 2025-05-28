package com.company.model.validation.auth;

import com.company.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class OldPasswordCorrectValidator implements ConstraintValidator<OldPasswordCorrect, String> {

    @Autowired
    private AccountService accountService;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(String oldPassword, ConstraintValidatorContext context) {

        if (StringUtils.isEmpty(oldPassword)) {
            return false;
        }

        return accountService.isOldPasswordCorrect(oldPassword);
    }
}
