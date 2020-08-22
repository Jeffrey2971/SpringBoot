package com.jeffrey.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查，没有登录的用户不能直接访问后台页面
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    // 方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object user = httpServletRequest.getSession().getAttribute("loginUser");
        if (user == null){
            // 用户未登录，返回登录页面
            httpServletRequest.setAttribute("message", "请先登录！");
            httpServletRequest.getRequestDispatcher("/index.html").forward(httpServletRequest, httpServletResponse);
            return false;
        }else{
            // 用户已登录，放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
