package com.example.readword.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Author: WangGQ
 * @Date: 2020/7/6 20:08
 * @Desc:
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    static ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        return (T) ac.getBean(beanName);
    }

    public static <T> T getBean(Class c) {
        return (T) ac.getBean(c);
    }


    public static Environment getEnvironment() {
        return ac.getEnvironment();
    }


    //取容器中的zhi
    public static <T> T getEnvironmentValue(String k) {
        return (T) getEnvironment().getProperty(k);
    }


    public static <T> T getProperty(String k) {
        return (T) getEnvironment().getProperty(k);
    }

}
