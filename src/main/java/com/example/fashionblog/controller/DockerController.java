package com.example.fashionblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docker")
public class DockerController {
    @GetMapping("testAPI")
    public String testMethod(){
        return "am working just fine";
    }
}
