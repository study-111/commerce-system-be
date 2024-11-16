package study111.commerce.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @GeneratedValue
    @Id
    @Column(name = "orders_id")
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    protected Order(List<OrderProduct> orderProducts) {
        orderProducts.forEach(this::addOrderProduct);

        this.status = OrderStatus.ORDERED;
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);

        orderProduct.setOrder(this);
    }

    public int getTotalQuantity() {
        return orderProducts.stream()
            .map(OrderProduct::getQuantity)
            .reduce(0, Integer::sum);
    }

    public BigDecimal getTotalPrice() {
        return orderProducts.stream()
            .map(OrderProduct::getOrderPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
