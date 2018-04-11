package com.psk.bank.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.psk.bank.model.Account;
import com.psk.bank.model.AccountEntity;

@Repository
public class AccountRepository  implements BankRepository<Account, String>{

        private Map<String, Account> storage = new HashMap<>();
        
        public AccountRepository() {
        
            save(AccountEntity.newInstance("ABC1","Jan","Kowalski",true, LocalDateTime.parse("2017-01-02T21:32:00")));
            save(AccountEntity.newInstance("ABC2","Tomasz","Kot",true, LocalDateTime.parse("2017-01-02T21:32:00")));
            save(AccountEntity.newInstance("ABC3","Jan","Kowalski",true, LocalDateTime.parse("2017-01-02T21:32:00")));
        }
    
	@Override
	public void save(Account object) {
	    storage.put(object.getAccountNumber(), object);
	}

	@Override
	public Account findOne(String accountNumber) {
		return storage.get(accountNumber);
	}

	@Override
	public List<Account> findAll() {
		return storage.values().stream().collect(Collectors.toList());
	}

	@Override
	public Account deleteOne(String id) {
		return storage.remove(id);
	}


}
