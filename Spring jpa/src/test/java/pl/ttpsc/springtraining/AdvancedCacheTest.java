package pl.ttpsc.springtraining;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.product.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories
@EnableTransactionManagement
public class AdvancedCacheTest {
	private static final String TEST_AAA = "TEST_AAA";
	private @Autowired EntityManager em;

	@Test
	@Transactional
	@Rollback
	public void shouldNotUseCache() throws Exception {
		Statistics stats = em.unwrap(Session.class).getSessionFactory().getStatistics();
		SecondLevelCacheStatistics cacheStatistics = stats.getSecondLevelCacheStatistics(Customer.class.getName());
		long hitCount = cacheStatistics.getHitCount();

		Product p1 = Product.newInstance(TEST_AAA, "aaa", BigDecimal.ONE);
		em.persist(p1);

		em.flush();
		em.clear();

		Product p2 = em.find(Product.class, p1.getId());

		em.flush();
		em.clear();

		Product p3 = em.find(Product.class, p1.getId());

		assertThat(em.getEntityManagerFactory().getCache().contains(Product.class, p1.getId())).isFalse();
		assertThat(cacheStatistics.getHitCount() - hitCount).isEqualTo(0L);
	}

	@Test
	@Transactional
	@Rollback
	public void shouldUseCache() throws Exception {
		Statistics stats = em.unwrap(Session.class).getSessionFactory().getStatistics();
		SecondLevelCacheStatistics cacheStatistics = stats.getSecondLevelCacheStatistics(Customer.class.getName());
		long hitCount = cacheStatistics.getHitCount();

		Customer p1 = Customer.newInstance(TEST_AAA, "aaa", TEST_AAA, true);
		em.persist(p1);

		em.flush();
		em.clear();

		Customer p2 = em.find(Customer.class, p1.getId());

		em.flush();
		em.clear();

		Customer p3 = em.find(Customer.class, p1.getId());

		assertThat(em.getEntityManagerFactory().getCache().contains(Customer.class, p1.getId())).isTrue();
		assertThat(cacheStatistics.getHitCount() - hitCount).isEqualTo(1L);
	}
}
