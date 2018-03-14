package pl.ttpsc.springtraining.sales;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.product.Product;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	public Collection<Order> findAllByCustomer(Customer customer) {
		return orderRepository.findAllByCustomer(customer);
	}

	public Collection<Order> findAllByCustomerAndProduct(Customer customer, Product product) {
		return orderRepository.findAllByCustomerAndProduct(customer, product);
	}

	@Transactional
	public Order createNewOrder(@NonNull Customer customer) {
		Order order = Order.newInstance(customer);
		orderRepository.save(order);
		return order;
	}

	@Transactional
	public Order orderSingleProduct(@NonNull Customer customer, @NonNull Product product) {
		Order order = createNewOrder(customer);
		order.addPosition(OrderPosition.newInstance(product, product.getBasePrice()));
		return order;
	}

}
