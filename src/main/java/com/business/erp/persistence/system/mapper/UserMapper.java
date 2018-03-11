package com.business.erp.persistence.system.mapper;

import com.business.erp.model.po.system.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Select("select * from sys_user where username = #{name}")
    SysUser getUser(@Param("name") String username);

    @Delete("DELETE FROM sys_user WHERE id =#{id}")
    void delete(Long id);

    @Select("select * from sys_user where id = #{userId}")
    SysUser queryObject(String userId);

//    @Select("SELECT ID,USER_NAME FROM SYS_USER  WHERE ACCOUNT = #{account}")
//    @Results({
//            @Result(property = "userId", column = "USER_ID"),
//            @Result(property = "userName", column = "USER_NAME")
//    })
//    SysBaseInfo queryBaseInfo(String userName);
}
