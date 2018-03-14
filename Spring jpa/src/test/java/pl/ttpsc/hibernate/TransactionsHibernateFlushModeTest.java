package pl.ttpsc.hibernate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.customer.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { HibernateConfig.class })
@Slf4j
public class TransactionsHibernateFlushModeTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void flushModeSetToManual() throws Exception {
		List<Customer> findAll = customerRepository.findAll();
		assertThat(findAll).hasSize(0);

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.setFlushMode(FlushMode.MANUAL);
		log.debug("Underlying Hibernate session flushmode {}", session.getFlushMode());
		log.debug("step1");

		Customer customer1 = Customer.newInstance("Tester1", "Testowy", "tester.testowy@example.com", true);
		session.persist(customer1);

		log.debug("step2");

		Customer customer2 = Customer.newInstance("Tester1", "Testowy", "tester.testowy@example.com", true);
		session.persist(customer2);

		log.debug("step3");

		Query query = session.createQuery("SELECT c FROM Customer c");

		List<Customer> resultList = query.list();
		log.debug("CNT {}", resultList.size());

		log.debug("Underlying Hibernate session flushmode ####### " + session.getFlushMode());

		// session.clear();
		session.flush();

		transaction.commit();

		log.debug("end");
		findAll = customerRepository.findAll();
		assertThat(findAll).hasSize(2);
	}

	@Transactional
	public void springManagedTransaction() {
		List<Customer> findAll = customerRepository.findAll();
		assertThat(findAll).hasSize(0);

		Customer customer1 = Customer.newInstance("Tester1", "Testowy", "tester.testowy@example.com", true);
		customerRepository.save(customer1);

		Customer customer2 = Customer.newInstance("Tester1", "Testowy", "tester.testowy@example.com", true);
		customerRepository.save(customer2);

		findAll = customerRepository.findAll();
		assertThat(findAll).hasSize(2);
	}

}
