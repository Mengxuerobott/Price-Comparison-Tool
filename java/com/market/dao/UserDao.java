package com.market.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.market.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 平台用户表 Mapper 接口
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Repository
public interface UserDao extends BaseMapper<UserEntity> {
    @Select("select max(user_id) from bis_user")
    Integer getUserCount();

    @Select("select * from bis_user where user_phone =#{phone}")
    UserEntity getUserByPhone(String phone);
}
