package pl.ttpsc.springtraining.sales;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import pl.ttpsc.springtraining.AbstractIntegrationTest;
import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.customer.CustomerService;
import pl.ttpsc.springtraining.product.Product;
import pl.ttpsc.springtraining.product.ProductService;

@Rollback
public class OrderServiceTest extends AbstractIntegrationTest {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
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
	@Transactional
	public void newOrderShouldBePresisted() {
		Order order = createTestOrder();

		Collection<Order> result = orderService.findAllByCustomerAndProduct(customer, product);
		assertThat(result).contains(order).hasSize(2);
	}

	private Order createTestOrder() {
		Order order = orderService.createNewOrder(customer);
		order.addPosition(OrderPosition.newInstance(product, BigDecimal.ONE));
		return order;
	}

}
