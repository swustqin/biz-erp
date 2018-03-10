package com.business.erp.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author jaden qin
 */
@Component
public class SpringIocSupport extends AbstractBaseSupport implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringIocSupport.applicationContext == null) {
            SpringIocSupport.applicationContext = applicationContext;
        }
    }

    /**
     * Get the spring Ioc manager of application context
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * Get spring service component with bean name
     *
     * @param name The name of bean
     * @return spring service component
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * Get spring service component with bean class object
     *
     * @param clazz spring service component
     * @param <T>   The class of spring service component
     * @return spring service component
     */
    public static <T> T getBean(Class<T> clazz) {
        if (applicationContext == null) {
            return null;
        }
        return getApplicationContext().getBean(clazz);
    }

    /**
     * Get spring service component by manually specified
     *
     * @param name  spring service children target component
     * @param clazz The class of spring service component
     * @param <T>   The class of spring service component
     * @return spring service component
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}