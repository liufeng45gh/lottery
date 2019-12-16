package com.lucifer.controller;

import com.lucifer.dao.AwardDao;
import com.lucifer.enumeration.Award;
import com.lucifer.exception.NotLoginException;
import com.lucifer.mapper.MemberMapper;
import com.lucifer.model.Member;
import com.lucifer.model.MemberAward;
import com.lucifer.service.WxService;
import com.lucifer.utils.StringHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LotteryController {

    @Resource
    AwardDao awardDao;

    @Resource
    WxService wxService;

    @Resource
    MemberMapper memberMapper;

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


    @GetMapping("/my")
    public String my(@CookieValue(value = "token",required = false) String token,HttpServletRequest request) throws NotLoginException {

        String userId = wxService.getIdByToken(token);
        if (StringHelper.isEmpty(userId)) {
            throw new NotLoginException("can not find user by token  : " + token);
        }

        Long memberId = Long.valueOf(userId);
        List<MemberAward> memberAwards = awardDao.getMemberAwardCount(memberId);
        for (MemberAward memberAward : memberAwards) {
            request.setAttribute("award_" + memberAward.getAwardId(),memberAward.getCount());
        }
        Member member = memberMapper.getMemberById(memberId);
        request.setAttribute("member",member);
        return "/web/my";
    }


}
