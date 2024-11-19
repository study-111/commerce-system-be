package study111.commerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study111.commerce.domain.User;
import study111.commerce.request.OrderCreateRequest;
import study111.commerce.request.OrderGetResponse;
import study111.commerce.request.OrdersGetRequest;
import study111.commerce.response.CommonResponse;
import study111.commerce.response.OrdersGetResponse;
import study111.commerce.service.OrderService;

import jakarta.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    ResponseEntity<CommonResponse<Long>> order(@Valid @RequestBody OrderCreateRequest request) {
        Long orderId = orderService.order(request);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(CommonResponse.of(orderId));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    ResponseEntity<CommonResponse<List<OrdersGetResponse>>> getOrders(Authentication user, OrdersGetRequest request) {
        var orders = orderService.getOrders(((User) user.getPrincipal()).getId(), request);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(CommonResponse.of(orders));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    ResponseEntity<CommonResponse<OrderGetResponse>> getOrder(Long id) {
        var order = orderService.getOrder(id);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(CommonResponse.of(order));
    }
}
