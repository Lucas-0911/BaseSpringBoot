package com.company.service;

import com.company.model.dto.department.AccountDTO;
import com.company.model.dto.department.DepartmentDTO;
import com.company.model.dto.department.DepartmentDetailDTO;
import com.company.model.dto.department.DepartmentForFilterDTO;
import com.company.model.form.department.AccountFilterForm;
import com.company.model.form.department.CreatingDepartmentForm;
import com.company.model.form.department.DepartmentFilterForm;
import com.company.model.form.department.ImportedAccountIntoDepartmentForm;
import com.company.model.form.department.UpdatingDepartmentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    Page<DepartmentDTO> getAllDepartments(Pageable pageable, DepartmentFilterForm form);

    boolean isDepartmentExistsById(Integer id);

    DepartmentDetailDTO getDepartmentById(Integer id);

    Page<AccountDTO> getAllAccountsByDepartmentId(Integer departmentId, Pageable pageable, AccountFilterForm form);

    void removeAccountsInDepartment(List<Integer> accountIds);

    boolean isDepartmentExistsByName(String name);

    void createDepartment(CreatingDepartmentForm form);

    void updateDepartment(Integer departmentId, UpdatingDepartmentForm form);

    boolean isDepartmentHasUser(Integer departmentId);

    void deleteDepartment(Integer departmentId);

    List<DepartmentForFilterDTO> getAllDepartmentsForFilter();

    void importedAccountsIntoDepartment(Integer departmentId, ImportedAccountIntoDepartmentForm form);
}