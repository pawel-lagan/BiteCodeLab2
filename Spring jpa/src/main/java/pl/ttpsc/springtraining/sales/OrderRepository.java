package pl.ttpsc.springtraining.sales;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.ttpsc.springtraining.core.AppRepository;
import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.product.Product;

@Repository
public interface OrderRepository extends AppRepository<Order>, OrderRepositoryCustomized {

    List<Order> findAllByCustomer(Customer customer);

    @Query("SELECT o FROM OrderPosition op INNER JOIN op.order o WHERE op.product = :product AND o.customer = :customer")
    List<Order> findAllByCustomerAndProduct(@Param("customer") Customer customer, @Param("product") Product product);

    List<Order> findAllByCustomer(Customer customer, Sort sort);

    Page<Order> findAllByCustomer(Customer customer, Pageable pageable);

    @Query("SELECT new pl.ttpsc.springtraining.sales.OrderDTO(o.customer.firstName, o.customer.lastName) "
            + "FROM Order o WHERE o.customer = :customer")
    List<OrderDTO> getDTOsByCustomer(@Param("customer") Customer customer);

    @Query("SELECT o.customer.firstName as firstName, o.customer.lastName as lastName "
            + "FROM Order o WHERE o.customer = :customer")
    List<Tuple> getTuplesByCustomer(@Param("customer") Customer customer);

}
