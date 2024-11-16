package study111.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study111.commerce.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
