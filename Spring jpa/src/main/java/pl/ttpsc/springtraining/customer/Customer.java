package pl.ttpsc.springtraining.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import pl.ttpsc.springtraining.core.AppEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Customer implements AppEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private boolean active;

	public static Customer newInstance(@NonNull String firstName, @NonNull String lastName, @NonNull String email,
			boolean active) {
		Customer customer = new Customer();
		customer.setActive(active);
		customer.setEmail(email);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		return customer;
	}
}
