package com.scotchboard.management.controller;

import java.net.URI;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scotchboard.management.model.Consent;
import com.scotchboard.management.services.ServiceBO;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private ServiceBO serviceBO;

	@PostMapping("/{customer_id}/add/consent")
	public ResponseEntity<Consent> createConsent(@PathVariable("customer_id") long customerId, @RequestBody Consent consent) {
		try {
			Consent createdConsent = serviceBO.addCustomerConsent(customerId, consent);
			
			HttpHeaders headers = new HttpHeaders();
			URI locationUri = new URI("/consent/get/" + String.valueOf(createdConsent.getId()));
			headers.setLocation(locationUri);
			
			return new ResponseEntity<>(createdConsent, headers, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{customer_id}/get/consents")
	public ResponseEntity<Set<Consent>> getAllConsents(@PathVariable("customer_id") long customerId) {
		try {
			Set<Consent> consents = serviceBO.getAllConsents(customerId);
			
			if (consents.isEmpty()) {
		        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		      }

		    return new ResponseEntity<>(consents, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
