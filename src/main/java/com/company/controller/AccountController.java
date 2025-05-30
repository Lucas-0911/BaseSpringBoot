package com.company.controller;

import com.company.model.dto.account.AccountNoDepartmentDTO;
import com.company.model.dto.account.ImportedAccountInfoDTO;
import com.company.model.dto.department.DepartmentDTO;
import com.company.model.validation.account.AccountUsernameExists;
import com.company.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@Validated
@Log4j2
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/username/exists")
    public boolean isAccountExistsByUsername(String username) {
        return accountService.isAccountExistsByUsername(username);
    }

    @GetMapping("/email/exists")
    public boolean isAccountExistsByEmail(String email) {
        return accountService.isAccountExistsByEmail(email);
    }

    @GetMapping("/usernameOrEmail/exists")
    public boolean isAccountExistsByUsernameOrEmail(String usernameOrEmail) {
        return accountService.isAccountExistsByUsername(usernameOrEmail) || accountService.isAccountExistsByEmail(usernameOrEmail);
    }

    @GetMapping("/department")
    public DepartmentDTO getDepartmentInfo() {
        return accountService.getDepartmentInfo();
    }

    @GetMapping("/noDepartment")
    public List<AccountNoDepartmentDTO> getAllAccountsNoDepartment(Sort sort,
                                                                   @RequestParam(value = "q", required = false) String q) {
        return accountService.getAllAccountsNoDepartment(sort, q);
    }

    @GetMapping("/info")
    public List<ImportedAccountInfoDTO> getInfoAccountByUsername(
            @RequestParam(name = "usernames") List<@AccountUsernameExists String> usernames) {
        return accountService.getInfoAccountByUsername(usernames);
    }

}
