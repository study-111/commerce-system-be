package study111.commerce.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import study111.commerce.config.QueryDslConfig;
import study111.commerce.config.TempConfig;
import study111.commerce.request.GetProductsRequest;

import static org.assertj.core.api.Assertions.assertThat;

@Import({TempConfig.class, QueryDslConfig.class})
@DataJpaTest
class ProductQueryRepositoryTests {

    @Autowired
    JPAQueryFactory queryFactory;

    ProductQueryRepository productQueryRepository;

    @BeforeEach
    void beforeEach() {
        this.productQueryRepository = new ProductQueryRepository(queryFactory);
    }

    @Test
    void findProducts() {
        // given
        var request = new GetProductsRequest();
        var pageable = PageRequest.of(0, 10);

        // when
        var products = productQueryRepository.findProducts(request, pageable);

        // then
        assertThat(products).hasSize(10);
    }
}
