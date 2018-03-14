package pl.ttpsc.hibernate;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories(basePackages = "pl.ttpsc.springtraining", repositoryImplementationPostfix = "Impl")
@EnableWebMvc
@ComponentScan({ "pl.ttpsc.springtraining", "pl.ttpsc.hibernate" })
public class HibernateConfig {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabase db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.setScriptEncoding("UTF-8").ignoreFailedDrops(true).addScript("initdb.sql").build();
		return db;
	}

	@Bean
	public TransactionTemplate transactionTemplate(DataSource ds) throws IOException {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(transactionManager(ds));
		return transactionTemplate;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactoryBean(DataSource ds) {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(ds);
		factoryBean.setPackagesToScan("pl.ttpsc.springtraining");
		factoryBean.setImplicitNamingStrategy((new SpringImplicitNamingStrategy()));
		factoryBean.setPhysicalNamingStrategy((new SpringPhysicalNamingStrategy()));
		return factoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource ds) throws IOException {
		HibernateTransactionManager txName = new HibernateTransactionManager();
		txName.setDataSource(ds);
		txName.setSessionFactory(sessionFactoryBean(ds).getObject());
		return txName;
	}
}
