package com.market.controller.admin;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.market.entity.SpuSourceEntity;
import com.market.service.SpuSourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Random;

/**
 * Created by Elias on 2019/7/19
 */
@Controller
@RequestMapping("/analyse")
public class AnalyseController {
    private SpuSourceService spuSourceService;

    public AnalyseController(SpuSourceService spuSourceService) {
        this.spuSourceService = spuSourceService;
    }

    @GetMapping("/view")
    public String getView(){
        PageHelper.startPage(1,5);

        return "analyse";
    }
}
