package pl.ttpsc.springtraining.sales;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.ttpsc.springtraining.core.AppEntity;
import pl.ttpsc.springtraining.product.Product;

@Getter
@Setter
@EqualsAndHashCode
public class OrderPosition implements AppEntity {
	private Long id;
	private Product product;
	private BigDecimal salesPrice;

	public static OrderPosition newInstance(@NonNull Product product, @NonNull BigDecimal salesPrice) {
		OrderPosition position = new OrderPosition();
		position.product = product;
		position.salesPrice = salesPrice;
		return position;
	}
}
