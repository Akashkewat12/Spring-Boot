package HibernetAndDataJPA.W_3;

import HibernetAndDataJPA.W_3.entites.ProductEntity;
import HibernetAndDataJPA.W_3.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class HibernetAndDataJpaW3ApplicationTests {

    @Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

		@Test
	void testRepository() {
		ProductEntity productEntity=ProductEntity.builder()
				.sku("nestle123")
				.title("nest chocolate")
				.price(BigDecimal.valueOf(123.12))
				.quantity(12)
				.build();

		ProductEntity savedProductEntity=productRepository.save(productEntity);
	}

	@Test
	void getRepository() {
		//List<ProductEntity> entities= productRepository.findByTitleLike("%c%");

		List<ProductEntity> entities=productRepository.findByTitleContainingIgnoreCase("choch", null);
		System.out.println(entities);
	}

	@Test
	void getSingleFomRepository() {
		Optional<ProductEntity> productEntity =productRepository
				.findByTitleAndPrice("parle734 22",BigDecimal.valueOf(12.4));
		productEntity.ifPresent(System.out::println);
	}

}
