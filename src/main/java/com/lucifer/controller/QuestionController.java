package com.lucifer.controller;

import com.lucifer.mapper.QuestionMapper;
import com.lucifer.model.Question;
import com.lucifer.service.QuestionService;
import com.lucifer.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/mobile")
public class QuestionController {

    @Resource
    QuestionMapper questionMapper;

    @Resource
    QuestionService questionService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String toLogin(){
        return   "/web/list";
    }

    @RequestMapping(value = "/show-score",method = RequestMethod.GET)
    public String showScore(){
        return   "/web/show-score";
    }

    @RequestMapping(value = "/practice",method = RequestMethod.GET)
    public String practice(){
        return   "/web/practice";
    }

    @RequestMapping(value = "/enable-id-list",method = RequestMethod.GET)
    @ResponseBody
    public Result enableIdList(){
        List<Long> enableIdList = questionMapper.enableIdList();
        return Result.ok(enableIdList);
    }

    @RequestMapping(value = "/enable-random-id-list",method = RequestMethod.GET)
    @ResponseBody
    public Result enableRandomIdList(){
        List<Long> enableIdList = questionMapper.enableRandomIdList(10);
        return Result.ok(enableIdList);
    }

    @RequestMapping(value = "/question/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result getQuestion(@PathVariable Long id){
        Question question = questionService.getQuestion(id);
        return Result.ok(question);
    }

    @RequestMapping(value = "/mock-exam",method = RequestMethod.GET)
    public String mockExam(){
        return   "/web/mock-exam";
    }
}
