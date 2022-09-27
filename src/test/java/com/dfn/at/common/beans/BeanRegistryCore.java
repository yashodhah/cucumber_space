package com.dfn.at.common.beans;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanRegistryCore implements BeanRegistryI {
    private ClassPathXmlApplicationContext context;
    private static volatile BeanRegistryCore beanRegistryCore;

    @Override
    public void init(ClassPathXmlApplicationContext context) {
        this.context = context;
    }

    public static BeanRegistryCore getInstance() {
        if (beanRegistryCore == null) {
            synchronized (BeanRegistryCore.class) {
                if (beanRegistryCore == null) {
                    beanRegistryCore = new BeanRegistryCore();
                }
            }
        }

        return beanRegistryCore;
    }

    public Object getBean(Class clz) {
        String beanName = beanNamePreProcess(clz);
        return getBean(beanName);
    }

    public Object getBean(String name) {
        return context.getBean(name);
    }

    public Object getBean(Class clz, Object... args) {
        String beanName = beanNamePreProcess(clz);
        return context.getBean(beanName, args);
    }

    private String beanNamePreProcess(Class clz) {
        return clz.getSimpleName();
    }
}
