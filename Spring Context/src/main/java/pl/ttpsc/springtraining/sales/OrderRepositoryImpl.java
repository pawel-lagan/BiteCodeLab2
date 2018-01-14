package pl.ttpsc.springtraining.sales;

import org.springframework.stereotype.Repository;

import pl.ttpsc.springtraining.core.SimpleAppRepository;

@Repository
public class OrderRepositoryImpl extends SimpleAppRepository<Order> implements OrderRepository {

}
