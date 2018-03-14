package pl.ttpsc.springtraining.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AppRepository<T extends AppEntity> extends JpaRepository<T, Long> {
}