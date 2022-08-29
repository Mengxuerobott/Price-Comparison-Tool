package com.market.controller.admin;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.market.dao.UserDao;
import com.market.entity.UserEntity;
import com.market.exception.ResultException;
import com.market.form.BaseForm;
import com.market.form.RegisterForm;
import com.market.service.UserService;
import com.market.utils.Constant;
import com.market.vo.CommonResult;
import com.market.vo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 平台用户表 前端控制器
 * </p>
 *
 * @author Elias
 * @since 2019-07-10
 */
@Api("用户")
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    private UserService userService;
    private UserDao userDao;

    @Autowired
    public UserController(UserService userService, UserDao userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    @GetMapping("/quickRegister/{phone}")
    public String quickRegister(@PathVariable String phone, Model model) {
        UserEntity entity = new UserEntity();
        entity.setUserPhone(phone);
        Integer count = this.userDao.getUserCount() + 1;
        entity.setUserNickname("visitor_" + count);
        entity.setUserEmail("");
        model.addAttribute("entity", entity);
        return "change-user-info";
    }

    @GetMapping("/error")
    public String goError() {
        return "error";
    }

    @GetMapping("/goRegister")
    @ApiOperation("前往注册页面")
    public String goRegister() {
        return "register";
    }

    @GetMapping("/login1")
    @ApiOperation("前往登陆页面")
    public String loginHtml() {
        return "login";
    }

    @PostMapping("/login")
    @ApiOperation("登陆")
    @Validated
    public String login(@RequestParam String phone, @RequestParam(defaultValue = "") String password,
                        @ApiIgnore HttpSession session, @RequestParam String type, Map<String, Object> map, HttpServletRequest request) {
        if (session.getAttribute(Constant.USER_KEY)!=null){
            session.invalidate();
            session=request.getSession();
        }
//第一次登录
        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_phone", phone);
        UserEntity userEntity = this.userService.selectOne(wrapper);
        System.out.println(userEntity);
        if (userEntity == null) {
            System.out.println("无此用户");
            map.put("errorInfo", Constant.ERROR_INFO_PHONE);
            return "login";
        } else if (userEntity.getUserPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            userEntity.setUserPassword(null);
//            在session中放入登录用户的个人信息
            session.setAttribute(Constant.USER_KEY, userEntity);
//            不失效
            session.setMaxInactiveInterval(0);
            return Constant.UserType.Admin_User.equals(userEntity.getUserType())
                    ? "redirect:/user/getList_th":"redirect:/query/index";
        } else {
            System.out.println("密码错误");
            map.put("errorInfo", Constant.ERROR_INFO_PASSWORD);
            return "redirect:/user/login1";
        }
    }
    //提交修改表单
    @PostMapping("/changeUserInfo")
    @ApiOperation("用户修改个人信息")
    public String changeUserInfo(RegisterForm registerForm, HttpSession session) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserPassword(DigestUtils.md5DigestAsHex(registerForm.getUserPassword().getBytes()));
        userEntity.setUserPhone(registerForm.getUserPhone());
        userEntity.setUserNickname(registerForm.getUserNickname());
        userEntity.setUserEmail(registerForm.getUserEmail());
        userEntity.setUserBirth(registerForm.getUserBirth());
        userEntity.setUserSex(registerForm.getUserSex());
        userEntity.update(new EntityWrapper<UserEntity>().
                eq("user_phone", registerForm.getUserPhone()));
        session.removeAttribute(Constant.USER_KEY);
//        重新登录
//        没有id，用phone查到个人信息
        EntityWrapper<UserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_phone", userEntity.getUserPhone());
        UserEntity entity = this.userService.selectOne(wrapper);
        session.setAttribute(Constant.USER_KEY, entity);
        if (Constant.UserType.Admin_User.equals(entity.getUserType())) {
            return "redirect:/user/getList_th";
        } else {
            return "redirect:/query/index";
        }
    }
    //管理平台->修改界面

    @GetMapping("/userInfo")
    public String changeUserInfo(Model model, HttpSession session) {
        UserEntity entity = (UserEntity) session.getAttribute(Constant.USER_KEY);
        if (entity == null) {
            return "redirect:/user/login1";
        }
        model.addAttribute("entity", entity);
        return "change-user-info";
    }


    @GetMapping("/logout")
    @ApiOperation("退出登陆")
    public String logout(@ApiIgnore HttpSession session) {
        session.invalidate();
        return "redirect:/user/login1";
    }

    @PostMapping("/register")
    @ApiOperation("注册")
    public String register(RegisterForm registerForm) {
        String password = DigestUtils.md5DigestAsHex(registerForm.getUserPassword().getBytes());
        UserEntity userEntity = new UserEntity();
        if (Constant.UserType.Normal_User.equals(registerForm.getRegisterType())) {
            //平台用户
            userEntity.setUserType(Constant.UserType.Normal_User);
        } else if (Constant.UserType.Admin_User.equals(registerForm.getRegisterType())) {
            //管理员账户
            userEntity.setUserType(Constant.UserType.Admin_User);
        } else {
            return "redirect:/user/goRegister";
        }

        userEntity.setUserPassword(password);
        userEntity.setUserPhone(registerForm.getUserPhone());
        userEntity.setUserNickname(registerForm.getUserNickname());
        userEntity.setUserEmail(registerForm.getUserEmail());
        userEntity.setUserBirth(registerForm.getUserBirth());
        userEntity.setUserSex(registerForm.getUserSex());
        this.userService.insert(userEntity);
        return "redirect:/user/login1";
    }


    @GetMapping("/getLoginInfo")
    @ApiOperation("获取登陆后的用户信息")
    public UserEntity getUserInfo(@SessionAttribute @ApiIgnore UserEntity userEntity) {
        if (userEntity == null) throw new ResultException("登陆失效");
        return userEntity;
    }

    //    数据回显 ajax
    @PostMapping("/getUserById")
    @ResponseBody
    public UserEntity getUserById(long id) {
        UserEntity entity = this.userService.selectById(id);
        return entity;
    }

    //    管理系统主页
    @ApiOperation("模板-查询")
    @GetMapping("/getList_th")
    public String getList_th(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<UserEntity> list = this.userService.selectList(new EntityWrapper<>());
        model.addAttribute("list", new PageResult<>(list));
        return "user";
    }

    @ApiOperation("模板-增加")
    @PostMapping("/insertItem_th")
    public String insertItemTh(UserEntity entity) {
        //新增id
        entity.setUserId(null);
        entity.setUserType(Constant.UserType.Normal_User);
        this.userService.insert(entity);
        return "redirect:/user/getList_th";
    }

    @ApiOperation("模板-获取用户信息")
    @PostMapping("/getItemById/{id}")
    public String getItemById(@PathVariable Long id, Model model) {
        UserEntity userEntity = this.userService.selectById(id);
        model.addAttribute("user", userEntity);
        return "redirect:/user/getList_th";
    }


    @ApiOperation("模板-删除")
    @GetMapping("/deleteById/{id}")
    public String deleteItem(@PathVariable Long id) {
        this.userService.deleteById(id);
        return "redirect:/user/getList_th";
    }

    @ApiOperation("模板-更新| 管理系统管理账户信息，对记录进行修改")
    @PostMapping("/updateItem_th")
    public String updateItemTh(UserEntity entity) {
        this.userService.updateById(entity);
        return "redirect:/user/getList_th";
    }

    @ApiOperation("更新")
    @PostMapping("/updateItem")
    @ResponseBody
    public CommonResult updateItem(@RequestBody UserEntity entity) {
        System.out.println(entity.toString());
        this.userService.updateById(entity);
        return CommonResult.ok();
    }


    @ApiOperation("查询")
    @PostMapping("/getList")
    @ResponseBody
    public CommonResult<PageResult<UserEntity>> getList(@RequestBody BaseForm form) {
        PageHelper.startPage(form.getPage(), form.getSize());
        List list = this.userService.selectList(new EntityWrapper<>());
        return CommonResult.okk(new PageResult<>(list));
    }

    @ApiOperation("增加")
    @PostMapping("/insertItem")
    @ResponseBody
    public CommonResult insertItem(@RequestBody UserEntity entity) {
        //新增id
        entity.setUserId(null);
        this.userService.insert(entity);
        return CommonResult.ok();
    }
}

