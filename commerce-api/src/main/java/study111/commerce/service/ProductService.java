package study111.commerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import study111.commerce.repository.ProductQueryRepository;
import study111.commerce.request.GetProductsRequest;
import study111.commerce.response.ProductsResponse;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductQueryRepository productQueryRepository;

    public List<ProductsResponse> getProducts(GetProductsRequest request, Pageable pageable) {
        return productQueryRepository.findProducts(request, pageable);
    }
}
