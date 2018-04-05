package pl.ttpsc.springtraining.sales;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.product.Product;

@NoRepositoryBean
public interface OrderRepositoryCustomized {
	List<Order> findAllByCustomerAndProductCustom(Customer customer, Product product);

	List<Order> findAllByCustomerAndProductCustom2(Customer customer, Product product);
	
	public List<OrderDTO> getDTOByCustomer2(Customer customer);
}
