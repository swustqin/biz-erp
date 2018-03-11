package com.business.erp.service.system;


import com.business.erp.model.po.system.SysMenu;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TcT
 * Date: 2018/3/10.
 * Time: 上午11:49.
 * To change
 */
public interface MenuService {


    List<SysMenu> getUserMenuList(String userId);

    List<SysMenu> queryNotButtonList();
}
