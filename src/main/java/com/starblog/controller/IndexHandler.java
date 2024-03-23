package com.starblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexHandler {
    @RequestMapping({"/index","/index.html"})
    public String index(){
        return "../index.html";
    }
}
