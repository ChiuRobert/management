package com.scotchboard.management.services;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scotchboard.management.bo.ConsentBO;
import com.scotchboard.management.bo.CustomerBO;
import com.scotchboard.management.model.Consent;
import com.scotchboard.management.model.ConsentType;
import com.scotchboard.management.model.Customer;

@Service
public class ServiceBO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBO.class);

	@Autowired
	private CustomerBO customerBO;
	
	@Autowired
	private ConsentBO consentBO;
	
	public Consent addCustomerConsent(long customerId, Consent consent) {
		LOGGER.info("Adding consent " + consent + " for customer with id: " + customerId);
		
		Customer customer = customerBO.getCustomer(customerId);
		
		if (customer != null) {
			consent.setCustomer(customer);
			return consentBO.persistConsent(consent);
		} else {
			LOGGER.error("Couldn't add consent becase the customer does not exist.");
			throw new IllegalArgumentException("Customer is null");
		}
	}
	
	public Set<Consent> getAllConsents(long customerId) {
		LOGGER.info("Fetching all consents for customer with id: " + customerId);
		
		Customer customer = customerBO.getCustomer(customerId);
		
		if (customer != null) {
			return consentBO.getAllConsents(customer);
		} else {
			LOGGER.error("Couldn't add consent becase the customer does not exist.");
			throw new IllegalArgumentException("Customer is null");
		}
	}
	
	public Set<Consent> getAllConsentsByType(ConsentType consentType, Pageable pageable) {
		LOGGER.info("Fetching all consents with ConsentType: " + consentType);
		
		return consentBO.getAllConsentsByType(consentType, pageable);
	}
}
