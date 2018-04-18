package com.psk.bank.repository;

import java.util.List;

public interface BankRepository<T, ID> {
	void save(T object);
	T findOne(ID id);
	T deleteOne(ID id);
	List<T> findAll();
}
