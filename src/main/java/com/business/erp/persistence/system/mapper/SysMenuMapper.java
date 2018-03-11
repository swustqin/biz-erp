package com.business.erp.persistence.system.mapper;

import com.business.erp.model.po.system.SysMenu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TcT
 * Date: 2018/3/10.
 * Time: 下午4:32.
 * To change
 */
@Repository
public interface SysMenuMapper {

    @Select("select * from sys_user where id = #{value}")
    List<SysMenu> findAll();

    @Select("select distinct m.*,(select p.name from sys_menu p where p.id = m.parent_id) as parentName"+
            " from sys_user_role ur" +
            " LEFT JOIN sys_role_menu rm on ur.role_id = rm.role_id" +
            " LEFT JOIN sys_menu m on rm.menu_id = m.id" +
            " where ur.user_id = #{userId} AND type != 2 order by m.order_num asc")
    List<SysMenu> queryMenuUserList(String userId);

    @Select("select * from sys_menu where type != 2 order by order_num asc ")
    List<SysMenu> queryNotButtonList();
}
