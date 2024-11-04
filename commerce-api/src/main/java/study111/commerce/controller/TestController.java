package study111.commerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/required-auth")
@RestController
public class TestController {

    @GetMapping
    public String requiredAuth() {
        return "required-auth";
    }
}
