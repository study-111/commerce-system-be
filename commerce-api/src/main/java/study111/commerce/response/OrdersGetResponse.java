package study111.commerce.response;

import lombok.Getter;
import lombok.Setter;
import study111.commerce.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
public class OrdersGetResponse {

    private Long orderId;
    private Instant orderDate;
    private BigDecimal totalPrice;
    private OrderStatus status;

    public OrdersGetResponse() {
    }

    public OrdersGetResponse(Long orderId, Instant orderDate, BigDecimal totalPrice, OrderStatus status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
