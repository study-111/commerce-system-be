package study111.commerce.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class OrderCreateRequest {

    @NotEmpty
    private List<OrderProductCreate> products = new ArrayList<>();

    @Setter
    @Getter
    public static class OrderProductCreate {
        private Long id;
        private int quantity;
    }
}
