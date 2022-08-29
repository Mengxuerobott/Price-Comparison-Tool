package com.market.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.market.dao.SpuSourceDao;
import com.market.entity.BrandEntity;
import com.market.entity.MarketSourceEntity;
import com.market.entity.SpuSourceEntity;
import com.market.service.BrandService;
import com.market.service.MarketSourceService;
import com.market.service.SpuSourceService;
import com.market.utils.Constant;
import com.market.utils.JUtils;
import com.market.vo.DoubleSpuSourceView;
import com.market.vo.PageResult;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品来源表 服务实现类
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Service
public class SpuSourceServiceImpl extends ServiceImpl<SpuSourceDao, SpuSourceEntity> implements SpuSourceService {
    private BrandService brandService;
    private MarketSourceService marketSourceService;
    @Autowired
    public SpuSourceServiceImpl(BrandService brandService, MarketSourceService marketSourceService) {
        this.brandService = brandService;
        this.marketSourceService = marketSourceService;
    }

    @Override
    public List<DoubleSpuSourceView> getLowPriceList(int categoryId) {
        List<DoubleSpuSourceView> views = new ArrayList<>();
        // 找出相同商品下价格最低的那条记录
        List<SpuSourceEntity> spuSourceList = this.baseMapper.getLowestPriceByInEachSpuByCategoryId(categoryId);
            int size=spuSourceList.size();
            //若奇数则舍去最后一条纪录，使其为偶数,使得页面整齐的 同时 避免在循环里进行判断
            if (size%2==1) spuSourceList.remove(size-1);
            //i < spuSourceList.size()-1，确保size-1为偶数，最后一次循环时判断i=size-3成立后执行i=i+2，
            // 并将i(即size-2)和i+1(即size-1)索引所在元素放入views中,size-1即最后一个元素的下标
            for (int i = 0; i < size -2; i = i + 2) {
                DoubleSpuSourceView view = new DoubleSpuSourceView(spuSourceList.get(i), spuSourceList.get(i + 1));
                views.add(view);
        }
        return views;
    }

    @Override
    public List<DoubleSpuSourceView> getHotList() {
        List<DoubleSpuSourceView> views = new ArrayList<>();
        EntityWrapper wrapper = new EntityWrapper();
        // 一个月前
        wrapper.between("gss_ttm",JUtils.dateFormat(JUtils.getDateAMonthBefore()) , JUtils.dateFormat((new Date())));
        PageHelper.startPage(1,Constant.SLIDES_COUNT);
        List<SpuSourceEntity> spuSourceList = selectList(wrapper);
        int size=spuSourceList.size();
        //若奇数则舍去最后一条纪录，使其为偶数,使得页面整齐的 同时 避免在循环里进行判断
        if (size%2==1) spuSourceList.remove(size-1);
        for (int i = 0; i < size-2; i = i + 2) {
            DoubleSpuSourceView view = new DoubleSpuSourceView(spuSourceList.get(i), spuSourceList.get(i + 1));
            // add 与 set ...
            views.add(view);
        }
        return views;
    }

    @Override
    public List<SpuSourceEntity> getMayLikeList() {
        List<SpuSourceEntity> mayLikeList=this.getListWithImg("hot");
        return mayLikeList;
    }

    @Override
    public List<SpuSourceEntity> getListWithImg(String directoryName) {
        PageHelper.startPage(1,Constant.SLIDES_COUNT);
        List<SpuSourceEntity> recommendList=selectList(new EntityWrapper<SpuSourceEntity>()
                .orderBy("gss_comment",false));
        for (SpuSourceEntity spuSourceEntity : recommendList) {
//            同款产品只有一张图片
            spuSourceEntity.setGssImgUrl("/static/assets/images/"+directoryName+"/"+spuSourceEntity.getGssSpuId()+".png");
        }
        return recommendList;
    }

