package com.company.service;

import com.company.model.dto.account.AccountNoDepartmentDTO;
import com.company.model.dto.account.ImportedAccountInfoDTO;
import com.company.model.dto.department.DepartmentDTO;
import com.company.model.entity.Account;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {

    Account getAccountByUsername(String username);

    Account getAccountById(Integer id);

    boolean isAccountExistsByUsername(String username);

    boolean isAccountExistsByEmail(String email);

    boolean isOldPasswordCorrect(String oldPassword);

    boolean isAccountExistsById(Integer id);

    DepartmentDTO getDepartmentInfo();

    List<AccountNoDepartmentDTO> getAllAccountsNoDepartment(Sort sort, String q);

    List<ImportedAccountInfoDTO> getInfoAccountByUsername(List<String> usernames);

}
