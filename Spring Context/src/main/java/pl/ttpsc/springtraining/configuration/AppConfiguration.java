package pl.ttpsc.springtraining.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pl.ttpsc.dataloader.ApplicationDataLoader;
import pl.ttpsc.dataloader.ApplicationDataLoaderConfiguration;
import pl.ttpsc.springtraining.customer.CustomerRepository;
import pl.ttpsc.springtraining.customer.CustomerRepositoryImpl;
import pl.ttpsc.springtraining.customer.CustomerService;

@Configuration
@Import(ApplicationDataLoaderConfiguration.class)
public class AppConfiguration {

}
