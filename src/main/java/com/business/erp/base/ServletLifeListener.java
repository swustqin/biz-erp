package com.business.erp.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Servlet life listener
 *
 * @author jadenQin
 */
@WebListener
public class ServletLifeListener implements ServletContextListener, HttpSessionListener {

    private Logger log = LoggerFactory.getLogger(ServletLifeListener.class);

    /**
     * Listening to create the servlet context
     *
     * @param servletContextEvent servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String serverInfo = servletContextEvent.getServletContext().getServerInfo();
        log.info("Create the servlet context");
        log.info(serverInfo);
    }

    /**
     * Listening to servlet context destroyed
     *
     * @param servletContextEvent servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("Destory the servlet context");
    }

    /**
     * Listening to the session created
     *
     * @param httpSessionEvent httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.info("Create the session");
    }

    /**
     * Listening to the session destroyed
     *
     * @param httpSessionEvent httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("Destory the session");
    }
}
