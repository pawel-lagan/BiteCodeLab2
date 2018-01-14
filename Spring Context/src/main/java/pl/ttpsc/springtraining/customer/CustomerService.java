package pl.ttpsc.springtraining.customer;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CustomerService {
	@Getter
	private CustomerRepository repository;

	public Collection<Customer> findAllActive() {
		return repository.findAll().stream().filter(Customer::isActive).collect(Collectors.toSet());
	}

	public Optional<Customer> findByEmail(String email) {
		return repository.findAll().stream().filter(customer -> Objects.equals(email, customer.getEmail())).findFirst();
	}
}
