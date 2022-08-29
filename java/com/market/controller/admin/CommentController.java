package com.market.controller.admin;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.market.entity.BrandEntity;
import com.market.entity.CommentEntity;
import com.market.entity.SpuSourceEntity;
import com.market.form.BaseForm;
import com.market.service.CommentService;
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
 * 评论表  前端控制器
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Api("评论")
@Controller
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/getCommentById")
    @ResponseBody
    public CommentEntity getUserById(String id) {
        CommentEntity entity = this.commentService.selectById(id);
        return entity;
    }



    //    管理系统主页
    @ApiOperation("模板-查询")
    @GetMapping("/getList_th")
    public String getList_th(Model model,@RequestParam(required = false,defaultValue = "1") Integer page,
                             @RequestParam(required = false,defaultValue = "10") Integer size) {
        PageHelper.startPage(page,size);
        List<CommentEntity> list = this.commentService.selectList(new EntityWrapper<>());
        model.addAttribute("list", new PageResult<>(list));
        return "comment";
    }

    @ApiOperation("模板-新增")
    @PostMapping("/insertItem_th")
    public String insertItemTh(CommentEntity entity) {
        //新增id
        entity.setCommentId(null);
        this.commentService.insert(entity);
        return "redirect:/comment/getList_th";
    }

    @ApiOperation("模板-修改")
    @PostMapping("/updateItem_th")
    public String updateItemTh(CommentEntity entity) {
        this.commentService.updateById(entity);
        return "redirect:/comment/getList_th";
    }

    @ApiOperation("模板-删除")
    @GetMapping("/deleteById/{id}")
    public String updateItemTh(@PathVariable Long id) {
        this.commentService.deleteById(id);
        return "redirect:/comment/getList_th";
    }

    @ResponseBody
    @ApiOperation("查询")
    @PostMapping("/getList")
    public CommonResult<PageResult<CommentEntity>> getBrandList(@RequestBody BaseForm form) {
        PageHelper.startPage(form.getPage(), form.getSize());
        List list = this.commentService.selectList(new EntityWrapper<>());
        return CommonResult.okk(new PageResult<>(list));
    }

    @ResponseBody
    @ApiOperation("增加")
    @PostMapping("/insertItem")
    public CommonResult insertItem(@RequestBody CommentEntity entity) {
        //新增id
        entity.setCommentId(null);
        this.commentService.insert(entity);
        return CommonResult.ok();
    }

    @ResponseBody
    @ApiOperation("修改")
    @PostMapping("/updateItem")
    public CommonResult updateItem(@RequestBody CommentEntity entity) {
        this.commentService.updateById(entity);
        return CommonResult.ok();
    }

    @ResponseBody
    @ApiOperation("删除")
    @GetMapping("/deleteById")
    public CommonResult updateItem(@RequestParam Long id) {
        this.commentService.deleteById(id);
        return CommonResult.ok();
    }
}

