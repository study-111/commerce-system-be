package study111.commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study111.commerce.dto.ResponsePayload;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        // TODO: 커스텀 에러 객체로 변경
        var errors = exception.getAllErrors();

        return ResponseEntity.badRequest()
            .body(ResponsePayload.of(errors));
    }
}

