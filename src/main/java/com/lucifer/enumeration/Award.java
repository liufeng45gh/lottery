package com.lucifer.enumeration;

import lombok.Data;


public enum Award {

    King(1,"王者粉丝奖 100元加油卡",4,0.001f),
    diamond(2,"钻石粉丝奖 30元红包",20,0.003f),
    platinum(3,"白金粉丝奖 20元红包",30,0.005f),
    gold(4,"黄金粉丝奖 10元红包",50,0.010f),
    bronze(5,"青铜粉丝奖 5元红包",110,0.020f),
    ;

    public final Integer id;

    public final String name;

    public final Integer totalCount;

    public final Float rate;

    Award(Integer id,String name,Integer totalCount,Float rate){
        this.id = id;
        this.name = name;
        this.totalCount= totalCount;
        this.rate = rate;
    }
}