package com.psk.bank.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.psk.bank.model.User;

@Repository
public class UserRepository implements BankRepository<User, Long> {

	private Map<Long, User> storage = new HashMap<>();
	private Long idSeq = 0L;
	
	public UserRepository() {

		save(new User(nextId(), "User1", LocalDateTime.parse("2017-01-02T21:32:00")));
		save(new User(nextId(), "User2", LocalDateTime.parse("2017-02-02T21:32:00")));
		save(new User(nextId(), "User3", LocalDateTime.parse("2017-03-02T21:32:00")));
	}

	@Override
	public void save(User object) {
		storage.put(object.getId(), object);

	}

	@Override
	public User findOne(Long id) {
		return storage.get(id);
	}

	@Override
	public User deleteOne(Long id) {
		return storage.remove(id);
	}

	@Override
	public List<User> findAll() {
		return storage.values().stream().collect(Collectors.toList());
	}
	
	public Long nextId() {
        return idSeq++;
    }

}
