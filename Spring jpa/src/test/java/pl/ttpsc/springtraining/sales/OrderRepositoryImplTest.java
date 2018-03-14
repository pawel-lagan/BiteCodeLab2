package pl.ttpsc.springtraining.sales;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.ttpsc.springtraining.AbstractIntegrationTest;
import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.customer.CustomerService;
import pl.ttpsc.springtraining.product.Product;
import pl.ttpsc.springtraining.product.ProductService;

public class OrderRepositoryImplTest extends AbstractIntegrationTest {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductService productService;

	private Customer customer;
	private Product product;

	@Before
	public void before() {
		customer = customerService.findByEmail("jan.kowalski@example.com").orElse(null);
		product = productService.findByName("Monitor").orElse(null);
	}
	
	@Test
        public void orderOps() {
	    fail("implement experiments with jpa");
	}
	
	@Test
        public void orderFetchAtOnce() {
	    fail("implement experiments with jpa");
	}

	@Test
	public void newOrderShouldBePresisted() {
		Order order = createTestOrder();
		
		Collection<Order> result = orderRepository.findAllByCustomerAndProductCustom(customer, product);
		assertThat(result).contains(order).hasSize(2);
	}

	@Test
	public void newOrderShouldBePresisted2() {
		Order order = createTestOrder();

		Collection<Order> result = orderRepository.findAllByCustomerAndProductCustom2(customer, product);
		assertThat(result).contains(order).hasSize(2);
	}

	private Order createTestOrder() {
		Order order = orderService.createNewOrder(customer);
		order.addPosition(OrderPosition.newInstance(product, BigDecimal.ONE));
		orderRepository.save(order);
		return order;
	}
}
