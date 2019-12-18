package com.lucifer.service;

import com.lucifer.utils.Constant;
import com.lucifer.utils.DateUtils;
import com.lucifer.utils.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class LotteryService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
}
