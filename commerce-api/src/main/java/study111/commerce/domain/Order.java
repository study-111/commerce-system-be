package study111.commerce.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table
public class Order extends BaseEntity {

    @GeneratedValue
    @Id
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);

        orderProduct.setOrder(this);
    }

    public int getTotalQuantity() {
        return orderProducts.stream()
                .map(OrderProduct::getQuantity)
                .reduce(0, Integer::sum);
    }
}
