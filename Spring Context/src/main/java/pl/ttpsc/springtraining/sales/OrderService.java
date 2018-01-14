package pl.ttpsc.springtraining.sales;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

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
		return orderRepository.findAll().stream().filter(order -> Objects.equals(order.getCustomer(), customer))
				.collect(Collectors.toSet());
	}

	public Collection<Order> findAllByCustomerAndProduct(Customer customer, Product product) {
		return findAllByCustomer(customer).stream()
				.filter(order -> order.getPositions().stream()
						.filter(position -> Objects.equals(position.getProduct(), product)).findAny().isPresent())
				.collect(Collectors.toSet());
	}

	public Order createNewOrder(@NonNull Customer customer) {
		Order order = Order.newInstance(customer);
		orderRepository.save(order);
		return order;
	}

	public Order orderSingleProduct(@NonNull Customer customer, @NonNull Product product) {
		Order order = createNewOrder(customer);
		order.addPosition(OrderPosition.newInstance(product, product.getBasePrice()));
		return order;
	}

}
