package com.company.model.validation.auth;

import com.company.service.JWTTokenService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class RefreshTokenValidValidator implements ConstraintValidator<RefreshTokenValid, String> {

    @Autowired
    private JWTTokenService jwtTokenService;

    @SuppressWarnings("deprecation")
    @Override
    public boolean isValid(String refreshToken, ConstraintValidatorContext constraintValidatorContext) {

        if (StringUtils.isEmpty(refreshToken)) {
            return false;
        }

        return jwtTokenService.isRefreshTokenValid(refreshToken);
    }
}
