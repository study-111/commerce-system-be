package study111.commerce.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class GetProductsRequest {

    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
