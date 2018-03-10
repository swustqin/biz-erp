package com.business.erp;

import com.business.erp.model.bo.SysBaseInfo;
import com.business.erp.model.po.system.SysUser;
import com.business.erp.service.system.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * @author jadenQin
 */
@ServletComponentScan
@SpringBootApplication
//@EntityScan("com.business.erp.model.po")
@MapperScan("com.business.erp.persistence")
@EnableCaching
@EnableSwagger2
public class ErpApplication implements CommandLineRunner{

    private Logger log = LoggerFactory.getLogger(ErpApplication.class);

    @Resource
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("The erp system init successfully!");
        SysUser tuchuntong = userService.findByAccount("tuchuntong");
        SysBaseInfo tuchuntong1 = userService.queryBaseInfo("tuchuntong");
        log.info("Boot complete!");
    }
}


