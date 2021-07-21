package com.scotchboard.management.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CustomerId")
	private long id;
	
	@Column(name = "FirstName", nullable = false)
	private String firstName;
	
	@Column(name = "LastName", nullable = false)
	private String lastName;
	
	@Column(name="PersonalNumber", nullable = false, unique = true)
	private int personalNumber;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(int personalNumber) {
		this.personalNumber = personalNumber;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, personalNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& personalNumber == other.personalNumber;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", personalNumber=" + personalNumber
				+ "]";
	}
}
