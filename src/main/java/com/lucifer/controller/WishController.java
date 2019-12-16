package com.lucifer.controller;

import com.lucifer.dao.WishDao;
import com.lucifer.exception.NotLoginException;
import com.lucifer.model.Wish;
import com.lucifer.service.WxService;
import com.lucifer.utils.Result;
import com.lucifer.utils.StringHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class WishController {

    @Resource
    WxService wxService;

    @Resource
    WishDao wishDao;

    @PostMapping(value = "/wish/commit")
    public Result put(@RequestBody  Wish wish, @CookieValue(value = "token",required = false) String token) throws NotLoginException {
        String userId = wxService.getIdByToken(token);
        if (StringHelper.isEmpty(userId)) {
            throw new NotLoginException("can not find user by token  : " + token);
        }
        wishDao.insertWish(wish);
        return Result.ok();
    }

    @GetMapping(value = "/wish/list")
    public List<Wish> wishList(){
        return wishDao.getWishNewList();
    }
}
