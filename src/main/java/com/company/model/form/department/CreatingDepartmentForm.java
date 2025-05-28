package com.company.model.form.department;

import com.company.model.validation.account.AccountNoDepartment;
import com.company.model.validation.department.DepartmentNameNotExists;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@NoArgsConstructor
public class CreatingDepartmentForm {

    @NotBlank
    @Length(max = 100)
    @DepartmentNameNotExists
    private String name;

    @NotNull
    @AccountNoDepartment
    private Integer managerId;

    private List<@AccountNoDepartment Integer> employeeIds;
}