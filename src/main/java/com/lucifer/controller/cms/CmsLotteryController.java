package com.lucifer.controller.cms;

import com.lucifer.mapper.AwardMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CmsLotteryController {

    @Resource
    AwardMapper awardMapper;

    @RequestMapping(value="/cms/reward/list",method = RequestMethod.GET)
    public String list(HttpServletRequest request){
        List<List> rewardList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            List memberList = awardMapper.rewardList(i);
                    //new ArrayList();
            rewardList.add(memberList);
        }
        request.setAttribute("rewardList",rewardList);
        return "/cms/reward/list";
    }


}
