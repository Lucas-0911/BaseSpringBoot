package com.company.repository;

import com.company.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Account findByUsername(String username);

    Account findByUsernameOrEmail(String username, String email);

    @Query(value = "UPDATE `account` SET department_id = :departmentId WHERE id IN (:accountIds)", nativeQuery = true)
    @Modifying
    void updateDepartment(
            @Param("departmentId") Integer departmentId,
            @Param("accountIds") List<Integer> accountIds);

    List<Account> findByUsernameIn(List<String> usernames);
}
