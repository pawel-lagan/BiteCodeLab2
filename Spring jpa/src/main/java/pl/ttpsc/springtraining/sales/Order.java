package pl.ttpsc.springtraining.sales;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import pl.ttpsc.springtraining.core.AppEntity;
import pl.ttpsc.springtraining.customer.Customer;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order implements AppEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Customer customer;

	private OrderStatus status;
	private LocalDateTime tsCreated;
	private LocalDateTime tsModified;

	public static enum OrderStatus {
		OPEN, READY, SENT, FINISHED, CANCELED
	}

	@Setter(AccessLevel.PRIVATE)
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderPosition> positions;

	public static Order newInstance(@NonNull Customer customer) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setStatus(OrderStatus.OPEN);
		order.setTsCreated(LocalDateTime.now());
		order.positions = new HashSet<>();
		return order;
	}

	public Set<OrderPosition> getPositions() {
		return Collections.unmodifiableSet(positions);
	}

	public OrderPosition addPosition(@NonNull OrderPosition position) {
		positions.add(position);
		position.setOrder(this);
		return position;
	}

	public OrderPosition removePosition(@NonNull OrderPosition position) {
		positions.remove(position);
		return position;
	}
}
