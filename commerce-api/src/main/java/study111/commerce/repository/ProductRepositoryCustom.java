package study111.commerce.repository;

import org.springframework.data.domain.Pageable;
import study111.commerce.request.ProductsGetRequest;
import study111.commerce.response.ProductsResponse;

import java.util.List;

public interface ProductRepositoryCustom {

    List<ProductsResponse> findProducts(ProductsGetRequest request, Pageable pageable);
}
