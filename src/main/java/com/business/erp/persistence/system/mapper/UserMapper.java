package com.business.erp.persistence.system.mapper;

import com.business.erp.model.bo.SysBaseInfo;
import com.business.erp.model.po.system.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Select("select * from SYS_USER where user_name = #{name}")
    SysUser getUser(@Param("name") String username);

    @Delete("DELETE FROM SYS_USER WHERE id =#{id}")
    void delete(Long id);

    @Select("SELECT USER_ID,ACCOUNT,USER_NAME FROM SYS_USER  WHERE ACCOUNT = #{account}")
    @Results({
            @Result(property = "userId", column = "USER_ID"),
            @Result(property = "account", column = "ACCOUNT"),
            @Result(property = "userName", column = "USER_NAME")
    })
    SysBaseInfo queryBaseInfo(String userName);
}
