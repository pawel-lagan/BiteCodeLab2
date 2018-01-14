package pl.ttpsc.springtraining.product;

import org.springframework.stereotype.Repository;

import pl.ttpsc.springtraining.core.SimpleAppRepository;

@Repository
public class ProductRepositoryImpl extends SimpleAppRepository<Product> implements ProductRepository {

}
