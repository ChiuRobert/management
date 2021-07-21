package com.scotchboard.management.bo;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scotchboard.management.model.Customer;
import com.scotchboard.management.repository.CustomerRepository;

@Service
public class CustomerBO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerBO.class);

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer getCustomer(long customerId) {
		LOGGER.info("Returning customer with id: " + customerId);
		
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		if (customer.isPresent()) {
			return customer.get();
		}
		
		LOGGER.error("Customer is null");
		return null;
	}
}
