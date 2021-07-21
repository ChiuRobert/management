package com.scotchboard.management.util;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scotchboard.management.model.ChannelName;
import com.scotchboard.management.model.Consent;
import com.scotchboard.management.model.ConsentType;
import com.scotchboard.management.model.Customer;
import com.scotchboard.management.repository.ConsentRepository;
import com.scotchboard.management.repository.CustomerRepository;

@Component
public final class Generator {

	private static final Logger LOGGER = LoggerFactory.getLogger(Generator.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ConsentRepository consentRepository;
	
	public void generate() {
		LOGGER.info("Generating started");
		
		Customer customer = new Customer();
		customer.setFirstName("Ceapa");
		customer.setLastName("Rosie");
		customer.setPersonalNumber(1234);
		
		Customer customer2 = new Customer();
		customer2.setFirstName("George");
		customer2.setLastName("Bush");
		customer2.setPersonalNumber(13225);
		
		Consent consent = new Consent();
		consent.setChannelConsent(true);
		consent.setChannelName(ChannelName.EMAIL);
		consent.setConsentType(ConsentType.MARKETING);
		
		Consent consent2 = new Consent();
		consent2.setChannelConsent(false);
		consent2.setChannelName(ChannelName.PHONE);
		consent2.setConsentType(ConsentType.ESSENTIALS);
		
		Set<Consent> consents = new HashSet<>();
		consents.add(consent);
		consents.add(consent2);
		
		customer = customerRepository.save(customer);
		customerRepository.save(customer2);
		
		consent.setCustomer(customer);
		consent2.setCustomer(customer);
		
		consentRepository.save(consent);
		consentRepository.save(consent2);
		
		LOGGER.info("Generating ended");
	}
}
