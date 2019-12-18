package com.lucifer.mapper;

import com.lucifer.annotation.MapperScanSelf;
import com.lucifer.model.AwardDayConfig;
import com.lucifer.model.MemberAward;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MapperScanSelf
public interface AwardMapper {
    List<MemberAward> getMemberAwardCount(@Param(value = "memberId") Long memberId);

    String getAwardDay(@Param(value = "id") String id);

    List<AwardDayConfig> getAwardDayConfigList(@Param(value = "day") String day);

    Integer getAwardDayCount(@Param(value = "day") String day,@Param(value = "configId") Integer configId);

}
