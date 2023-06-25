package com.wchen.onlineboardgamesshop.Controller;


import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/hello")
    public String test() {
        return "Hello jenkins! Pipeline created!!! Go Go Go!!!!!!!";
    }
}
