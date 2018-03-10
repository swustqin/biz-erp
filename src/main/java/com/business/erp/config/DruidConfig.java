package com.business.erp.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * configure data source
 * options:
 * druidStatViewServlet() monitor page (配置监控界面)
 * druidWebStatViewFilter() (编写过滤器，配合druid监控的使用)
 * Access URL：http://localhost:8082/hr_new/druid/  (登录之后，即可查看数据源及SQL统计等)
 *
 * @author tuchuntong
 */
@Configuration
public class DruidConfig {
    private Logger log = LoggerFactory.getLogger(DruidConfig.class);

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setValidationQuery("select 1");

        log.info("Create druid datasource");
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP allowed users (白名单)
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP No access users (黑名单.共同存在时，deny优先于allow)
        //servletRegistrationBean.addInitParameter("deny","192.168.1.100");
        // ROOT manager (控制台管理用户)
        servletRegistrationBean.addInitParameter("loginUsername", "root");
        servletRegistrationBean.addInitParameter("loginPassword", "root");
        // The reset flag for data (是否能够重置数据)
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        log.info("To Register monioring bean for druid");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // Add filter strategy (添加过滤规则)
        filterRegistrationBean.addUrlPatterns("/*");
        // Ignore filer format (忽略过滤的格式)
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        log.info("To Register filter bean for druid");
        return filterRegistrationBean;
    }


}
