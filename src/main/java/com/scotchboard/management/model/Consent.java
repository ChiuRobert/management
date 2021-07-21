package com.scotchboard.management.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Consent")
public class Consent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ConsentId")
	private long id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="CustomerId", nullable = false)
    private Customer customer;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
    private ConsentType consentType;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
    private ChannelName channelName;
	
	@Column(columnDefinition = "boolean default false")
    private Boolean channelConsent;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ConsentType getConsentType() {
		return consentType;
	}

	public void setConsentType(ConsentType consentType) {
		this.consentType = consentType;
	}

	public ChannelName getChannelName() {
		return channelName;
	}

	public void setChannelName(ChannelName channelName) {
		this.channelName = channelName;
	}

	public Boolean getChannelConsent() {
		return channelConsent;
	}

	public void setChannelConsent(Boolean channelConsent) {
		this.channelConsent = channelConsent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(channelConsent, channelName, consentType, customer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consent other = (Consent) obj;
		return Objects.equals(channelConsent, other.channelConsent) && channelName == other.channelName
				&& consentType == other.consentType && Objects.equals(customer, other.customer);
	}

	@Override
	public String toString() {
		return "Consent [consentType=" + consentType + ", channelName=" + channelName + ", channelConsent="
				+ channelConsent + "]";
	}
}
