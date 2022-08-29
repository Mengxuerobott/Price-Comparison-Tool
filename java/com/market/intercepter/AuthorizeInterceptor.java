package com.market.intercepter;

import com.market.ApiApplication;
import com.market.dao.UserDao;
import com.market.entity.UserEntity;
import com.market.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Elias on 2019/4/20
 */
@Component
@Slf4j
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

    private UserDao userDao;

    @Autowired
    public AuthorizeInterceptor(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 允许跨域，允许跨域Cookie
        String origin = request.getHeader("Origin");
        if (null != origin) {
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", origin);
            // json数据类型需要明确指定允许的请求头
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        }

        String uri = request.getRequestURI();
        log.error(uri);
        if (uri.equals("/") || uri.equals("/query/index")) {
            return true;
        }

        HttpSession session = request.getSession();
        UserEntity userAccount = (UserEntity) session.getAttribute(Constant.USER_KEY);
        if (userAccount == null) {
//            未登录
//                如果是debug模式则放行
            if (ApiApplication.IS_DEBUG) {
                session.setAttribute(Constant.USER_KEY,
                        userDao.selectById(ApiApplication.DEBUG_USER_ID));
                return true;
            } else {
                if (!uri.startsWith("/query") && !uri.startsWith("/user")) {
                    System.out.println("未登录访问");
                    response.sendRedirect(request.getContextPath() + "/user/login1");
                    return true;
                } else {
                    if (uri.startsWith("/query/postComment")) {
                        System.out.println("未登录提交评论");
                        response.sendRedirect(request.getContextPath() + "/user/login1");
                    }else return true;
                }
            }
        } else {
//            已经登录
            if (Constant.UserType.Admin_User.equals(userAccount.getUserType())) {
                return true;
            } else if (Constant.UserType.Normal_User.equals(userAccount.getUserType())) {
//                普通用户访问前台接口或账户接口
                if (uri.startsWith("/query") || uri.startsWith("/user")) {
                    return true;
                } else {
                    log.error(userAccount.getUserId() + "：是普通用户，不可访问管理系统");
                    response.sendRedirect("/user/error");
                    return true;
                }
            }
        }
        return false;
    }


}
