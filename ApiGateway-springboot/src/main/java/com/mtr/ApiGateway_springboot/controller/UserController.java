package com.mtr.ApiGateway_springboot.controller;

import com.mtr.ApiGateway_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String init(){
        return "index";
    }


}
