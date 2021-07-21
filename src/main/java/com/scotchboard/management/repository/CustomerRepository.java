package com.scotchboard.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scotchboard.management.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
