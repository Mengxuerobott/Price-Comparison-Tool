package com.market.service.impl;

import com.market.entity.CommentEntity;
import com.market.dao.CommentDao;
import com.market.service.CommentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表  服务实现类
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

}
