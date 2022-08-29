package com.market.controller.admin;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.market.entity.CategoryEntity;
import com.market.entity.SpuEntity;
import com.market.entity.SpuSourceEntity;
import com.market.entity.UserEntity;
import com.market.form.BaseForm;
import com.market.service.SpuSourceService;
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
 * 商品来源表 前端控制器
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Api("比价商品来源")
@Controller
@RequestMapping("/spuSource")
public class SpuSourceController {
    private SpuSourceService spuSourceService;

    @Autowired
    public SpuSourceController(SpuSourceService spuSourceService) {
        this.spuSourceService = spuSourceService;
    }


    //    管理系统主页
    @ApiOperation("模板-查询")
    @GetMapping("/getList_th")
    public String getList_th(Model model,@RequestParam(required = false,defaultValue = "1") Integer page,
                             @RequestParam(required = false,defaultValue = "10") Integer size) {
        PageHelper.startPage(page,size);
        List<SpuSourceEntity> list = this.spuSourceService.selectList(new EntityWrapper<>());
        model.addAttribute("list", new PageResult<>(list));
        return "spu_source";
    }

    @PostMapping("/getSpuSourceById")
    @ResponseBody
    public SpuSourceEntity getSpuSourceById(String id){
        SpuSourceEntity entity=this.spuSourceService.selectById(id);
        return entity;
    }

    @ApiOperation("模板-增加")
    @PostMapping("/insertItem_th")
    public String insertItemTh(SpuSourceEntity entity) {
        //新增id
        entity.setGssId(null);
        this.spuSourceService.insert(entity);
        return "redirect:/spuSource/getList_th";
    }

    @ApiOperation("模板-修改")
    @PostMapping("/updateItem_th")
    public String updateItemTh(SpuSourceEntity entity) {
        this.spuSourceService.updateById(entity);
        return "redirect:/spuSource/getList_th";
    }

    @ApiOperation("模板-删除")
    @GetMapping("/deleteById/{id}")
    public String deleteById_th(@PathVariable Long id) {
        this.spuSourceService.deleteById(id);
        return "redirect:/spuSource/getList_th";
    }

    @ResponseBody
    @ApiOperation("查询")
    @PostMapping("/getList")
    public CommonResult<PageResult<SpuSourceEntity>> getList(@RequestBody BaseForm form) {
        PageHelper.startPage(form.getPage(), form.getSize());
        List list = this.spuSourceService.selectList(new EntityWrapper<>());
        return CommonResult.okk(new PageResult<>(list));
    }
    @ResponseBody
    @ApiOperation("增加")
    @PostMapping("/insertItem")
    public CommonResult insertItem(@RequestBody SpuSourceEntity entity) {
        //新增id
        entity.setGssId(null);
        this.spuSourceService.insert(entity);
        return CommonResult.ok();
    }
    @ResponseBody
    @ApiOperation("修改")
    @PostMapping("/updateItem")
    public CommonResult updateItem(@RequestBody SpuSourceEntity entity) {
        this.spuSourceService.updateById(entity);
        return CommonResult.ok();
    }
    @ResponseBody
    @ApiOperation("删除")
    @GetMapping("/deleteById")
    public CommonResult updateItem( @RequestParam Long id) {
        this.spuSourceService.deleteById(id);
        return CommonResult.ok();
    }


}

