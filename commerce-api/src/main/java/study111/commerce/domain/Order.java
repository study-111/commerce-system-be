package study111.commerce.domain;

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
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table
public class Order {

    @GeneratedValue
    @Id
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // TODO: created by => created by(base entity)

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
