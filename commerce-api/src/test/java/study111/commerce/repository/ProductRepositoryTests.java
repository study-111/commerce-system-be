package study111.commerce.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import study111.commerce.request.ProductsGetRequest;

import static org.assertj.core.api.Assertions.assertThat;

class ProductRepositoryTests extends AbstractDataJpaTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void findProducts() {
        // given
        var request = new ProductsGetRequest();
        var pageable = PageRequest.of(0, 10);

        // when
        var products = productRepository.findProducts(request, pageable);

        // then
        assertThat(products).hasSize(10);
    }
}
