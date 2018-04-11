package com.psk.bank.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.psk.bank.model.Account;
import com.psk.bank.model.Transaction;

@Repository
public class TransactionRepository implements BankRepository<Transaction, Long> {
    
    private Map<Long, Transaction> storage = new HashMap<>();
    private Long idSeq = 1L;

    public TransactionRepository() {
    }
    
    @Override
    public void save(Transaction object) {
        storage.put(object.getId(), object);
    }

    @Override
    public Transaction findOne(Long id) {
            return storage.get(id);
    }

    @Override
    public List<Transaction> findAll() {
            return storage.values().stream().collect(Collectors.toList());
    }

    public List<Transaction> findAllByAccount(Account account) {
        if(account == null) {
            return Collections.emptyList();
        }
        return storage.values().stream().filter(t -> t.getAccount().equals(account)).collect(Collectors.toList());
    }
    
    public Long nextId() {
        return idSeq++;
    }

	@Override
	public Transaction deleteOne(Long id) {
		return null;
	}
    

}
