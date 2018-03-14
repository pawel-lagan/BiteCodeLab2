package pl.ttpsc.springtraining;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.customer.CustomerRepository;

public class ExampleRepositoryTest extends AbstractIntegrationTest implements WithAssertions {

    @Component
    public static class MyRepo  {
        
        public void save(Customer customer) {
        }
        
        public List<Customer> findAll() {
            return null;
        }
        
    }
    
    private @Autowired MyRepo myRepo;
    private @Autowired CustomerRepository customerRepository;

    @Test
    public void shouldAddCustomer() {
        Customer cust = Customer.newInstance("AB", "AC", "abcdefxx", false);
        
        myRepo.save(cust);
        
        Optional<Customer> result = customerRepository.findOneByEmail("abcdefxx");
        assertThat(result).isNotEmpty().contains(cust);
    }
    
    
    @Test
    public void shouldReturnList() {
        List<Customer> result = myRepo.findAll();
        assertThat(result).hasSize(2);
    }
}
