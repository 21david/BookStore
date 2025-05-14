package com.pluralsight.bookstore.web;

import javax.servlet.ServletContext;

public class TemplateEngineUtil {
    private static final String TEMPLATE_ENGINE_ATTR = "com.pluralsight.bookstore.web.TemplateEngineInstance";

    public static void storeTemplateEngine(ServletContext context, org.thymeleaf.TemplateEngine engine) {
        context.setAttribute(TEMPLATE_ENGINE_ATTR, engine);
    }

    public static org.thymeleaf.TemplateEngine getTemplateEngine(ServletContext context) {
        return (org.thymeleaf.TemplateEngine) context.getAttribute(TEMPLATE_ENGINE_ATTR);
    }
}
