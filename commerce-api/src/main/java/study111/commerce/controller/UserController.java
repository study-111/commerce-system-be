package study111.commerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study111.commerce.dto.ResponsePayload;
import study111.commerce.service.UserJoinCommand;
import study111.commerce.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> join(@Valid @RequestBody UserJoinCommand command) {
        var userId = userService.join(command);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ResponsePayload.of(userId));
    }
}
