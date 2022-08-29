package com.market.dao;

import com.market.entity.SpuSourceEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品来源表 Mapper 接口
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Repository
public interface SpuSourceDao extends BaseMapper<SpuSourceEntity> {

    @Select("select  * from bis_goods_spu_source as table_a \n" +
            "where gss_spu_category_id=#{categoryId} and gss_full_price =\n" +
            " (select min(gss_full_price) from bis_goods_spu_source as table_b where table_a.gss_spu_id =table_b.gss_spu_id) ")
    List<SpuSourceEntity> getLowestPriceByInEachSpuByCategoryId(int categoryId);

    List<SpuSourceEntity> selectByKeys(List<String> strings, Integer scope);
}
