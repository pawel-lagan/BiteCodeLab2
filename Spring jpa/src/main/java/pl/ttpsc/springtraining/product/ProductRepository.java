package pl.ttpsc.springtraining.product;

import org.springframework.stereotype.Repository;

import pl.ttpsc.springtraining.core.AppRepository;

@Repository
public interface ProductRepository extends AppRepository<Product> {

}
