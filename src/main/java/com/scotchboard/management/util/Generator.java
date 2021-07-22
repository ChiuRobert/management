package com.scotchboard.management.util;

import java.util.Arrays;

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
		
		setupCustomer1();
		setupCustomer2();
		setupCustomer3();
		
		LOGGER.info("Generating ended");
	}
	
	private void setupCustomer1() {
		Customer customer = new Customer();
		customer.setFirstName("Ceapa");
		customer.setLastName("Rosie");
		customer.setPersonalNumber(1234);
		
		Consent consent1 = new Consent();
		consent1.setConsentType(ConsentType.ESSENTIALS);
		consent1.setChannelName(ChannelName.EMAIL);
		consent1.setChannelConsent(true);
		
		Consent consent2 = new Consent();
		consent2.setConsentType(ConsentType.MARKETING);
		consent2.setChannelName(ChannelName.PHONE);
		consent2.setChannelConsent(false);
		
		Consent consent3 = new Consent();
		consent3.setConsentType(ConsentType.OTHERS);
		consent3.setChannelName(ChannelName.PHONE);
		consent3.setChannelConsent(true);
		
		Customer persistedCustomer = customerRepository.save(customer);
		
		Arrays.asList(consent1, consent2, consent3).forEach(consent -> {
			consent.setCustomer(persistedCustomer);
			consentRepository.save(consent);
		});
	}
	
	private void setupCustomer2() {
		Customer customer = new Customer();
		customer.setFirstName("George");
		customer.setLastName("Bush");
		customer.setPersonalNumber(13225);
		
		Consent consent1 = new Consent();
		consent1.setConsentType(ConsentType.ESSENTIALS);
		consent1.setChannelName(ChannelName.EMAIL);
		consent1.setChannelConsent(true);
		
		Consent consent2 = new Consent();
		consent2.setConsentType(ConsentType.OTHERS);
		consent2.setChannelName(ChannelName.SOCIAL);
		consent2.setChannelConsent(true);
		
		Customer persistedCustomer = customerRepository.save(customer);
		
		Arrays.asList(consent1, consent2).forEach(consent -> {
			consent.setCustomer(persistedCustomer);
			consentRepository.save(consent);
		});
	}
	
	private void setupCustomer3() {
		Customer customer = new Customer();
		customer.setFirstName("Ion");
		customer.setLastName("Tiriac");
		customer.setPersonalNumber(25567);
		
		Consent consent1 = new Consent();
		consent1.setConsentType(ConsentType.MARKETING);
		consent1.setChannelName(ChannelName.PHONE);
		consent1.setChannelConsent(true);
		
		Consent consent2 = new Consent();
		consent2.setConsentType(ConsentType.MARKETING);
		consent2.setChannelName(ChannelName.SMS);
		consent2.setChannelConsent(true);
		
		Customer persistedCustomer = customerRepository.save(customer);
		
		Arrays.asList(consent1, consent2).forEach(consent -> {
			consent.setCustomer(persistedCustomer);
			consentRepository.save(consent);
		});
	}
}
