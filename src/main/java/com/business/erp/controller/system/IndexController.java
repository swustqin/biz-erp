package com.business.erp.controller.system;

import com.business.erp.controller.AbstractCommonController;
import com.business.erp.model.po.system.SysMenu;
import com.business.erp.service.system.MenuService;
import com.business.erp.support.utils.ShiroUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController extends AbstractCommonController {

    @Resource
    private MenuService menuService;

    /**
     * 后台管理页面
     *
     * @return
     */
//    @RequestMapping("/index") //如果加/,则url后缀必须跟上/
//    public ModelAndView index(){
//        ModelAndView modelAndView = new ModelAndView("/system/menu/index");
//        return modelAndView;
//    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/system/menu/index");
        return view;
    }

    /**
     * 用户菜单列表
     */
    @RequestMapping("/user/menu")
    @ResponseBody
    public List<SysMenu> user() {
        return menuService.getUserMenuList(ShiroUtil.getUserId());
    }

    /**
     * 首页显示区
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView homePage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/system/menu/home");
        return view;
    }

    /**
     * 登录,1
     */
    @RequestMapping("/perLogin")
    public ModelAndView preLogin() {
        ModelAndView modelAndView = new ModelAndView("/system/perLogin");
        return modelAndView;
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("/sessionOutLogin")
    public String sessionOutLogin() {
        return "sessionOutLogin";
    }

}
