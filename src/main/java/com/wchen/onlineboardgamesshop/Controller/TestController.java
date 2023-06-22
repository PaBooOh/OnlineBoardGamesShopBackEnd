package com.wchen.onlineboardgamesshop.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }
}
