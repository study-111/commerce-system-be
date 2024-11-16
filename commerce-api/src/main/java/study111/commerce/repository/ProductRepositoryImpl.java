package study111.commerce.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import study111.commerce.request.ProductsGetRequest;
import study111.commerce.response.ProductsResponse;

import java.math.BigDecimal;
import java.util.List;

import static com.querydsl.core.types.Projections.constructor;
import static study111.commerce.domain.QCategory.category;
import static study111.commerce.domain.QProduct.product;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductsResponse> findProducts(ProductsGetRequest request, Pageable pageable) {
        return queryFactory.select(constructor(ProductsResponse.class, product))
            .from(product)
            .join(product.category, category)
            .where(
                containsName(request.getName()),
                goeMinPrice(request.getMinPrice()),
                loeMaxPrice(request.getMaxPrice())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }

    private BooleanExpression loeMaxPrice(BigDecimal maxPrice) {
        return maxPrice != null ? product.price.loe(maxPrice) : null;
    }

    private BooleanExpression goeMinPrice(BigDecimal minPrice) {
        return minPrice != null ? product.price.goe(minPrice) : null;
    }

    private BooleanExpression containsName(String name) {
        return name != null ? product.name.contains(name) : null;
    }
}
