package pl.ttpsc.springtraining.sales;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.Tuple;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.ttpsc.springtraining.AbstractIntegrationTest;
import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.customer.CustomerService;

public class DTOExampleTest extends AbstractIntegrationTest {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderRepository orderRepository;

	private Customer customer;

	@Before
	public void before() {
		customer = customerService.findByEmail("jan.kowalski@example.com").orElse(null);
	}

	@Test
	public void shouldReturnDTO() {
	}

	@Test
	public void shouldReturnTuple() {
	}
}
