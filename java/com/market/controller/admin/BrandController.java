package com.market.controller.admin;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.market.dao.BrandDao;
import com.market.entity.BrandEntity;
import com.market.entity.UserEntity;
import com.market.form.BaseForm;
import com.market.service.BrandService;
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
 * 品牌表 前端控制器
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Api("品牌")
@Controller
@RequestMapping("/brand")
public class BrandController {

    private BrandService brandService;
    private BrandDao brandDao;

    @Autowired
    public BrandController(BrandService brandService, BrandDao brandDao) {
        this.brandService = brandService;
        this.brandDao = brandDao;
    }

    //    管理系统主页
    @ApiOperation("模板-查询")
    @GetMapping("/getList_th")
    public String getList_th(Model model,@RequestParam(required = false,defaultValue = "1") Integer page,
                             @RequestParam(required = false,defaultValue = "10") Integer size) {
        PageHelper.startPage(page,size);
        List<BrandEntity> list = this.brandService.selectList(new EntityWrapper<>());
        model.addAttribute("list", new PageResult<>(list));
        return "brand";
    }

    @ApiOperation("模板-增加")
    @PostMapping("/insertItem_th")
    public String insertItemTh(BrandEntity entity) {
        //新增id
        entity.setGbId(null);
        this.brandService.insert(entity);
        return "redirect:/brand/getList_th";
    }

    @ApiOperation("模板-修改")
    @PostMapping("/updateItem_th")
    public String updateItemTh(@RequestBody BrandEntity entity) {
        this.brandService.updateById(entity);
        return "redirect:/brand/getList_th";
    }

    @ApiOperation("模板-删除")
    @GetMapping("/deleteById/{id}")
    public String updateItemTj(@PathVariable Long id) {
        this.brandService.deleteById(id);
        return "redirect:/brand/getList_th";
    }

    @ApiOperation("模板-获取单条信息")
    @ResponseBody
    @PostMapping("/getBrandInfoById")
    public BrandEntity getBrandInfoById(String id) {
        return this.brandService.selectById(id);
    }

    @ResponseBody
    @ApiOperation("查询")
    @PostMapping("/getList")
    public CommonResult<PageResult<BrandEntity>> getBrandList(@RequestBody BaseForm baseForm) {
        PageHelper.startPage(baseForm.getPage(), baseForm.getSize());
        List list = this.brandDao.getList();
        return CommonResult.okk(new PageResult<>(list));
    }

    @ResponseBody
    @ApiOperation("增加")
    @PostMapping("/insertItem")
    public CommonResult insertItem(@RequestBody BrandEntity entity) {
        //新增id
        entity.setGbId(null);
        this.brandService.insert(entity);
        return CommonResult.ok();
    }

    @ResponseBody
    @ApiOperation("修改")
    @PostMapping("/updateItem")
    public CommonResult updateItem(@RequestBody BrandEntity entity) {
        this.brandService.updateById(entity);
        return CommonResult.ok();
    }

    @ApiOperation("删除")
    @GetMapping("/deleteById")
    @ResponseBody
    public CommonResult updateItem( @RequestParam Long id) {
        this.brandService.deleteById(id);
        return CommonResult.ok();
    }


}

