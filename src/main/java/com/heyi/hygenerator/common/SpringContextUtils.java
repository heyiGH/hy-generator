package com.heyi.hygenerator.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtils implements BeanFactoryPostProcessor {
    private static ConfigurableListableBeanFactory beanFactory = null;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        beanFactory = configurableListableBeanFactory;
    }

    public static ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public static void setBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        SpringContextUtils.beanFactory = beanFactory;
    }
}
