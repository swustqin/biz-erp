package com.business.erp.service.system;

import com.business.erp.model.po.system.SysUser;
import com.business.erp.persistence.system.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    @Test
    public void findByName() throws Exception {
        SysUser tuchuntong = userService.findUser("admin");
        log.info(tuchuntong.toString());
        Assert.assertNotNull(tuchuntong);
    }

    @Transactional
    @Test
    public void save() throws Exception {
        SysUser user = userMapper.getUser("admin");
        user.setId("2");
        user.setUsername("jaden");
        user.setPassword("jaden");
        user.setEmail("526195212@qq.com");
        user.setEntryId(0);
        user.setMobile("18160017836");
        user.setName("秦正");
        user.setUpdateId(0);
        SysUser save = userService.save(user);
        log.info(save.toString());
        Assert.assertNotNull(save);
    }

}