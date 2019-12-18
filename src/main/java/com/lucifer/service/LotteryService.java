package com.lucifer.service;

import com.lucifer.dao.AwardDao;
import com.lucifer.exception.AwardException;
import com.lucifer.exception.NotLoginException;
import com.lucifer.utils.Constant;
import com.lucifer.utils.DateUtils;
import com.lucifer.utils.Result;
import com.lucifer.utils.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class LotteryService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    WxService wxService;

    @Resource
    AwardDao awardDao;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getMemberLotteryCountKey(Long memberId){
        String dayString = dateFormat.format(DateUtils.now());
        String key = Constant.CACHE_KEY_DAY_LOTTERY_COUNT_PRE + dayString + ":" + memberId;
        logger.info("getMemberLotteryCountKey is {}",key);
        return key;
    }

    public Long getMemberLotteryCount(Long memberId){
        String key = this.getMemberLotteryCountKey(memberId);
        String value = stringRedisTemplate.opsForValue().get(key);
        logger.info("getMemberLotteryCount value is {}",value);
        if (StringHelper.isEmpty(value)) {
            return 0l;
        }
        return Long.valueOf(value);

    }

    public Long incrementMemberLotteryCount(Long memberId){
        String key = this.getMemberLotteryCountKey(memberId);
        Long value = stringRedisTemplate.opsForValue().increment(key);
        logger.info("incrementMemberLotteryCount value is {}",value);
        return value;
    }

    public Result doLottery(String token) throws NotLoginException, AwardException {
        String userId = wxService.getIdByToken(token);
        if (StringHelper.isEmpty(userId)) {
            throw new NotLoginException("can not find user by token  : " + token);
        }

        Long memberId = Long.valueOf(userId);

        String dayString = dateFormat.format(DateUtils.now());
        String awardDay = awardDao.getAwardDay(dayString);
        if (StringHelper.isEmpty(awardDay)) {
            throw  new AwardException("Not Award Day");
        }

        Long count = this.incrementMemberLotteryCount(memberId);
        if (count>3) {
            throw  new AwardException("award times limit exceed");
        }


        

        return Result.ok();
    }
}
