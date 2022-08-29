package com.market.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Elias on 2019/7/18
 */
@Controller("/")
public class ViewController {
    @GetMapping("/")
    public String main(){
        return "forward:/query/index";
    }

    @GetMapping("/login")
    public String admin(){
        return "forward:/user/login1";
    }
}
