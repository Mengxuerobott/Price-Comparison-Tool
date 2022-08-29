package com.market.service.impl;

import com.market.entity.MarketSourceEntity;
import com.market.dao.SourceDao;
import com.market.service.MarketSourceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品来源表(全网商城表) 服务实现类
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Service
public class MarketSourceServiceImpl extends ServiceImpl<SourceDao, MarketSourceEntity> implements MarketSourceService {

}
