package com.lucifer.dao;

import com.lucifer.dao.cms.IBatisBaseDao;
import com.lucifer.model.MemberAward;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AwardDao  extends IBatisBaseDao {

    public List<MemberAward> getMemberAwardCount(Long memberId){
        return this.sqlSession.selectList("getMemberAwardCount",memberId);
    }

}
