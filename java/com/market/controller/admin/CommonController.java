package com.market.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.market.entity.*;
import com.market.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * Created by Elias on 2019/7/12
 */
@Api
@Controller
@RequestMapping("/common")
public class CommonController {
    private BrandService brandService;
    private CategoryService categoryService;
    private CommentService commentService;
    private DicService dicService;
    private MarketSourceService marketSourceService;
    private SpuService spuService;
    private SpuSourceService spuSourceService;
    private UserService userService;
    @Autowired
    public CommonController(BrandService brandService, CategoryService categoryService,
                            CommentService commentService, DicService dicService,
                            MarketSourceService marketSourceService, SpuService spuService,
                            SpuSourceService spuSourceService, UserService userService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.commentService = commentService;
        this.dicService = dicService;
        this.marketSourceService = marketSourceService;
        this.spuService = spuService;
        this.spuSourceService = spuSourceService;
        this.userService = userService;
    }

    @GetMapping("/getView")
    public String getView(@RequestParam String pageName){
        return pageName;
    }


    @ApiOperation("获取所有信息")
    @GetMapping("/console")
    public String getConsole(Model model){
        List<UserEntity> userList=this.userService.selectList(new EntityWrapper<>());
        List<BrandEntity> brandList = this.brandService.selectList(new EntityWrapper<>());
        List<CategoryEntity> categoryList = this.categoryService.selectList(new EntityWrapper<>());
        List<CommentEntity> commentList = this.commentService.selectList(new EntityWrapper<>());
        List<DicEntity> dicList = this.dicService.selectList(new EntityWrapper<>());
        List<MarketSourceEntity> marketSourceList = this.marketSourceService.selectList(new EntityWrapper<>());
        List<SpuEntity> spuList=this.spuService.selectList(new EntityWrapper<>());
        List<SpuSourceEntity> spuSourceList =this.spuSourceService.selectList(new EntityWrapper<>());

        model.addAttribute("userList",userList);
        model.addAttribute("brandList",brandList);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("commentList",commentList);
        model.addAttribute("dicList",dicList);
        model.addAttribute("marketSourceList",marketSourceList);
        model.addAttribute("spuList",spuList);
        model.addAttribute("spuSourceList",spuSourceList);
        return "console";
    }



}
