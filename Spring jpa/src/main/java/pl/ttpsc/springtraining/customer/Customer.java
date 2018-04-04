package pl.ttpsc.springtraining.customer;

import javax.persistence.Entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import pl.ttpsc.springtraining.core.AbstractVersionedAppEntity;

@Getter
@Setter
@ToString
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Customer extends AbstractVersionedAppEntity {

	private String firstName;
	private String lastName;
	private String email;
	private boolean active;

	public Customer() {
		super();
	}

	public Customer(boolean generateUuid) {
		super(generateUuid);
	}

	public static Customer newInstance(@NonNull String firstName, @NonNull String lastName, @NonNull String email,
			boolean active) {
		Customer customer = new Customer(true);
		customer.setActive(active);
		customer.setEmail(email);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		return customer;
	}
}
