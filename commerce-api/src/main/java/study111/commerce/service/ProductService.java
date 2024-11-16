package study111.commerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import study111.commerce.repository.ProductRepository;
import study111.commerce.request.ProductsGetRequest;
import study111.commerce.response.ProductsResponse;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductsResponse> getProducts(ProductsGetRequest request, Pageable pageable) {
        return productRepository.findProducts(request, pageable);
    }
}
