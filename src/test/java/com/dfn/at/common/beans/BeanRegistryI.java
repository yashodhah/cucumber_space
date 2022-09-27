package com.dfn.at.common.beans;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public interface BeanRegistryI {
    void init(ClassPathXmlApplicationContext context);
}
