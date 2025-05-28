package com.company.model.validation.department;

import com.company.service.DepartmentService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentNoUserValidator implements ConstraintValidator<DepartmentNoUser, Integer> {

    @Autowired
    private DepartmentService service;

    @Override
    public boolean isValid(Integer departmentId, ConstraintValidatorContext context) {

        if (departmentId == null || departmentId <= 0) {
            return true;
        }

        return !service.isDepartmentHasUser(departmentId);
    }
}