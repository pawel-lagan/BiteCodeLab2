package pl.ttpsc.springtraining.sales;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.ttpsc.springtraining.customer.Customer;
import pl.ttpsc.springtraining.product.Product;

@Component
public class OrderRepositoryImpl implements OrderRepositoryCustomized {
	@Autowired
	private EntityManager em;

	@Override
	public List<Order> findAllByCustomerAndProductCustom(Customer customer, Product product) {
		TypedQuery<Order> query = em.createQuery(
				"SELECT o FROM OrderPosition op INNER JOIN op.order o WHERE op.product = :product AND o.customer = :customer",
				Order.class);
		query.setParameter("product", product);
		query.setParameter("customer", customer);
		return query.getResultList();
	}

	@Override
	public List<Order> findAllByCustomerAndProductCustom2(Customer customer, Product product) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> mainQuery = cb.createQuery(Order.class);
		ParameterExpression<Customer> customerParam = cb.parameter(Customer.class, "customer");
		ParameterExpression<Product> productParam = cb.parameter(Product.class, "product");

		Root<Order> order = mainQuery.from(Order.class);
		Root<OrderPosition> orderPosition = mainQuery.from(OrderPosition.class);

		mainQuery.select(order);
		Predicate where = cb.and(cb.equal(order, orderPosition.get("order")), cb.and(
				cb.equal(orderPosition.get("product"), productParam), cb.equal(order.get("customer"), customerParam)));

		mainQuery.where(where);

		TypedQuery<Order> query = em.createQuery(mainQuery);
		query.setParameter("product", product);
		query.setParameter("customer", customer);
		return query.getResultList();
	}
	
        public List<OrderDTO> getDTOByCustomer2(Customer customer) {
                TypedQuery<OrderDTO> query = em.createQuery(
                                "SELECT new pl.ttpsc.springtraining.sales.OrderDTO(o.customer.firstName, o.customer.lastName) "
            + "FROM Order o WHERE o.customer = :customer",
                                OrderDTO.class);
                query.setParameter("customer", customer);
                return query.getResultList();
        }

}
