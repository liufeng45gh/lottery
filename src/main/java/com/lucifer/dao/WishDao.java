package com.lucifer.dao;

import com.lucifer.dao.cms.IBatisBaseDao;
import com.lucifer.model.Wish;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WishDao extends IBatisBaseDao {

    public Integer insertWish(Wish wish){
        return this.sqlSession.insert("insertWish",wish);
    }

    public List<Wish> getWishNewList(){
        return this.sqlSession.selectList("getWishNewList");
    }
}
