package pl.ttpsc.springtraining;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.customer.CustomerService;
import pl.ttpsc.springtraining.sales.Order;
import pl.ttpsc.springtraining.sales.OrderService;

/**
 * http://localhost:8080/console/
 * 
 * jdbc:h2:mem:testdb
 * 
 * @author pawel
 *
 */

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pl.ttpsc.springtraining", repositoryImplementationPostfix = "Impl")
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan({ "pl.ttpsc.springtraining" })
public class SpringTrainingApplication {

	private @Autowired CustomerService customerService;
	private @Autowired OrderService orderService;

	private void showActiveCustomers() {
		System.out.println("Customers");
		getActiveCustomers().forEach(customer -> {
			System.out.println(customer);
			getCustomerOrders(customer).forEach(order -> {
				System.out.println(order);
			});
		});
	}

	Collection<Order> getCustomerOrders(Customer customer) {
		return orderService.findAllByCustomer(customer);
	}

	Collection<Customer> getActiveCustomers() {
		return customerService.findAllActive();
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			showActiveCustomers();
		};
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication app = new SpringApplication(SpringTrainingApplication.class);
		app.run();
	}
}
