package com.company.repository;

import com.company.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupRepository extends JpaRepository<Group, Integer> {
}