package com.school.schoolSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @GetMapping("/server")
    public String checkServerActive(){
        return "server ok";
    }
}
