package pl.ttpsc.springtraining.customer;

import java.util.Collection;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CustomerService {
	@Getter
	private CustomerRepository repository;

	public Collection<Customer> findAllActive() {
		return repository.findAllByActive(true);
	}

	public Optional<Customer> findByEmail(String email) {
		return repository.findOneByEmail(email);
	}
}
