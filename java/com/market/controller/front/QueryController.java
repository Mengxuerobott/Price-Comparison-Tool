package com.market.controller.front;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.market.dao.CommentDao;
import com.market.dao.SpuSourceDao;
import com.market.entity.CommentEntity;
import com.market.entity.MarketSourceEntity;
import com.market.entity.SpuSourceEntity;
import com.market.entity.UserEntity;
import com.market.service.BrandService;
import com.market.service.CommentService;
import com.market.service.MarketSourceService;
import com.market.service.SpuSourceService;
import com.market.utils.Constant;
import com.market.vo.CommentView;
import com.market.vo.DoubleSpuSourceView;
import com.market.vo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elias on 2019/7/15
 */
@Api("通用查询接口")
@RequestMapping("/query")
@Controller
public class QueryController {
    private SpuSourceService spuSourceService;
    private SpuSourceDao spuSourceDao;
    private CommentDao commentDao;

    @Autowired
    public QueryController(SpuSourceService spuSourceService, SpuSourceDao spuSourceDao, CommentDao commentDao) {
        this.spuSourceService = spuSourceService;
        this.spuSourceDao = spuSourceDao;
        this.commentDao = commentDao;
    }

    @ApiOperation("获取主页信息--index.html")
    @GetMapping("/index")
    public String getIndexInfo(Model model, HttpSession session) {
        this.spuSourceService.setCommonMenuInfo(model);
//        大图banner
        List<SpuSourceEntity> bannerList=this.spuSourceService.getBannerList("banner");
        model.addAttribute("banner", bannerList);
//      一周热卖
        List<SpuSourceEntity> hotSaleList = this.spuSourceService.getListWithImg("hot");
        model.addAttribute("recommendList", hotSaleList);
        final int slidesIndex = Constant.SLIDES_COUNT - 1;
//        热门列表
        //      首页--热门
        List<DoubleSpuSourceView> hotTotalList = this.spuSourceService.getHotList();
        List<DoubleSpuSourceView> hotList = new ArrayList<>(hotTotalList.subList(0,
                hotTotalList.size() > slidesIndex ? slidesIndex : hotTotalList.size())); //注意subList的陷阱
        model.addAttribute("hotList", hotList);
        //手机卖场
        List<DoubleSpuSourceView> phoneSpuTotalList = this.spuSourceService.getLowPriceList(Constant.CategoryType.phone);
        List<DoubleSpuSourceView> phoneSpuList = new ArrayList<>(phoneSpuTotalList.subList(0,
                phoneSpuTotalList.size() > slidesIndex ? slidesIndex : phoneSpuTotalList.size()));
        model.addAttribute("phoneSpuList", phoneSpuList);
        //电脑卖场
        List<DoubleSpuSourceView> computerSpuTotalList = this.spuSourceService.getLowPriceList(Constant.CategoryType.computer);
        List<DoubleSpuSourceView> computerSpuList = new ArrayList<>(computerSpuTotalList.subList(0,
                computerSpuTotalList.size() > slidesIndex ? slidesIndex : computerSpuTotalList.size()));
        model.addAttribute("computerSpuList", computerSpuList);
        return "index";
    }


    @ApiOperation("新品上市--list-product-new.html")
    @GetMapping("/getNewList")
    public String getNew(Model model, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        this.spuSourceService.setCommonMenuInfo(model);
//      商场列表
        List<MarketSourceEntity> marketList = this.spuSourceService.getMarketList(Constant.MARKET_COUNT);
        model.addAttribute("marketList", marketList);
        // 最新商品列表
        List<SpuSourceEntity> spuSourceList = this.spuSourceService.getNewList(page, size);
        model.addAttribute("spuSourceList", new PageResult<>(spuSourceList));
        //        侧栏推荐
        List<SpuSourceEntity> recommendList = this.spuSourceService.getListWithImg("recommend");
        model.addAttribute("recommendList", recommendList);
//        您可能喜欢
        List<SpuSourceEntity> mayLikeList = this.spuSourceService.getMayLikeList();
        model.addAttribute("mayLikeList", mayLikeList);
        return "list-product-new";
    }

