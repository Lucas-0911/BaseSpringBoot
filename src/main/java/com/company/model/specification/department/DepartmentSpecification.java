package com.company.model.specification.department;

import com.company.model.entity.Department;
import com.company.model.form.department.DepartmentFilterForm;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class DepartmentSpecification {

    @SuppressWarnings("deprecation")
    public static Specification<Department> buildWhere(DepartmentFilterForm form) {

        Specification<Department> where = null;

        if (form == null) return where;

        if (form.getQ() != null && !StringUtils.isEmpty(form.getQ().trim())) {
            String search = form.getQ().trim();

            where = searchByKeyword(search);
        }

        if (form.getMinCreatedDate() != null) {
            Specification<Department> minCreatedDateSpecification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("createdDateTime").as(java.sql.Date.class), form.getMinCreatedDate());

            where = where == null ? minCreatedDateSpecification : where.and(minCreatedDateSpecification);
        }

        if (form.getMaxCreatedDate() != null) {
            Specification<Department> maxCreatedDateSpecification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("createdDateTime").as(java.sql.Date.class), form.getMaxCreatedDate());

            where = where == null ? maxCreatedDateSpecification : where.and(maxCreatedDateSpecification);
        }

        if (form.getMinMemberSize() != null) {
            Specification<Department> minMemberSizeSpecification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("memberSize").as(Integer.class), form.getMinMemberSize());
            where = where == null ? minMemberSizeSpecification : where.and(minMemberSizeSpecification);
        }

        if (form.getMaxMemberSize() != null) {
            Specification<Department> maxMemberSizeSpecification = (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("memberSize").as(Integer.class), form.getMaxMemberSize());

            where = where == null ? maxMemberSizeSpecification : where.and(maxMemberSizeSpecification);
        }

        return where;
    }

    private static Specification<Department> searchByKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> managerJoin = root.join("manager", JoinType.LEFT);

            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("name"), "%" + keyword + "%"),
                    criteriaBuilder.like(managerJoin.get("fullname"), "%" + keyword + "%"),
                    criteriaBuilder.like(managerJoin.get("email"), "%" + keyword + "%"),
                    criteriaBuilder.like(managerJoin.get("username"), "%" + keyword + "%")
            );
        };
    }

}
