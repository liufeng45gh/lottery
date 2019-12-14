package com.lucifer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LotteryController {

    @GetMapping("/")
    public String index(){
        return "/web/index";
    }
}
