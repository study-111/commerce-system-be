package study111.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study111.exception.CustomException;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleNotFoundTodo(CustomException exception) {
        var status = exception.getStatus();
        var message = exception.getMessage();

        return ResponseEntity.status(status).body(message);
    }
}
