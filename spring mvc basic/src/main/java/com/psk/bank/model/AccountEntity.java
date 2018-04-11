package com.psk.bank.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class AccountEntity implements Account {
	private String accountNumber;
	private String firstName;
	private String lastName;
	private Boolean active;
	private LocalDateTime creationTs;

	public static AccountEntity newInstance(String accountNumber, String firstName, String lastName, Boolean active,
			LocalDateTime creationTs) {
		AccountEntity entity = new AccountEntity();
		entity.setAccountNumber(accountNumber);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		entity.setActive(active);
		entity.setCreationTs(creationTs);
		return entity;
	}


	AccountEntity() {
	};

	@Override
	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public Boolean isActive() {
		return active;
	}

	@Override
	public LocalDateTime getCreationTs() {
		return this.creationTs;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setCreationTs(LocalDateTime creatinonTs) {
		this.creationTs = creatinonTs;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "AccountEntity [accountNumber=" + accountNumber + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", active=" + active + ", creationTs=" + creationTs + "]";
	}
	
	
}
