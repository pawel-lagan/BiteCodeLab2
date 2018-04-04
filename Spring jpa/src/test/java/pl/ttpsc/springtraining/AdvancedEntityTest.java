package pl.ttpsc.springtraining;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.customer.CustomerRepository;
import pl.ttpsc.springtraining.product.Product;
import pl.ttpsc.springtraining.product.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories
@EnableTransactionManagement
public class AdvancedEntityTest {
	private static final String TEST_AAA = "TEST_AAA";
	private @Autowired ProductRepository productRepository;
	private @Autowired CustomerRepository customerRepository;
	private @Autowired EntityManager em;

	private Set<Product> products = new HashSet<>();
	private Set<Customer> customers = new HashSet<>();

	@Test
	@Transactional
	@Rollback
	public void shouldNotFindP2InSet() throws Exception {
		Product p1 = Product.newInstance(TEST_AAA, "aaa", BigDecimal.ONE);
		products.add(p1);

		assertThat(products).contains(p1);

		productRepository.save(p1);

		assertThat(products).contains(p1);

		em.clear();
		em.flush();

		Product p2 = productRepository.getOne(p1.getId());
		assertThat(p2.getName()).isEqualTo(TEST_AAA);

		assertThat(products).doesNotContain(p2);
	}

	@Test
	@Transactional
	@Rollback
	public void shouldFindP2InSet() throws Exception {
		Customer p1 = Customer.newInstance(TEST_AAA, "aaa", TEST_AAA, true);
		customers.add(p1);

		customerRepository.save(p1);

		assertThat(customers).contains(p1);

		em.clear();
		em.flush();

		Customer p2 = customerRepository.getOne(p1.getId());
		assertThat(p2.getFirstName()).isEqualTo(TEST_AAA);

		assertThat(customers).contains(p2);
	}
}
