package com.scotchboard.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scotchboard.management.model.Consent;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, Long> {

}
