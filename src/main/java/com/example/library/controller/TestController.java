package com.example.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/test")
@Controller
public class TestController {
    @GetMapping(value = "/hello")
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }
}
