package study111.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study111.domain.TodoEntity;
import study111.dto.ResponseTodo;
import study111.dto.TodoAdd;
import study111.dto.TodoUpdate;
import study111.exception.NotFoundTodo;
import study111.repository.TodoRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public void add(TodoAdd todoAdd) {
        var content = todoAdd.getContent();
        var todoResult = TodoEntity.builder().content(content).build();

        todoRepository.save(todoResult);
    }

    public List<ResponseTodo> list() {
        var result = todoRepository.findAll();

        return result.stream().map(ResponseTodo::new).collect(Collectors.toList());
    }

    @Transactional
    public void save(Long id, TodoUpdate todoUpdate) {
        var searchResult = todoRepository.findById(id)
            .orElseThrow((NotFoundTodo::new));

        searchResult.update(todoUpdate.getContent());
    }

    public ResponseTodo search(Long id) {
        var result = todoRepository.findById(id)
            .orElseThrow(NotFoundTodo::new);

        return new ResponseTodo(result);
    }

    @Transactional
    public void delete(Long id) {
        var result = todoRepository.findById(id)
            .orElseThrow(NotFoundTodo::new);

       todoRepository.delete(result);
    }
}
