package pl.ttpsc.hibernate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pl.ttpsc.ParallelTransactionSimulation;
import pl.ttpsc.springtraining.customer.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HibernateConfig.class)
public class TransactionsIsolationLevelTest {
	private static final String AAAA = "AAAA";
	@Autowired
	private TransactionsComittedDemo demo;

	@Autowired
	private TransactionsSerializableDemo demo2;

	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void shouldSeeCommitedChanges() throws Exception {
		demo.create();
		List<Customer> findAll = demo.run(demo);

		List<Customer> customers = demo.getAllCustomers();
		assertThat(findAll).hasSize(2).extracting(Customer::getLastName).contains(AAAA, "TestowyXX");
	}

	@Test
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	public void shouldSeeSerialized() throws Exception {
		demo.create();
		List<Customer> findAll = demo2.run(demo2);
		assertThat(findAll).hasSize(1).extracting(Customer::getLastName).contains("Kowalski");
	}

	@Slf4j
	@Component
	public static class TransactionsComittedDemo extends ParallelTransactionSimulation {
		@Autowired
		private SessionFactory sf;

		@Transactional
		public void create() {
			Session currentSession = sf.getCurrentSession();
			Customer customer1 = Customer.newInstance("jan", "Kowalski", "Kowalski@example.com", true);
			currentSession.persist(customer1);
		}

		@Transactional
		public List<Customer> getAllCustomers() throws InterruptedException {
			Session currentSession = sf.getCurrentSession();
			return currentSession.createQuery("SELECT c From Customer c").setCacheable(false).list();
		}

		@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
		public void doT1() throws InterruptedException {
			log.info("T1 START");
			Session currentSession = sf.getCurrentSession();
			log.info("T1 Q1");
			List list = currentSession.createQuery("SELECT c From Customer c").setCacheable(false).list();
			log.info("T1 Q1 DONE");
			Thread.sleep(10000);
			log.info("T1 Q2");
			List list2 = currentSession.createQuery("SELECT c From Customer c").setCacheable(false).list();
			log.info("T1 FINISH");
			assertThat(list).hasSize(1);
			assertThat(list2).hasSize(2);
			result = list2;
		}

		@Transactional(propagation = Propagation.REQUIRES_NEW)
		public void doT2() throws InterruptedException {
			log.info("T2 START");
			Session currentSession = sf.getCurrentSession();
			Thread.sleep(2000);
			Customer customer1 = Customer.newInstance("Tester", "TestowyXX", "tester.testowy@example.com", true);
			currentSession.persist(customer1);
			Customer v = currentSession.get(Customer.class, 1L);
			v.setLastName(AAAA);
			currentSession.flush();
			log.info("T2 FINISH");
		}
	}

	@Component
	public static class TransactionsSerializableDemo extends ParallelTransactionSimulation {
		@Autowired
		private SessionFactory sf;

		@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
		public void doT1() throws InterruptedException {
			Session currentSession = sf.getCurrentSession();
			List list = currentSession.createQuery("SELECT c From Customer c").list();
			Thread.sleep(6000);
			result = currentSession.createQuery("SELECT c From Customer c").list();
			assertThat(result).hasSameElementsAs(list);
		}

		@Transactional(propagation = Propagation.REQUIRES_NEW)
		public void doT2() throws InterruptedException {
			Session currentSession = sf.getCurrentSession();
			Thread.sleep(2000);
			Customer customer1 = Customer.newInstance("Tester", "TestowyXX", "tester.testowy@example.com", true);
			currentSession.persist(customer1);
			Customer v = currentSession.get(Customer.class, 1L);
			v.setLastName(AAAA);
		}
	}

}
