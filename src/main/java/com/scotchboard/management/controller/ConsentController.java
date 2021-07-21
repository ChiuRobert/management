package com.scotchboard.management.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scotchboard.management.model.Consent;
import com.scotchboard.management.model.ConsentType;
import com.scotchboard.management.services.ServiceBO;

@RestController
@RequestMapping("/consent")
public class ConsentController {
	
	private static final int DEFAULT_PAGE_SIZE = 10;
	
	@Autowired
	private ServiceBO serviceBO;

	@GetMapping("/get/{consent_type}")
	public ResponseEntity<Set<Consent>> getAllConsentsByType(@PathVariable("consent_type") ConsentType consentType,
			@RequestParam(value = "page") Integer page,
			@RequestParam(value = "size", required = false) Optional<Integer> size) {
		try {
			Pageable defaultPage = PageRequest.of(page, size.orElse(DEFAULT_PAGE_SIZE));
			Set<Consent> consents = serviceBO.getAllConsentsByType(consentType, defaultPage);

			if (consents.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(consents, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
