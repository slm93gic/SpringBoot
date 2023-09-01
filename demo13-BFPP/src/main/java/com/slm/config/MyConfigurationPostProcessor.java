package com.slm.config;

import com.slm.entity.ProcessorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;


/**
 * @author limin shen
 * @description 然后借助 Spring 的包扫描器 ClassPathBeanDefinitionScanner 进行扫描，
 * 此时我们的 MyConfigurationPostProcessor1 就有点类似于简化版的 ConfigurationClassPostProcessor
 * 后置处理器，都是在 postProcessBeanDefinitionRegistry() 方法中进行操作的，都实现了接口
 * BeanDefinitionRegistryPostProcessor ，只不过 Spring 提供内置的扫描功能比较全面，
 * 我们做的比较简陋而已，就只模拟扫描自定义注
 * @date 2023-09-01 15:07
 */
@Slf4j
@Component
public class MyConfigurationPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.info("MyConfigurationPostProcessor1---postProcessBeanDefinitionRegistry");
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(ProcessorEntity.class);
        MutablePropertyValues propertyValues = genericBeanDefinition.getPropertyValues();
        // 修改 ProcessorEntity 类中 name 属性默认值
        propertyValues.addPropertyValue("name", "小明");

        // 然后再将 BeanDefinition 注册到 BeanFactory 容器中
        registry.registerBeanDefinition("processorEntity", genericBeanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("MyConfigurationPostProcessor1---postProcessBeanFactory");
    }
}
