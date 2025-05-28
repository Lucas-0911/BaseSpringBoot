package com.company.model.validation.department;

import com.company.service.DepartmentService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentIdExistsValidator implements ConstraintValidator<DepartmentIdExists, Integer> {

    @Autowired
    private DepartmentService service;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {

        if (id == null || id <= 0) {
            return false;
        }

        return service.isDepartmentExistsById(id);
    }
}