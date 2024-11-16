package study111.commerce.repository;

import study111.commerce.request.OrdersGetRequest;
import study111.commerce.response.OrdersGetResponse;

import java.util.List;

public interface OrderRepositoryCustom {

    List<OrdersGetResponse> findOrders(Long userId, OrdersGetRequest request);
}
