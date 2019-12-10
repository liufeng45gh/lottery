package com.lucifer.mapper;

import com.lucifer.annotation.MapperScanSelf;
import com.lucifer.model.Member;
import org.apache.ibatis.annotations.Param;

@MapperScanSelf
public interface MemberMapper {

    Integer insertMemberToken(@Param("memberId")Long memberId,@Param("token")String token);

    Integer insertMember(Member member);

    Member getByPhone(@Param("telephone") String telephone);

    Long getMemberIdByToken(@Param("token")String token);

    Member getMemberById(@Param("id") Long id);

    Member getMemberByWxId(@Param("wxId") String wxId);

    void updateMemberInfo(Member member);

    String getSysConfigValue(@Param("key") String key);

    void updateSysConfigValue(@Param("key") String key, @Param("value")String value);
}
