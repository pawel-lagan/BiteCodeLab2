package com.psk.bank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psk.bank.model.Account;
import com.psk.bank.model.AccountEntity;
import com.psk.bank.repository.AccountRepository;


@RestController
public class AccountController {

	@Autowired
	AccountRepository accountRepository;

	public AccountController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@GetMapping("/getAccountWithGivenId/{id}")
	public Account getAccountWithGivenId(@PathVariable String id) {

		return accountRepository.findOne(id);
	}

	@DeleteMapping("/deleteAccountWithGivenId/{id}")
	public Account deleteAccountWithGivenId(@PathVariable String id) {

		return accountRepository.deleteOne(id);
	}

	@PostMapping("/addAccount")
	public String addAccount(@RequestBody AccountEntity account) {

		accountRepository.save(account);

		return "Account added";

	}

}
