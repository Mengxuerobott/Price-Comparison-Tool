package com.market.dao;

import com.market.entity.BrandEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 品牌表 Mapper 接口
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Repository
public interface BrandDao extends BaseMapper<BrandEntity> {

    @Select("select * from bis_goods_brand")
    List<BrandEntity> getList();

}
