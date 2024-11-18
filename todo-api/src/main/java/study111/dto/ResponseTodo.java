package study111.dto;

import lombok.Getter;
import study111.domain.TodoEntity;

@Getter
public class ResponseTodo {

    private Long id;
    private String content;

    public ResponseTodo(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.content = todoEntity.getContent();
    }

}
