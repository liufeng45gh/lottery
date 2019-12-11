package com.lucifer.service;

import com.lucifer.exception.UnexpectedException;
import com.lucifer.mapper.MemberMapper;
import com.lucifer.mapper.oauth2.UserMapper;
import com.lucifer.model.Member;
import com.lucifer.model.user.User;
import com.lucifer.utils.Constant;
import com.lucifer.utils.Md5Utils;
import com.lucifer.utils.Result;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MemberService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private WxService wxService;



    public Member getMemberByToken(String token) throws UnexpectedException {
        String stringId = wxService.getIdByToken(token);
        if (stringId == null) {
            throw new UnexpectedException(" memberId can not find by token: " + token);
        }

        Long Id = Long.valueOf(stringId);
        Member member = memberMapper.getMemberById(Id);
        if (null == member) {
            throw new UnexpectedException(" member can not find by id: " + stringId);
        }
        return member;
    }

    public void updateMemberInfo(String token,Member member) throws UnexpectedException {
        String stringId = wxService.getIdByToken(token);
        if (stringId == null) {
            throw new UnexpectedException(" memberId can not find by token: " + token);
        }
        Long id = Long.valueOf(stringId);
        member.setId(id);
        memberMapper.updateMemberInfo(member);
    }
}