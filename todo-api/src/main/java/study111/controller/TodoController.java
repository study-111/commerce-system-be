package study111.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study111.dto.ResponseTodo;
import study111.dto.TodoAdd;
import study111.dto.TodoUpdate;
import study111.service.TodoService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Void> addTodo(@RequestBody TodoAdd todoAdd) {
        todoService.add(todoAdd);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseTodo>> searchTodo() {
        var result = todoService.list();

        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> updateTodo(@RequestBody TodoUpdate todoUpdate, @PathVariable Long id) {
        todoService.save(id, todoUpdate);

        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseTodo> searchTodo(@PathVariable Long id) {
        var result = todoService.search(id);

        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
