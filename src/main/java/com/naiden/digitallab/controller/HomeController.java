package com.naiden.digitallab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @RequestMapping(value = {"", "/home"})
    public String home() {
        return "home";
    }
}
