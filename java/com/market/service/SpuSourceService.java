package com.market.service;

import com.baomidou.mybatisplus.service.IService;
import com.market.entity.MarketSourceEntity;
import com.market.entity.SpuSourceEntity;
import com.market.vo.DoubleSpuSourceView;
import com.market.vo.PageResult;
import org.springframework.ui.Model;

import java.util.List;

/**
 * <p>
 * 商品来源表 服务类
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
public interface SpuSourceService extends IService<SpuSourceEntity> {
    List<DoubleSpuSourceView> getLowPriceList(int categoryId);

    //    超值低价-最近热卖
    List<DoubleSpuSourceView> getHotList();

    //    您可能喜欢
    List<SpuSourceEntity> getMayLikeList();

    //    侧栏推荐
    List<SpuSourceEntity> getListWithImg(String directoryName);
    List<SpuSourceEntity> getNewList(int page, int size);

    List<SpuSourceEntity> getSpuSourceListByIds(long categoryId, long brandId, int page, int size);

    List<MarketSourceEntity> getMarketList(Integer count);

    void setCommonMenuInfo(Model model);

    //    搜索
    PageResult<SpuSourceEntity> searchByKeys(Integer scope, String[] strings,Integer page,Integer size);

    List<SpuSourceEntity> getBannerList(String dirName);
    List<SpuSourceEntity> changeImgPath(String dir,List<SpuSourceEntity> list);

    void doDataAnalyse(Long userId,Long spuId,Long spuSourceId);
}
