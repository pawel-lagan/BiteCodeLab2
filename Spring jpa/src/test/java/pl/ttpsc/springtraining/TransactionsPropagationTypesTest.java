package pl.ttpsc.springtraining;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.customer.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories
@EnableTransactionManagement
public class TransactionsPropagationTypesTest {

	private @Autowired CustomerRepository customerRepository;
	private @Autowired TestComponent2 component2;

	@Component
	public static class TestComponent {
		private @Autowired CustomerRepository customerRepository;

		@Transactional(propagation = Propagation.REQUIRES_NEW)
		public void addInNewTxAndSimulateException() {
			Customer customer1 = Customer.newInstance("Tester1", "Testowy", "tester.testowy@example.com", true);
			customerRepository.save(customer1);

			throw new RuntimeException("rollback on exception");
		}

		@Transactional(propagation = Propagation.REQUIRES_NEW)
		public void addInNewTx() {
			Customer customer1 = Customer.newInstance("Tester1", "Testowy", "tester.testowy@example.com", true);
			customerRepository.save(customer1);
		}
	}

	@Component
	public static class TestComponent2 {
		private @Autowired CustomerRepository customerRepository;
		private @Autowired TestComponent component;

		@Transactional(propagation = Propagation.REQUIRED)
		public void simulatedExceptionInNewTx() {
			Customer customer1 = Customer.newInstance("Tester2", "Testowy", "tester.testowy@example.com", true);
			customerRepository.save(customer1);
			try {
				component.addInNewTxAndSimulateException();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Transactional(propagation = Propagation.REQUIRED)
		public void simulatedExceptionInFirstTx() {
			Customer customer1 = Customer.newInstance("Tester2", "Testowy", "tester.testowy@example.com", true);
			customerRepository.save(customer1);
			component.addInNewTx();

			throw new RuntimeException("EeEEE");

		}

	}

	@Test
	@Transactional
	@Rollback
	public void shouldPrintOneCustomer6() throws Exception {
		component2.simulatedExceptionInNewTx();

		Collection<Customer> result = customerRepository.findAllByActive(true);
		assertThat(result).hasSize(2).extracting(Customer::getFirstName).containsExactly("Jan", "Tester2");
	}

	@Test
	public void shouldPrintOneCustomer() throws Exception {
		try {
			component2.simulatedExceptionInFirstTx();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collection<Customer> result = customerRepository.findAllByActive(true);
		assertThat(result).hasSize(2).extracting(Customer::getFirstName).containsExactly("Jan", "Tester1");
	}

}
