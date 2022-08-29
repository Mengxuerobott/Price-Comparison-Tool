package com.market.dao;

import com.market.entity.MarketSourceEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商品来源表(全网商城表) Mapper 接口
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Repository
public interface SourceDao extends BaseMapper<MarketSourceEntity> {

}
