package study111.commerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study111.commerce.annotation.PreAuthorizeMine;
import study111.commerce.request.UserJoinRequest;
import study111.commerce.request.UserModifyRequest;
import study111.commerce.response.CommonResponse;
import study111.commerce.service.UserService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ANONYMOUS')")
    @PostMapping
    public ResponseEntity<CommonResponse<Long>> join(
        @Valid @RequestBody UserJoinRequest command) {
        var userId = userService.join(command);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(CommonResponse.of(userId));
    }

    @PreAuthorizeMine
    @PatchMapping("/{userId}")
    public ResponseEntity<CommonResponse<Boolean>> modify(
        @PathVariable Long userId,
        @Valid @RequestBody UserModifyRequest user
    ) {
        userService.modify(userId, user);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(CommonResponse.of(true));
    }

}
