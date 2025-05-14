package com.pluralsight.bookstore.web;

import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ThymeLeafConfiguration implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        TemplateEngineUtil.storeTemplateEngine(sce.getServletContext(), templateEngine(sce));
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

    private org.thymeleaf.TemplateEngine templateEngine(ServletContextEvent sce) {
        org.thymeleaf.TemplateEngine engine = new org.thymeleaf.TemplateEngine();
        engine.setTemplateResolver(templateResolver(sce));
        return engine;
    }

    private ITemplateResolver templateResolver(ServletContextEvent sce) {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(sce.getServletContext());
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }
}
