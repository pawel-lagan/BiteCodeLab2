package pl.ttpsc.springtraining.sales;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import pl.ttpsc.springtraining.core.AppEntity;
import pl.ttpsc.springtraining.product.Product;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class OrderPosition implements AppEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Product product;

	@ManyToOne
	private Order order;

	private BigDecimal salesPrice;

	public static OrderPosition newInstance(@NonNull Product product, @NonNull BigDecimal salesPrice) {
		OrderPosition position = new OrderPosition();
		position.product = product;
		position.salesPrice = salesPrice;
		return position;
	}
}
