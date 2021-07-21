package com.scotchboard.management.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scotchboard.management.model.Consent;
import com.scotchboard.management.model.ConsentType;
import com.scotchboard.management.model.Customer;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, Long> {

	public Set<Consent> findByCustomer(Customer customer);
	
	public List<Consent> findByConsentType(ConsentType consentType, Pageable pageable);
}
