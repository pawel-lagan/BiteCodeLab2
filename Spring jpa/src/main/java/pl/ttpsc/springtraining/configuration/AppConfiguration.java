package pl.ttpsc.springtraining.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import pl.ttpsc.dataloader.ApplicationDataLoader;
import pl.ttpsc.springtraining.customer.CustomerRepository;
import pl.ttpsc.springtraining.customer.CustomerService;

@Configuration
@Import(ApplicationDataLoader.class)
@EnableTransactionManagement
public class AppConfiguration {
	@Bean
	public CustomerService customerService(CustomerRepository customerRepository) {
		return new CustomerService(customerRepository);
	}

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabase db = new EmbeddedDatabaseBuilder().generateUniqueName(true).setType(EmbeddedDatabaseType.HSQL)
				.setScriptEncoding("UTF-8").ignoreFailedDrops(true).addScript("initdb.sql").build();

		return db;
	}
}