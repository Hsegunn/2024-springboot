package com.jaemin.backboard.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@Log4j2
public class MainController {
    
    @GetMapping("/hello")
    public String Hello() {
        log.info("getHello(); 실행");
        return "Hello";
    }

    @GetMapping("/")
    public String Main() {
        return "redirect:/board/list/free";  // localhost:8080/ -> localhost:8080/board/list 변경
    }
    
    
}