    @Override
    public List<SpuSourceEntity> getNewList(int page,int size) {
        EntityWrapper<SpuSourceEntity> wrapper = new EntityWrapper<>();
        wrapper.between("gss_ttm", JUtils.dateFormat(JUtils.getDateAMonthBefore()),
                JUtils.dateFormat((new Date())));
        PageHelper.startPage(page, size);
        List<SpuSourceEntity> spuSourceList = selectList(wrapper);
        return spuSourceList;
    }

    @Override
    public List<SpuSourceEntity> getSpuSourceListByIds(long categoryId, long brandId,int page,int size) {
        System.out.println("category Id = "+categoryId);
        System.out.println("brand Id" + brandId);
        EntityWrapper<SpuSourceEntity> wrapper = new EntityWrapper<>();
        List<SpuSourceEntity> spuSourceList;
        // categoryId= -1是最低价手机列表
        if (-1L==categoryId) {
            PageHelper.startPage(page, size);
            spuSourceList = this.baseMapper.getLowestPriceByInEachSpuByCategoryId(Constant.CategoryType.phone);
        } else if (-2L==categoryId) {
//            -2是最低价电脑列表
            PageHelper.startPage(page, size);
            spuSourceList = this.baseMapper.getLowestPriceByInEachSpuByCategoryId(Constant.CategoryType.computer);
        } else {
            wrapper.eq("gss_spu_category_id", categoryId);
//            brandId=0时是查询该分类下全部列表，不为0时查询分类下的某品牌的列表
            if (brandId!=0L) {
                wrapper.eq("gss_spu_brand_id", brandId);
            }
            PageHelper.startPage(page, size);
            spuSourceList = this.baseMapper.selectList(wrapper);
        }
        return spuSourceList;
    }

    @Override
    public List<MarketSourceEntity> getMarketList(Integer count) {
        PageHelper.startPage(1,count);
        List<MarketSourceEntity> marketList=this.marketSourceService.selectList(new EntityWrapper<>());
        return marketList;
    }


    @Override
    public void setCommonMenuInfo(Model model) {

//        手机导航菜单--获取品牌列表
        List<BrandEntity> phoneList = this.brandService.selectList(new EntityWrapper<BrandEntity>()
                .eq("gb_gc_id", Constant.CategoryType.phone));
        model.addAttribute("phoneList", phoneList);
//        电脑导航菜单--获取品牌列表
        List<BrandEntity> computerList = this.brandService.selectList(new EntityWrapper<BrandEntity>()
                .eq("gb_gc_id", Constant.CategoryType.computer));
        model.addAttribute("computerList", computerList);
    }

    @Override
    public PageResult<SpuSourceEntity> searchByKeys(Integer scope, String [] strings, Integer page, Integer size) {
        if (page==null || size==null){
            page=Constant.PAGE;
            size=Constant.SIZE;
        }
        PageHelper.startPage(page,size);
        List<SpuSourceEntity> list=this.baseMapper.selectByKeys(Arrays.asList(strings),scope);
        return new PageResult<>(list);
    }

    @Override
    public List<SpuSourceEntity> getBannerList(String dirName) {
        PageHelper.startPage(1,6);
        List<SpuSourceEntity> list=this.baseMapper.selectList
                (new EntityWrapper<SpuSourceEntity>().orderBy("gss_comment"));
        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setGssImgUrl("/static/assets/images/"+dirName+"/"+(i+1)+".jpg");
        }
        return list;
    }


    @Override
    public List<SpuSourceEntity> changeImgPath(String dir,List<SpuSourceEntity> list) {
        for (SpuSourceEntity spuSourceEntity : list) {
//            同款产品只有一张图片
            spuSourceEntity.setGssImgUrl("/static/assets/images/"+dir+"/"+spuSourceEntity.getGssSpuId()+".png");
        }
        return list;
    }

    @Override
    public void doDataAnalyse(Long userId, Long spuId, Long spuSourceId) {
        SpuSourceEntity entity=this.selectById(spuSourceId);
        entity.setGssView(entity.getGssView()==null?1:entity.getGssView()+1);
        this.updateById(entity);
    }

}
