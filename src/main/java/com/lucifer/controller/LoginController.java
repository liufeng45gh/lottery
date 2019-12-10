package com.lucifer.controller;

import com.lucifer.mapper.MemberMapper;
import com.lucifer.service.MemberService;
import com.lucifer.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/mobile")
public class LoginController {

    @Resource
    MemberService memberLoginService;

    @Resource
    MemberMapper memberMapper;

    @RequestMapping(value = {"/","index"},method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        request.setAttribute("independent",false);
        return  "/web/index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request){
        String totalMemberCount = memberMapper.getSysConfigValue("total_member_count");
        request.setAttribute("totalMemberCount",totalMemberCount);
        return   "/web/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result submitLogin(@RequestParam String telephone, @RequestParam String password, HttpServletResponse response ){
        Result result = memberLoginService.loginByTelephonePhone(telephone,password,response);
        return result;
    }


}
