package pl.ttpsc.springtraining.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.NonNull;

public class CustomerSecondRepositoryImpl implements CustomerRepository {
	private Map<Long, Customer> store = new HashMap<>();
	private long idx;

	@Override
	public Customer save(@NonNull Customer entity) {
		if (!store.containsKey(entity.getId())) {
			entity.setId(idx++);
			store.put(entity.getId(), entity);
		}
		return entity;
	}

	@Override
	public Collection<Customer> findAll() {
		return store.values();
	}

}
