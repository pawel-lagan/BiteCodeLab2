package pl.ttpsc.springtraining.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import lombok.NonNull;

public class SimpleAppRepository<T extends AppEntity> implements AppRepository<T> {
	private Set<T> store = new HashSet<>();
	private long idx;

	@Override
	public Collection<T> findAll() {
		return new HashSet<>(store);
	}

	@Override
	public T save(@NonNull T entity) {
		if (!store.contains(entity)) {
			entity.setId(idx++);
		}
		store.add(entity);
		return entity;
	}

}
