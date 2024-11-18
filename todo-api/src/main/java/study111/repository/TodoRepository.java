package study111.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study111.domain.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
