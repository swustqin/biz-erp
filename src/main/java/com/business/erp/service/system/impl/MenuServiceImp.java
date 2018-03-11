package com.business.erp.service.system.impl;

import com.business.erp.base.Constant;
import com.business.erp.model.po.system.SysMenu;
import com.business.erp.model.po.system.SysUser;
import com.business.erp.persistence.system.mapper.SysMenuMapper;
import com.business.erp.service.system.MenuService;
import com.business.erp.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: TcT
 * Date: 2018/3/10.
 * Time: 下午12:01.
 * To change
 */
@Service
public class MenuServiceImp implements MenuService {

    @Autowired
    private UserService userService;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getUserMenuList(String userId) {
        //系统管理员，拥有最高权限
        SysUser userModel = userService.queryObject(userId);
        if (Constant.SUPER_ADMIN == userModel.getSuperManager()) {
            return queryNotButtonList();
        }
        //用户菜单列表
        List<SysMenu> menuList = sysMenuMapper.queryMenuUserList(userId);
        return menuList;
    }

    @Override
    public List<SysMenu> queryNotButtonList() {
        return sysMenuMapper.queryNotButtonList();
    }
}