    @ApiOperation("根据品牌和分类进行筛选--list-product-category.html")
    @GetMapping("/getSpuListByIds/{brandId}/{categoryId}")
    public String getSpuListByBrandAndCategory(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "10") Integer size
            , @PathVariable String brandId, @PathVariable String categoryId, Model model) {
        this.spuSourceService.setCommonMenuInfo(model);
//        商城列表
        List<MarketSourceEntity> marketList = this.spuSourceService.getMarketList(Constant.MARKET_COUNT);
        model.addAttribute("marketList", marketList);
//        侧栏推荐
        List<SpuSourceEntity> recommendList = this.spuSourceService.getListWithImg("recommend");
        model.addAttribute("recommendList", recommendList);
//        商品列表
        List<SpuSourceEntity> spuSourceList = this.spuSourceService.
                getSpuSourceListByIds(Long.parseLong(categoryId), Long.parseLong(brandId), page, size);
        model.addAttribute("spuSourceList", new PageResult<>(spuSourceList));
//        您可能喜欢
        List<SpuSourceEntity> mayLikeList = this.spuSourceService.getMayLikeList();
        model.addAttribute("mayLikeList", mayLikeList);
//        该主页的标识
        model.addAttribute("brandId", brandId);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("type", 1);
        return "list-product-category";
    }

    @ApiOperation("获取spu产品细节-single-product.html")
    @GetMapping("/getSpuSourceDetail/{spuId}/{spuSourceId}")
    public String getSpuSourceDetail(@PathVariable String spuId, @PathVariable String spuSourceId, Model model) {
        this.spuSourceService.setCommonMenuInfo(model);
//        侧栏推荐列表
        List<SpuSourceEntity> recommendList = this.spuSourceService.getListWithImg("recommend");
        model.addAttribute("recommendList", recommendList);
//        获取当前产品的当前记录信息
        SpuSourceEntity entity = this.spuSourceService.selectById(spuSourceId);
        model.addAttribute("entity", entity);
//        获取同一产品的其他记录 进行比价
        PageHelper.startPage(1, 5);
        List<SpuSourceEntity> sameProductList = this.spuSourceDao.
                selectList(new EntityWrapper<SpuSourceEntity>().eq("gss_spu_id", spuId));
        List<SpuSourceEntity> changeImgList=this.spuSourceService.changeImgPath("recommend",sameProductList);
        model.addAttribute("sameProductList", changeImgList);
//        获取友情商家信息
        List<MarketSourceEntity> marketList = this.spuSourceService.getMarketList(Constant.MARKET_COUNT);
        model.addAttribute("marketList", marketList);
//       获取该产品的评论信息
        List<CommentView> commentViews = this.commentDao.getSpuComments(Long.parseLong(spuId));
        model.addAttribute("comments", commentViews);
//        该页面标识，还有spuSourceId在entity中可获得
        model.addAttribute("spuId", spuId);
        return "single-product";
    }


    @ApiOperation("最低价列表")
    @GetMapping("/getLowPriceList/{categoryId}")
    public String getLowPriceList(@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer size, @PathVariable Integer categoryId, Model model) {
        this.spuSourceService.setCommonMenuInfo(model);
        PageHelper.startPage(page, size);
        List<SpuSourceEntity> spuSourceList = this.spuSourceDao.getLowestPriceByInEachSpuByCategoryId(categoryId);
        model.addAttribute("spuSourceList", new PageResult<>(spuSourceList));
        return "list-product-new";
    }


    @ApiOperation("提交评论")
    @PostMapping("/postComment/{spuSourceId}")
    public String postComment(CommentEntity comment, @PathVariable String spuSourceId) {
//        评论数加1
        SpuSourceEntity entity=this.spuSourceService.selectById(spuSourceId);
        entity.setGssComment(entity.getGssComment() +1);
        this.spuSourceService.updateById(entity);
        this.commentDao.insert(comment);
        return "redirect:/query/getSpuSourceDetail/" + comment.getCommentSpuId() + "/" + spuSourceId;
    }

    @ApiOperation("主页-搜索栏")
    @GetMapping("/doSearchByKeywords")
    public String doSearchByKeyWords(@RequestParam("keys") String keys, @RequestParam("scope") Integer scope,
                                     @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                     @RequestParam(value = "size", defaultValue = "10", required = false) Integer size, Model model) {
        this.spuSourceService.setCommonMenuInfo(model);
//        spuSourceList
        String[] strArray = keys.split("\\s+");
        PageResult<SpuSourceEntity> result = this.spuSourceService.searchByKeys(scope, strArray, page, size);

        model.addAttribute("spuSourceList", result);
        //        商城列表
        List<MarketSourceEntity> marketList = this.spuSourceService.getMarketList(Constant.MARKET_COUNT);
        model.addAttribute("marketList", marketList);
        List<SpuSourceEntity> recommendList = this.spuSourceService.getListWithImg("recommend");
        model.addAttribute("recommendList", recommendList);
        List<SpuSourceEntity> mayLikeList = this.spuSourceService.getMayLikeList();
        model.addAttribute("mayLikeList", mayLikeList);
//        该页标识
        model.addAttribute("keys", keys);
        model.addAttribute("scope", scope);
        model.addAttribute("type", 2);
        return "list-product-category";
    }


}
