package com.dfn.at.common.services;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextHandler {
    private static volatile ClassPathXmlApplicationContext context;

    public static ClassPathXmlApplicationContext init(String contextPath) {
        return initContext(contextPath);
    }

    public static ClassPathXmlApplicationContext initContext(String contextFileName) {
        if (context == null) {
            try {
                synchronized (SpringContextHandler.class) {
                    if (context == null) {
                        context = new ClassPathXmlApplicationContext(contextFileName);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return context;
    }
}
