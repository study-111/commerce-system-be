package study111.commerce.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import study111.commerce.domain.OrderStatus;

import java.time.LocalDate;

@Setter
@Getter
public class OrdersGetRequest {

    private OrderStatus status;
    // orderDate, 공통적인 설정방법이 따로 있지 않을까?
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
//    private Instant startDate;
//    private Instant endDate;
}
