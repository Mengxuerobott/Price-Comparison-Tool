package com.market.utils;

/**
 * Created by Elias on 2019/7/10
 */
public interface Constant {
    String ERROR_INFO_TYPE = "所选用户类型出错";
    String ERROR_INFO_PHONE = "该用户不存在!";
    String ERROR_INFO_PASSWORD = "密码错误!";
    String ERROR_MSG="未知错误，请联系管理员";
    String USER_KEY="SESSION_USER";
    Integer SLIDES_COUNT=16; //首页响应式幻灯片总数,偶数
    Integer MARKET_COUNT=16;
    int PAGE=1; //默认当前页号
    int SIZE=10; //默认分页大小

    interface FilePrefix{
        String spuSourceImg="SPU_SOURCE_IMG-";

    }
    interface UserType{
        String Admin_User="000000";
        String Normal_User="000001";
        String Visitor="000002";
    }

    interface CategoryType{
        Integer phone=1;
        Integer computer=2;
        Long LowestPhone=-1L;
        Long LowestComputer=-2L;
    }

    interface BrandType{
        Integer ALL=0; //0表示所有品牌
        // ... 其余的是品牌编号
    }

    interface SearchScope{
        int ALL=0;
        int PHONE=1;
        int COMPUTER=2;
    }

    interface RecordState{
        //有效
        String Valid="100000";
        //已删除
        String Delete="100001";
    }

}


