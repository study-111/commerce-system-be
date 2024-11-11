package study111.commerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study111.commerce.request.GetProductsRequest;
import study111.commerce.response.CommonResponse;
import study111.commerce.response.ProductsResponse;
import study111.commerce.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/products")
    public ResponseEntity<CommonResponse<List<ProductsResponse>>> getProducts(
        GetProductsRequest request,
        Pageable pageable
    ) {
        List<ProductsResponse> products = productService.getProducts(request, pageable);

        return ResponseEntity.ok().body(CommonResponse.of(products));
    }

}
