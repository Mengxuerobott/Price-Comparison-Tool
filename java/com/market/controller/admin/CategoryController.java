package com.market.controller.admin;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.market.entity.CategoryEntity;
import com.market.entity.UserEntity;
import com.market.service.CategoryService;
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
 * 分类表 前端控制器
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Api("商品分类")
@Controller
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/getCategoryById")
    @ResponseBody
    public CategoryEntity getUserById(String id){
        CategoryEntity entity=this.categoryService.selectById(id);
        return entity;
    }

    //    管理系统主页
    @ApiOperation("模板-查询")
    @GetMapping("/getList_th")
    public String getList_th(Model model,@RequestParam(required = false,defaultValue = "1") Integer page,
                             @RequestParam(required = false,defaultValue = "10") Integer size) {
        PageHelper.startPage(page,size);
        List<CategoryEntity> list = this.categoryService.selectList(new EntityWrapper<>());
        model.addAttribute("list", new PageResult<>(list));
        return "category";
    }

    @ApiOperation("模板-增加")
    @PostMapping("/insertItem_th")
    public String insertItemTh(CategoryEntity entity){
        //新增id
        entity.setGcId(null);
        this.categoryService.insert(entity);
        return "redirect:/category/getList_th";
    }

    @ApiOperation("模板-修改")
    @PostMapping("/updateItem")
    public String updateItem(CategoryEntity entity){
        this.categoryService.updateById(entity);
        return "redirect:/category/getList_th";
    }

    @ApiOperation("模板-删除")
    @GetMapping("/deleteById/{id}")
    public String updateItem(@PathVariable Long id) {
        this.categoryService.deleteById(id);
        return "redirect:/category/getList_th";
    }

}

