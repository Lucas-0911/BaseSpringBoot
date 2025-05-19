package com.company.model.specification.department;

import com.company.model.entity.Account;
import com.company.model.form.department.AccountFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class AccountSpecification {

    @SuppressWarnings("deprecation")
    public static Specification<Account> buildWhere(Integer departmentId, AccountFilterForm form) {

        Specification<Account> where = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("department"), departmentId);

        if (form == null) return where;

        if (form.getQ() != null && !StringUtils.isEmpty(form.getQ().trim())) {
            String search = form.getQ().trim();

            Specification<Account> usernameSpecification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("username"), "%" + search + "%");
            Specification<Account> fullnameSpecification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("fullname"), "%" + search + "%");

            where = where.and(usernameSpecification.or(fullnameSpecification));
        }

        if (form.getMinCreatedDate() != null) {
            Specification<Account> minCreatedDateSpecification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("createdDateTime").as(java.sql.Date.class), form.getMinCreatedDate());
            where = where.and(minCreatedDateSpecification);
        }

        if (form.getMaxCreatedDate() != null) {
            Specification<Account> maxCreatedDateSpecification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("createdDateTime").as(java.sql.Date.class), form.getMaxCreatedDate());
            where = where.and(maxCreatedDateSpecification);
        }

        if (form.getRole() != null) {
            Specification<Account> roleSpecification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("role"), form.getRole());
            where = where.and(roleSpecification);
        }

        if (form.getStatus() != null) {
            Specification<Account> statusSpecification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("status"), form.getStatus());
            where = where.and(statusSpecification);
        }

        return where;
    }
}
