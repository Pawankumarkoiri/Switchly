package com.swicthly.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")


public class helloController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println("hello world");
        return "Hello";
    }
}
