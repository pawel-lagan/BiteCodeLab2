package pl.ttpsc.springtraining.product;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import pl.ttpsc.springtraining.core.AppEntity;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Product implements AppEntity {
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
