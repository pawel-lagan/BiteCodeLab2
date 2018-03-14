package pl.ttpsc.springtraining.product;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import pl.ttpsc.springtraining.core.AppEntity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product implements AppEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private BigDecimal basePrice;

	public static Product newInstance(@NonNull String name, @NonNull String description,
			@NonNull BigDecimal basePrice) {
		Product product = new Product();
		product.name = name;
		product.description = description;
		product.basePrice = basePrice;
		return product;
	}
}
