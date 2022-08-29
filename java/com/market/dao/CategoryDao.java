package com.market.dao;

import com.market.entity.CategoryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Repository
public interface CategoryDao extends BaseMapper<CategoryEntity> {

}
