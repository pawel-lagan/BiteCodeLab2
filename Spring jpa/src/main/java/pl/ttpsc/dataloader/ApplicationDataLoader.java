package pl.ttpsc.dataloader;

import java.math.BigDecimal;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.customer.CustomerRepository;
import pl.ttpsc.springtraining.product.Product;
import pl.ttpsc.springtraining.product.ProductRepository;
import pl.ttpsc.springtraining.sales.Order;
import pl.ttpsc.springtraining.sales.OrderPosition;
import pl.ttpsc.springtraining.sales.OrderRepository;

public class ApplicationDataLoader implements InitializingBean {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;

	// @PostConstruct
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void prepareAllRequiredData() {
		Product product1 = Product.newInstance("Laptop", "Laptop", BigDecimal.TEN);
		productRepository.save(product1);
		Product product2 = Product.newInstance("Monitor", "Monitor", BigDecimal.valueOf(12, 2));
		productRepository.save(product2);

		Customer customer1 = Customer.newInstance("Jan", "Kowalski", "jan.kowalski@example.com", true);
		customerRepository.save(customer1);
		Customer customer2 = Customer.newInstance("Grzegorz", "Brzęczyszczykiewicz", "gbrzecz@example.com", false);
		customerRepository.save(customer2);

		Order order = Order.newInstance(customer1);
		order.addPosition(OrderPosition.newInstance(product1, BigDecimal.valueOf(15, 2)));
		orderRepository.save(order);

		Order order2 = Order.newInstance(customer2);
		order2.addPosition(OrderPosition.newInstance(product1, BigDecimal.valueOf(13, 2)));
		orderRepository.save(order2);

		Order order3 = Order.newInstance(customer1);
		order3.addPosition(OrderPosition.newInstance(product2, BigDecimal.valueOf(193, 2)));
		orderRepository.save(order3);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		prepareAllRequiredData();
	}

}
