package study111.commerce.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Entity
@Table
public class OrderProduct {

    @GeneratedValue
    @Id
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "order_product_order_fkey"))
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "order_product_product_fkey"))
    private Product product;

    private int quantity;

    @Builder
    protected OrderProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;

        product.removeStock(quantity);
    }

    public OrderProduct() {
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getOrderPrice() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
