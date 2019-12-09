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
public class MemberLoginService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserMapper userMapper;

    @Resource
    private MemberMapper memberMapper;

    public Result loginByTelephonePhone(String telephone, String password, HttpServletResponse response){

        User dbUser = userMapper.getUserByAccount("klny-question");
        if  (null == dbUser)  {
            return Result.fail("用户未找到");
        }
        String md5Password = Md5Utils.md5(Md5Utils.md5(password)+dbUser.getSalt());
        logger.info("md5Password: "+md5Password);
        if (!md5Password.equals(dbUser.getPassword())) {
            return Result.fail("密码错误");
        }

        Member member = memberMapper.getByPhone(telephone);
        if (null == member) {
            member = new Member();
            member.setTelephone(telephone);
            memberMapper.insertMember(member);
            String totalMemberCount = memberMapper.getSysConfigValue("total_member_count");
            Integer tmp = Integer.valueOf(totalMemberCount);
            tmp = tmp + 1;
            totalMemberCount = String.valueOf(tmp);
            memberMapper.updateSysConfigValue("total_member_count",totalMemberCount);

        }
        String token = RandomStringUtils.randomAlphanumeric(20);

        memberMapper.insertMemberToken(member.getId(),token);

        Cookie cookie = new Cookie(Constant.TOKEN,token);
        cookie.setPath("/");
        cookie.setMaxAge(365 * 24*60*60);
        response.addCookie(cookie);

        return Result.ok(token);
    }

    public Member getMemberByToken(String token) throws UnexpectedException {
        Long memberId = memberMapper.getMemberIdByToken(token);
        if (memberId == null) {
            throw new UnexpectedException(" memberId can not find by token: " + token);
        }

        Member member = memberMapper.getMemberById(memberId);
        if (null == member) {
            throw new UnexpectedException(" member can not find by id: " + memberId);
        }
        return member;
    }

    public void updateMemberInfo(String token,Member member) throws UnexpectedException {
        Long memberId = memberMapper.getMemberIdByToken(token);
        if (memberId == null) {
            throw new UnexpectedException(" memberId can not find by token: " + token);
        }
        member.setId(memberId);
        memberMapper.updateMemberInfo(member);
    }
}
