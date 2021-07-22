package com.scotchboard.management.bo;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scotchboard.management.model.ChannelName;
import com.scotchboard.management.model.Consent;
import com.scotchboard.management.model.ConsentType;
import com.scotchboard.management.model.Customer;
import com.scotchboard.management.repository.ConsentRepository;

@Service
public class ConsentBO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsentBO.class);

	@Autowired 
	private ConsentRepository consentRepository;
	
	public Consent persistConsent(Consent consent) {
		if (!consentRepository.findAll().contains(consent)) {
			LOGGER.info("Persising consent: " + consent);
			
			return consentRepository.save(consent);
		}
		
		LOGGER.info("Consent already exists");
		return consent;
		
	}
	
	public Set<Consent> getAllConsents(Customer customer) {
		LOGGER.info("Getting all consents for customer: " + customer);
		
		Set<Consent> consents = new HashSet<>(consentRepository.findByCustomer(customer));
		
		for (var consentType : ConsentType.values()) {
			for (var channelName : ChannelName.values()) {
				boolean exists = consents.stream().anyMatch(
						consent -> consent.getConsentType() == consentType && consent.getChannelName() == channelName);
				
				if (!exists) {
					Consent tempConsent = new Consent();
					tempConsent.setCustomer(customer);
					tempConsent.setConsentType(consentType);
					tempConsent.setChannelName(channelName);
					tempConsent.setChannelConsent(false);
					consents.add(tempConsent);
				}
			}
		}
		
		return consents;
	}
	
	public Set<Consent> getAllConsentsByType(ConsentType consentType, Pageable pageable) {
		return new HashSet<>(consentRepository.findByConsentType(consentType, pageable));
	}
}
