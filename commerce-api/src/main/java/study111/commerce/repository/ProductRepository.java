package study111.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study111.commerce.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
}
