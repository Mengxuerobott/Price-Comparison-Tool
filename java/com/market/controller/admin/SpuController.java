package com.market.controller.admin;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.market.entity.SpuEntity;
import com.market.entity.UserEntity;
import com.market.form.BaseForm;
import com.market.service.SpuService;
import com.market.vo.CommonResult;
import com.market.vo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 标准产品单元 前端控制器
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Api("标准产品单元spu")
@RestController
@RequestMapping("/spu")
public class SpuController {

    private SpuService spuService;

    @Autowired
    public SpuController(SpuService spuService) {
        this.spuService = spuService;
    }


    @ApiOperation("模板-查询")
    @GetMapping("/getList_th")
    public String getList_th(Model model,@RequestParam(required = false,defaultValue = "1") Integer page,
                             @RequestParam(required = false,defaultValue = "10") Integer size) {
        PageHelper.startPage(page,size);
        List<SpuEntity> list = this.spuService.selectList(new EntityWrapper<>());
        model.addAttribute("list", new PageResult<>(list));
        return "spu";
    }

    @ApiOperation("模板-增加")
    @PostMapping("/insertItem_th")
    public String insertItemTh(SpuEntity entity){
        //新增id
        entity.setSpuId(null);
        this.spuService.insert(entity);
        return "spu";
    }

    @ApiOperation("模板-修改")
    @PostMapping("/updateItem_th")
    public String updateItemTh(SpuEntity entity){
        this.spuService.updateById(entity);
        return "spu";
    }

    @ApiOperation("模板-删除")
    @GetMapping("/deleteById_th/{id}")
    public String deleteById_th(@PathVariable Long id) {
        this.spuService.deleteById(id);
        return "spu";
    }







    @ResponseBody
    @ApiOperation("查询")
    @PostMapping("/getList")
    public CommonResult<PageResult<SpuEntity>> getList(@RequestBody BaseForm form){
        PageHelper.startPage(form.getPage(),form.getSize());
        List list=this.spuService.selectList(new EntityWrapper<>());
        return CommonResult.okk(new PageResult<>(list));
    }
    @ResponseBody
    @ApiOperation("增加")
    @PostMapping("/insertItem")
    public CommonResult insertItem(@RequestBody SpuEntity entity){
        //新增id
        entity.setSpuId(null);
        this.spuService.insert(entity);
        return CommonResult.ok();
    }
    @ResponseBody
    @ApiOperation("修改")
    @PostMapping("/updateItem")
    public CommonResult updateItem(@RequestBody SpuEntity entity){
        this.spuService.updateById(entity);
        return CommonResult.ok();
    }
    @ResponseBody
    @ApiOperation("删除")
    @GetMapping("/deleteById")
    public CommonResult updateItem( @RequestParam Long id) {
        this.spuService.deleteById(id);
        return CommonResult.ok();
    }
}

