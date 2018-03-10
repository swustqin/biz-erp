package com.business.erp.config;

import com.business.erp.base.ErpShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroFilterFactoryBean 处理拦截资源文件问题。
 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
 * Filter Chain定义说明
 * 1、一个URL可以配置多个Filter，使用逗号分隔
 * 2、当设置多个过滤器时，全部验证通过，才视为通过
 * 3、部分过滤器可指定参数，如perms，roles
 *
 * @author tuchuntong
 */
@Configuration
public class ShiroConfig {

    private Logger log = LoggerFactory.getLogger(ShiroConfig.class);
    private final static String SHIRO_FILTER = "shiroFilter";

    @Bean(name = SHIRO_FILTER)
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 未认证,跳转,如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/perLogin");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 认证没权限跳未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/resources/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/views/system/perLogin.htm*", "anon");//*表示可能会有参数
        filterChainDefinitionMap.put("/sys/login*", "anon");
        filterChainDefinitionMap.put("/perLogin*", "anon");
        filterChainDefinitionMap.put("/services/**", "anon");//WS接口放行
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout*", "logout");

        //filterChainDefinitionMap.put("/add", "perms[权限添加]");

        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        log.info("Shiro intercptor factory regitered successfully!");
        return shiroFilterFactoryBean;
    }


    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     * <p>
     * 在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理。因为在Shiro中，
     * 最终是通过Realm来获取应用程序中的用户、角色及权限信息的。通常情况下，在Realm中会直接从我们的数据源中获取Shiro需要的验证信息。
     * 可以说，Realm是专用于安全框架的DAO.
     * Shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo(token)方法。
     */
    @Bean(name = "erpShiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public ErpShiroRealm deafultShiroRealm() {
        ErpShiroRealm erpShiroRealm = new ErpShiroRealm();
        return erpShiroRealm;
    }

    /**
     * EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，
     * 然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
     */
    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        return new EhCacheManager();
    }


    /**
     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
     *
     * @return SecurityManager
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(deafultShiroRealm());
        //设置缓存
        //        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }


    /**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
        aASA.setSecurityManager(securityManager());
        return aASA;
    }


    /**
     * Register to web.xml, that means as following
     * <filter>
     * <filter-name>shiroFilter</filter-name>
     * <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
     * </filter>
     * <filter-mapping>
     * <filter-name>shiroFilter</filter-name>
     * <url-pattern>/*</url-pattern>
     * </filter-mapping>
     * <p>
     * <p>
     * 很重要,第一配置
     * spring整合shiro出现UnavailableSecurityManagerException在网上查出的问题都是没有配置DelegatingFilterProxy或者DelegatingFilterProxy的配置顺序错了，
     * 对应的解决办法就是在web.xml上添加DelegatingFilterProxy。
     * <p>
     * 通过delegatingFilterProxy"委派代理"注入参数"shiro的过滤链对象",交给FileRegistration注册器管理
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.addUrlPatterns("/*");
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName(SHIRO_FILTER); // Get the bean which the name of SHIRO_FILTER
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    /**
     * Shiro life cycle processor
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * HashedCredentialsMatcher，这个类是为了对密码进行编码的，
     * 防止密码在数据库里明码保存，当然在登陆认证的时候，
     * 这个类也负责对form里输入的密码进行编码。
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }


    /**
     * 开启shiro注解模式
     * <p>
     * 传统springAOP配置,通过spring的DefaultAdvisorAutoProxyCreator,为bean创建shiro的代理对象Advisor,就会检查有没有这个注解
     * <p>
     * 注解 @RequiresPermissions("key_word") 需要的权限,keyword对应数据库字段
     * <p>
     * 代理AOP针对接口管理事务,扫描不到权限注解,所以需要在
     * "注解事务配置参数":,proxy-target-class=true
     * 另外JPA最好也配置事务管理Bean,JPATransactionManager
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator proxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);//针对类进行注解AOP代理
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor advisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }


}
