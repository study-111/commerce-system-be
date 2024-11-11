package study111.commerce.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study111.commerce.domain.Product;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class ProductsResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryResponse category;

    public ProductsResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = new CategoryResponse(product.getCategory());
    }
}
