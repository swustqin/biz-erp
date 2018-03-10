package com.business.erp.base;

import com.business.erp.model.bo.SysBaseInfo;
import com.business.erp.model.bo.User;

import javax.servlet.http.HttpSession;

/**
 * Thread local
 *
 * @author tuchuntong
 */
public class ContextThread {
    private static String realPath = "";//实际地址

    private static ThreadLocal<HttpSession> sessionThreadLocal = new ThreadLocal<HttpSession>();

    public static void setSession(HttpSession session) {
        ContextThread.sessionThreadLocal.set(session);
    }

    public static User getUser() {
        return (User) sessionThreadLocal.get().getAttribute("user");
    }

    public static SysBaseInfo getSysBaseInfo() {
        return (SysBaseInfo) sessionThreadLocal.get().getAttribute("baseInfo");
    }

    /**
     * The client get special session instance (客户端获得其对应的session实例)
     *
     * @return HttpSession
     */
    public static HttpSession getSession() {
        return ContextThread.sessionThreadLocal.get();
    }

    public static void setRealPath(String path) {
        if ("".equals(realPath)) {
            realPath = path;
        }
    }

    public static String getRealPath() {
        return realPath;
    }

}
