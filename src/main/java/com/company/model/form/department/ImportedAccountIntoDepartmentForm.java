package com.company.model.form.department;

import com.company.model.validation.account.AccountNoDepartment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ImportedAccountIntoDepartmentForm {

    @NotNull
    @NotEmpty
    private List<@NotNull @AccountNoDepartment Integer> employeeIds;

    @AccountNoDepartment
    private Integer managerId;
}