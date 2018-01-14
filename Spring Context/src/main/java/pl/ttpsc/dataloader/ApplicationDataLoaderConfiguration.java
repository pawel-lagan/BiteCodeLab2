package pl.ttpsc.dataloader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationDataLoaderConfiguration {

	@Bean
	public ApplicationDataLoader dataLoader() {
		ApplicationDataLoader dataLoader = new ApplicationDataLoader();
		return dataLoader;
	}
}
