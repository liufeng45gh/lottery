package com.lucifer.dao;

import com.lucifer.dao.cms.IBatisBaseDao;
import com.lucifer.model.AwardDayConfig;
import com.lucifer.model.MemberAward;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AwardDao  extends IBatisBaseDao {

    public List<MemberAward> getMemberAwardCount(Long memberId){
        return this.sqlSession.selectList("getMemberAwardCount",memberId);
    }

    public String getAwardDay(String id){
        return this.sqlSession.selectOne("getAwardDay",id);
    }

    public List<AwardDayConfig> getAwardDayConfigList(String day){
        return this.sqlSession.selectList("getAwardDayConfigList",day);
    }

//    public Integer getAwardDayCount(String day,Long configId){
//        return this.sqlSession.selectOne("getAwardDayCount",@Param(value = "day") day);
//    }

}
