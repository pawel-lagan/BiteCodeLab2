package pl.ttpsc.springtraining.product;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
public class ProductService {
	@Getter
	private ProductRepository repository;

	public Optional<Product> findByName(String name) {
		return repository.findAll().stream().filter(product -> Objects.equals(product.getName(), name)).findFirst();
	}
}
