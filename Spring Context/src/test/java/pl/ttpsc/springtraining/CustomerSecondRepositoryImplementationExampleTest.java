package pl.ttpsc.springtraining;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import pl.ttpsc.springtraining.CustomerSecondRepositoryImplementationExampleTest.TestSpecificConfig;
import pl.ttpsc.springtraining.core.AppRepository;
import pl.ttpsc.springtraining.core.BeanUtil;
import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.customer.CustomerRepository;
import pl.ttpsc.springtraining.customer.CustomerSecondRepositoryImpl;
import pl.ttpsc.springtraining.customer.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringTrainingApplication.class, TestSpecificConfig.class })
public class CustomerSecondRepositoryImplementationExampleTest {

	@Configuration
	public static class TestSpecificConfig {
	}

	private @Autowired CustomerService customerService;
	private @Autowired CustomerSecondRepositoryImpl customerRepository;

	@Test
	public void shouldHaveSameInstanceOfCustomerRepository() {
		AppRepository<Customer> repository = customerService.getRepository();
		assertThat(repository).isSameAs(customerRepository);
	}

	@Test
	public void shouldReturnInstanceOf() {
		CustomerSecondRepositoryImpl service = BeanUtil.getService(CustomerSecondRepositoryImpl.class);
		assertThat(service).isSameAs(customerRepository);
	}

	@Test
	public void shouldReturnInstanceOfByGenericInterfave() {
		AppRepository<Customer> serviceByResolvable = BeanUtil.getServiceByGeneric(AppRepository.class, Customer.class);
		assertThat(serviceByResolvable).isSameAs(customerRepository);
	}
}
