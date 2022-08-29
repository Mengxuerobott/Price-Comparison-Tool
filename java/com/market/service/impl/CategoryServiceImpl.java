package com.market.service.impl;

import com.market.entity.CategoryEntity;
import com.market.dao.CategoryDao;
import com.market.service.CategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

}
