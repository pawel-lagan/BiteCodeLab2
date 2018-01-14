package pl.ttpsc.springtraining.core;

import java.util.Collection;

public interface AppRepository<T extends AppEntity> {
	Collection<T> findAll();

	T save(T entity);
}
