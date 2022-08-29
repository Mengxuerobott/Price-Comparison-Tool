package com.market.controller.admin;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.market.entity.CommentEntity;
import com.market.entity.DicEntity;
import com.market.form.BaseForm;
import com.market.service.DicService;
import com.market.vo.CommonResult;
import com.market.vo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Api("数据字典")
@Controller
@RequestMapping("/dic")
public class DicController {
    private DicService dicService;

    @Autowired
    public DicController(DicService dicService) {
        this.dicService = dicService;
    }


    @PostMapping("/getDicById")
    @ResponseBody
    public DicEntity getUserById(String id) {
        DicEntity entity = this.dicService.selectById(id);
        return entity;
    }

    //    管理系统主页
    @ApiOperation("模板-查询")
    @GetMapping("/getList_th")
    public String getList_th(Model model,@RequestParam(required = false,defaultValue = "1") Integer page,
                             @RequestParam(required = false,defaultValue = "10") Integer size) {
        com.github.pagehelper.PageHelper.startPage(page,size);
        List<DicEntity> list = this.dicService.selectList(new EntityWrapper<>());
        model.addAttribute("list", new PageResult<>(list));
        return "dic";
    }

    @ApiOperation("模板-增加")
    @PostMapping("/insertItem_th")
    public String insertItemTh(DicEntity entity) {
        //新增id
        entity.setDicId(null);
        this.dicService.insert(entity);
        return "redirect:/dic/getList_th";
    }

    @ApiOperation("模板-修改")
    @PostMapping("/updateItem_th")
    public String updateItemTh(DicEntity entity) {
        this.dicService.updateById(entity);
        return "redirect:/dic/getList_th";
    }

    @ApiOperation("模板-删除")
    @GetMapping("/deleteById/{id}")
    public String updateItemTh(@PathVariable Long id) {
        this.dicService.deleteById(id);
        return "redirect:/dic/getList_th";
    }


    @ResponseBody
    @ApiOperation("查询")
    @PostMapping("/getList")
    public CommonResult<PageResult<DicEntity>> getList(@RequestBody BaseForm form) {
        PageHelper.startPage(form.getPage(), form.getSize());
        List list = this.dicService.selectList(new EntityWrapper<>());
        return CommonResult.okk(new PageResult<>(list));
    }

    @ResponseBody
    @ApiOperation("增加")
    @PostMapping("/insertItem")
    public CommonResult insertItem(@RequestBody DicEntity entity) {
        //新增id
        entity.setDicId(null);
        this.dicService.insert(entity);
        return CommonResult.ok();
    }

    @ResponseBody
    @ApiOperation("修改")
    @PostMapping("/updateItem")
    public CommonResult updateItem(@RequestBody DicEntity entity) {
        this.dicService.updateById(entity);
        return CommonResult.ok();
    }

    @ResponseBody
    @ApiOperation("删除")
    @GetMapping("/deleteById")
    public CommonResult updateItem( @RequestParam Long id) {
        this.dicService.deleteById(id);
        return CommonResult.ok();
    }

}

