package com.vicento14.api_template_spring.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import com.vicento14.api_template_spring.models.UserAccounts;

public interface UserAccountsRepository extends CrudRepository<UserAccounts, Integer> {
    Streamable<UserAccounts> findByIdNumberContaining(String idNumber);
    Streamable<UserAccounts> findByFullNameContaining(String fullName);
    Streamable<UserAccounts> findByRole(String role);
    Iterable<UserAccounts> findAll(Specification<UserAccounts> spec);
}
