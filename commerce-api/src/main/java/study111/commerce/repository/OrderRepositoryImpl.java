package study111.commerce.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study111.commerce.domain.OrderStatus;
import study111.commerce.request.OrdersGetRequest;
import study111.commerce.response.OrdersGetResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import static study111.commerce.domain.QOrder.order;
import static study111.commerce.domain.QOrderProduct.orderProduct;
import static study111.commerce.domain.QProduct.product;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<OrdersGetResponse> findOrders(Long userId, OrdersGetRequest request) {
        return queryFactory
            .select(Projections.fields(OrdersGetResponse.class,
                order.id.as("orderId"),
                order.createdDate.as("orderDate"),
                order.status.as("status"),
                Expressions.numberTemplate(BigDecimal.class, "({0} * {1})", orderProduct.quantity, product.price).as("totalPrice")
            ))
            .from(order)
            .join(order.orderProducts, orderProduct)
            .join(orderProduct.product, product)
            .where(
                order.createdBy.eq(userId),
                eqOrderStatus(request.getStatus()),
                afterStartDate(request.getStartDate()),
                beforeEndDate(request.getEndDate())
            )
            .fetch();
    }

    private static BooleanExpression beforeEndDate(LocalDate endDate) {
        return endDate != null
            ? order.createdDate.before(endDate.atStartOfDay().toInstant(ZoneOffset.UTC))
            : null;
    }

    private BooleanExpression afterStartDate(LocalDate startDate) {
        return startDate != null
            ? order.createdDate.after(startDate.atStartOfDay().toInstant(ZoneOffset.UTC))
            : null;
    }

    private BooleanExpression eqOrderStatus(OrderStatus status) {
        return status != null ? order.status.eq(status) : null;
    }
}
