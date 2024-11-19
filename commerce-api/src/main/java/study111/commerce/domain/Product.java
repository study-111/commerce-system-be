package study111.commerce.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
@Table
public class Product {

    @GeneratedValue
    @Id
    @Column(name = "product_id")
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private int stock;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "product_category_fkey"))
    private Category category;

    @Builder
    public Product(String name, String description, BigDecimal price, int stock, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public void addStock(int quantity) {
        this.stock += quantity;
    }

    public void removeStock(int quantity) {
        if (this.stock - quantity < 0) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        this.stock -= quantity;
    }
}
