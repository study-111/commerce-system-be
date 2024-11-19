package study111.commerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study111.commerce.domain.Order;
import study111.commerce.domain.OrderProduct;
import study111.commerce.domain.Product;
import study111.commerce.repository.OrderRepository;
import study111.commerce.repository.ProductRepository;
import study111.commerce.request.OrderCreateRequest;
import study111.commerce.request.OrderGetResponse;
import study111.commerce.request.OrdersGetRequest;
import study111.commerce.response.OrdersGetResponse;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long order(@Valid OrderCreateRequest request) {
        var orderProductsRequest = request.getProducts();
        List<Long> productIds = orderProductsRequest.stream().map(OrderCreateRequest.OrderProductCreate::getId).collect(Collectors.toList());
        List<Product> products = productRepository.findAllById(productIds);

        List<OrderProduct> orderProducts = orderProductsRequest.stream().map(req -> {
            var findProduct = products.stream().filter(product -> product.getId().equals(req.getId())).findFirst().orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 상품이 포함되어 있습니다.")
            );

            return OrderProduct.builder()
                .product(findProduct)
                .quantity(req.getQuantity())
                .build();
        }).collect(Collectors.toList());

        Order newOrder = Order.builder()
            .orderProducts(orderProducts)
            .build();

        return orderRepository.save(newOrder).getId();
    }

    public List<OrdersGetResponse> getOrders(Long userId, OrdersGetRequest request) {
        return orderRepository.findOrders(userId, request);
    }

    public OrderGetResponse getOrder(Long id) {
        return null;
    }
}
