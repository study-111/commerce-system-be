package study111.commerce.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study111.commerce.domain.Category;
import study111.commerce.domain.Product;
import study111.commerce.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class TempConfig {

    @Bean
    ApplicationRunner initialProducts(ProductRepository productRepository) {
        return args -> {
            var category = Category.builder().name("test").build();
            var products = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                    .name("product " + i)
                    .price(BigDecimal.TEN)
                    .stock(100)
                    .category(category)
                    .build())
                .collect(Collectors.toList());

            productRepository.saveAll(products);
        };
    }
}
