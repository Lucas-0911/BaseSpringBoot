package com.company.model.specification.account;

import com.company.model.entity.Account;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class AccountNoDepartmentSpecification {

    @SuppressWarnings("deprecation")
    public static Specification<Account> buildWhere(String q) {

        Specification<Account> where = (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get("department"));

        if (q != null && !StringUtils.isEmpty(q.trim())) {
            String search = q.trim();
            Specification<Account> usernameSpecification = (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + search + "%");
            Specification<Account> fullnameSpecification = ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("fullname"), "%" + search + "%"));

            where = where.and(usernameSpecification.or(fullnameSpecification));
        }

        return where;
    }
}