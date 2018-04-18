package com.psk.bank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psk.bank.model.Account;
import com.psk.bank.model.AccountEntity;
import com.psk.bank.repository.AccountRepository;


@Controller
public class AccountController {
    

	@Autowired
	AccountRepository accountRepository;
	
	public AccountController (AccountRepository accountRepository) {
		this.accountRepository=accountRepository;
	}
	
	
	@GetMapping("/getAccountWithGivenId/{id}")
	@ResponseBody
	public Account getAccountWithGivenId(@PathVariable String id){
		
		return accountRepository.findOne(id);
	}
	
	@DeleteMapping("/deleteAccountWithGivenId/{id}")
	@ResponseBody
	public Account deleteAccountWithGivenId(@PathVariable String id){
		
		return accountRepository.deleteOne(id);
	}
	
	@PostMapping("/addAccount")
	@ResponseBody
	public String addAccount(@RequestBody AccountEntity account){
		
		accountRepository.save(account);
		
		return "Account added";
		
	}
	
}
