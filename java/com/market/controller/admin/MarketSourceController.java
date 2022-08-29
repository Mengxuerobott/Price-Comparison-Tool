package com.market.controller.admin;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.market.entity.MarketSourceEntity;
import com.market.form.BaseForm;
import com.market.service.MarketSourceService;
import com.market.vo.CommonResult;
import com.market.vo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品来源表(全网商城表) 前端控制器
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Api("商城")
@Controller
@RequestMapping("/source")
public class MarketSourceController {
    private MarketSourceService marketSourceService;

    @Autowired
    public MarketSourceController(MarketSourceService marketSourceService) {
        this.marketSourceService = marketSourceService;
    }

    @ApiOperation("模板-查询")
    @GetMapping("/getList_th")
    public String getListTh(@RequestBody BaseForm form){
        PageHelper.startPage(form.getPage(),form.getSize());
        List list=this.marketSourceService.selectList(new EntityWrapper<>());
        return "market";
    }

    @ApiOperation("模板-增加")
    @PostMapping("/insertItem_th")
    public String insertItemTh(MarketSourceEntity entity){
        //新增id
        entity.setGsId(null);
        this.marketSourceService.insert(entity);
        return "market";
    }

    @ApiOperation("模板-修改")
    @PostMapping("/updateItem_th")
    public String updateItemTh(MarketSourceEntity entity){
        this.marketSourceService.updateById(entity);
        return "market";
    }

    @ApiOperation("模板-删除")
    @GetMapping("/deleteById_th/{id}")
    public String deleteById_th(@PathVariable Long id) {
        this.marketSourceService.deleteById(id);
        return "market";
    }
    @ResponseBody
    @ApiOperation("查询")
    @PostMapping("/getList")
    public CommonResult<PageResult<MarketSourceEntity>> getList(@RequestBody BaseForm form){
        PageHelper.startPage(form.getPage(),form.getSize());
        List list=this.marketSourceService.selectList(new EntityWrapper<>());
        return CommonResult.okk(new PageResult<>(list));
    }
    @ResponseBody
    @ApiOperation("增加")
    @PostMapping("/insertItem")
    public CommonResult insertItem(@RequestBody MarketSourceEntity entity){
        //新增id
        entity.setGsId(null);
        this.marketSourceService.insert(entity);
        return CommonResult.ok();
    }
    @ResponseBody
    @ApiOperation("修改")
    @PostMapping("/updateItem")
    public CommonResult updateItem(@RequestBody MarketSourceEntity entity){
        this.marketSourceService.updateById(entity);
        return CommonResult.ok();
    }
    @ResponseBody
    @ApiOperation("删除")
    @GetMapping("/deleteById")
    public CommonResult updateItem( @RequestParam Long id) {
        this.marketSourceService.deleteById(id);
        return CommonResult.ok();
    }
}

