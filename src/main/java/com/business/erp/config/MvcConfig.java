package com.business.erp.config;

import com.business.erp.base.BaseInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring MVC configuration
 *
 * @author tuchuntong and jadenQin
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public BaseInterceptor getLoginInterceptor() {
        return new BaseInterceptor();
    }

    /**
     * Add interceptors
     *
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Registered interceptor
        InterceptorRegistration registration = registry.addInterceptor(getLoginInterceptor());
        // Default DispatcherServlet intercept all requests including static (默认拦截所有请求包括静态)
        registration.addPathPatterns("/**");
        // To configure do NOT intercept path (配置不拦截的路径<!-- 登录、登出不拦截 -->)
        registration.excludePathPatterns("/messageAnnouncement/forPageUrl");
        registration.excludePathPatterns("/perLogin");
        registration.excludePathPatterns("/sys/login");
        registration.excludePathPatterns("/sessionOutLogin");

        super.addInterceptors(registry);
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //调用父类的配置
//        super.configureMessageConverters(converters);
//        //创建fastJson消息转换器
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        //创建配置类
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        //修改配置返回内容的过滤,null,空串等不参与序列
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteNullStringAsEmpty
//        );
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        //将fastjson添加到视图消息转换器列表内
//        converters.add(fastConverter);
//    }
//      注册XmlViewResolver，用于iReport & JasperReports报表生成
//    @Bean
//    public XmlViewResolver jasperReportResolver(){
//        XmlViewResolver resolver = new XmlViewResolver();
//        resolver.setOrder(0);
//       // resolver.setLocation(new PathResource("/WEB-INF/jasper-defs.xml"));
//        return resolver;
//    }
}
