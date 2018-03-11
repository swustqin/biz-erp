package com.business.erp.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interceptor of login
 *
 * @author tuchuntong and jadenQin
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {

    private Logger log = LoggerFactory.getLogger(BaseInterceptor.class);


    /**
     * This method is called before performing controller*
     * returns true said to continue
     * return false to suspend execution*
     * here you can join the login check, permissions, interception, etc
     * <p>
     * controller执行前调用此方法
     * 返回true表示继续执行，返回false中止执行
     * 这里可以加入登录校验、权限拦截等
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return The result of preHandle for interceptor
     * @throws Exception The exception thrown when dealing with intercept logic
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String path = request.getContextPath();
        String basePath = scheme + "://" + serverName + ":" + port + path;
        request.setAttribute("basePath", basePath);
        log.info("absolute path is " + basePath);
        return true;
    }

    /**
     * After performing controller before but did not return to view this method is called*
     * here for the model data are processed before returning to the user,
     * such as here to join the public information for page displays
     * <p>
     * controller执行后但未返回视图前调用此方法
     * 这里可在返回用户前对模型数据进行加工处理，比如这里加入公用信息以便页面显示
     *
     * @param request      request
     * @param response     response
     * @param handler      handler
     * @param modelAndView modelAndView
     * @throws Exception The exception thrown when dealing with intercept logic
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * controller执行后且视图返回后调用此方法
     * 这里可得到执行controller时的异常信息
     * 这里可记录操作日志，资源清理等
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @param ex       ex
     * @throws Exception The exception thrown when dealing with intercept logic
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }


}
