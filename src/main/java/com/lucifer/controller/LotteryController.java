package com.lucifer.controller;

import com.lucifer.enumeration.Award;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LotteryController {

    @GetMapping("/")
    public String index(){
        return "/web/index";
    }

    @GetMapping("/win")
    public String win(@RequestParam (value = "id") Integer id, HttpServletRequest request){
        Award award = Award.getById(id);
        String cardName = "card-"+id+".png";
        String cardText = award.name;
        request.setAttribute("cardName",cardName);
        request.setAttribute("cardText",cardText);
        return "/web/win";
    }
}
