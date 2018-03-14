package pl.ttpsc.springtraining.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import pl.ttpsc.springtraining.core.AppRepository;

@Repository
public interface CustomerRepository extends AppRepository<Customer> {
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	@Query("select c from Customer c")
	List<Customer> findAllUncommited();

	List<Customer> findAllByActive(boolean active);

	Optional<Customer> findOneByEmail(String email);
}
