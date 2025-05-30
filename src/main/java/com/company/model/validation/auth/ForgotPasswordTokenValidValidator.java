package com.company.model.validation.auth;

import com.company.service.TokenService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class ForgotPasswordTokenValidValidator implements ConstraintValidator<ForgotPasswordTokenValid, String> {

    @Autowired
    private TokenService tokenService;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(String forgotPasswordToken, ConstraintValidatorContext context) {

        if (StringUtils.isEmpty(forgotPasswordToken)) {
            return false;
        }

        return tokenService.isForgotPasswordTokenValid(forgotPasswordToken);
    }
}
