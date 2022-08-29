package com.market.service.impl;

import com.market.entity.UserEntity;
import com.market.dao.UserDao;
import com.market.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台用户表 服务实现类
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

}
