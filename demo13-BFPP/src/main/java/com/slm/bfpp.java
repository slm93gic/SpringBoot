//package com.slm;
//
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.core.Ordered;
//import org.springframework.core.PriorityOrdered;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * @author limin shen
// * @description TODO
// * @date 2023-09-01 15:39
// */
//public class bfpp {
//
//
//    public static void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {
//
//        // 因为第一过来的时候 TODO beanFactoryPostProcessors 这个变量不知道什么时候才会传入值，目前看是空集合，有待调试
//        Set<String> processedBeans = new HashSet<>();
//
//        // beanFactory 实现了 BeanDefinitionRegistry 注册接口，所以这里条件成立
//        if (beanFactory instanceof BeanDefinitionRegistry) {
//            BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
//            List<BeanFactoryPostProcessor> regularPostProcessors = new ArrayList<>();
//            List<BeanDefinitionRegistryPostProcessor> registryProcessors = new ArrayList<>();
//            for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessors) {
//                if (postProcessor instanceof BeanDefinitionRegistryPostProcessor) {
//                    BeanDefinitionRegistryPostProcessor registryProcessor = (BeanDefinitionRegistryPostProcessor) postProcessor;
//                    registryProcessor.postProcessBeanDefinitionRegistry(registry);
//                    registryProcessors.add(registryProcessor);
//                } else {
//                    regularPostProcessors.add(postProcessor);
//                }
//            }
//
//            List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();
//
//            /**
//             * 获取所有实现了 BeanDefinitionRegistryPostProcessor 接口的子类
//             * 并且判断是否还实现了 PriorityOrdered 权重接口，如果实现了则优先回调该类的 postProcessBeanDefinitionRegistry() 方法
//             * 目前看如果是以注解方式启动，Spring 其中有一个内置类实现了该接口，就是 ConfigurationClassPostProcessor 该配置类是在
//             * 外面的 this() 构造函数中直接把 BeanDefinition 注册到了 BeanDefinitionMap 容器中,其中还有 @AutowiredPostProcessor
//             * 所以这里优先调用的是这个扫描后置处理类的方法，然后去调用 doScan() 扫描其他组件
//             * 所以下面如果在重新调用这个 getBeanNamesForType() 方法，可能就会多出很多的子类了
//             * 因为 ConfigurationClassPostProcessor 后之类已经把所有的类扫描进来了
//             */
//            String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
//            for (String ppName : postProcessorNames) {
//                // 是否实现了 PriorityOrdered 排序接口
//                if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
//                    // 调用 getBean() 直接实例化这个子类
//                    currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
//                    // 记录一下当前已经执行过的 Processor，避免重复调用
//                    processedBeans.add(ppName);
//                }
//            }
//            sortPostProcessors(currentRegistryProcessors, beanFactory);
//            registryProcessors.addAll(currentRegistryProcessors);
//            /**
//             * 循环回调 BeanDefinitionRegistryPostProcessor 所有实现子类的方法
//             * 目前这里看只会回调到 ConfigurationClassPostProcessor 子类中的方法
//             * 这个类回调到 doScan() 扫描其他组件，然后注册到 BeanDefinitionMap 容器中，所以下面在执行 getBeanNamesForType()
//             * 方法时肯定会多出很多 BeanDefinitionRegistryPostProcessor 的子类实现
//             */
//            invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry, beanFactory.getApplicationStartup());
//            currentRegistryProcessors.clear();
//
//
//            /**
//             * 再次去检索一下 BeanDefinitionRegistryPostProcessor 接口的实现子类，此时应该是会更多或者是一样
//             * 此时如果我们扩展了这个接口，比较交给了 Spring 管理，那么这里肯定是能够扫描出来的
//             * 如果还实现了 Ordered 接口的话，那么下面这段代码就会会调用方法 postProcessBeanDefinitionRegistry()
//             */
//            postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
//            for (String ppName : postProcessorNames) {
//                // 是否实现了 Ordered 排序接口
//                if (!processedBeans.contains(ppName) && beanFactory.isTypeMatch(ppName, Ordered.class)) {
//                    // 调用 getBean() 直接实例化这个子类
//                    currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
//                    // 记录一下当前已经执行过的 Processor，避免重复调用
//                    processedBeans.add(ppName);
//                }
//            }
//            sortPostProcessors(currentRegistryProcessors, beanFactory);
//            registryProcessors.addAll(currentRegistryProcessors);
//            /**
//             * 执行过的就不会再回调了，因为 processedBeans 标记好了哪些执行过的，这里会回调我们自己扩展的 Processor 子类
//             */
//            invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry, beanFactory.getApplicationStartup());
//            currentRegistryProcessors.clear();
//
//
//            /**
//             * 最后，再次检索 BeanDefinitionRegistryPostProcessor，然后处理没有实现 PriorityOrdered、Ordered 接口的子类实现
//             */
//            boolean reiterate = true;
//            while (reiterate) {
//                reiterate = false;
//                postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
//                for (String ppName : postProcessorNames) {
//                    if (!processedBeans.contains(ppName)) {
//                        /**
//                         * 此处会通过 getBean() 去创建好这个后置处理器 Bean
//                         */
//                        currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
//                        processedBeans.add(ppName);
//                        reiterate = true;
//                    }
//                }
//                sortPostProcessors(currentRegistryProcessors, beanFactory);
//                registryProcessors.addAll(currentRegistryProcessors);
//                /** 调用 postProcessBeanDefinitionRegistry() 方法 */
//                invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry, beanFactory.getApplicationStartup());
//                currentRegistryProcessors.clear();
//            }
//
//            /**
//             * 上面一大段逻辑都是去调用 BeanDefinitionRegistryPostProcessor 类子类的 postProcessBeanDefinitionRegistry()
//             * 的注册方法。
//             * 最后在调用 BeanDefinitionRegistryPostProcessor 从父类继承过来的 postProcessBeanFactory() 方法
//             */
//            invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);
//            /**
//             * 一般这里是没有值的，无需注重
//             * 调用从入参中传入过来的 BeanDefinitionRegistryPostProcessor 对象的方法
//             */
//            invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory);
//
//        } else {
//            // TODO 这个应该是测试代码，只有一个地方调用了，就是 Test
//            invokeBeanFactoryPostProcessors(beanFactoryPostProcessors, beanFactory);
//        }
//
//
//        //=========================================================================================
//        // 这下面的逻辑全部都是调用 BeanFactoryPostProcessor 的 postProcessBeanFactory() 方法
//        //=========================================================================================
//
//
//        /** 获取到所有的  BeanFactoryPostProcessor beaFactory后置处理器*/
//        String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);
//
//
//        List<BeanFactoryPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
//        List<String> orderedPostProcessorNames = new ArrayList<>();
//        List<String> nonOrderedPostProcessorNames = new ArrayList<>();
//        for (String ppName : postProcessorNames) {
//            /**
//             * processedBeans 如果是执行过的 bean 后置处理器，就直接跳过 skip 不再处理
//             * 注意虽然代码会走到这些面，但是因为这里的 processedBeans 会拦截处理过的 BeanFactoryPostProcessor
//             * 这个细节注意哟
//             **/
//            if (processedBeans.contains(ppName)) {
//                // ...do nothing...
//            }
//            // 判断你是否实现了 PriorityOrdered 接口，实现这个接口可以将后置处理进行排序执行
//            // 其中 Ordered 数值越小优先级越高
//            else if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
//                priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanFactoryPostProcessor.class));
//            }
//            // 判断你是否实现了注解方式的优先级，和 PriorityOrdered 接口功能是一样的
//            else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
//                orderedPostProcessorNames.add(ppName);
//            } else {
//                // 就上述两种处理器，其他的都是普通的 bean 工厂后置处理器
//                nonOrderedPostProcessorNames.add(ppName);
//            }
//        }
//
//        // gwm_tag: 最先调用实现了 PriorityOrdered 接口的 beanFactoryPostProcessor 处理器
//        // 处理器进行排序
//        sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
//        // 开始调用实现了 PriorityOrdered 接口的 bean 工厂处理器的 postProcessBeanFactory() 方法
//        invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);
//
//        List<BeanFactoryPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
//        for (String postProcessorName : orderedPostProcessorNames) {
//            orderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
//        }
//        // 处理器进行排序
//        sortPostProcessors(orderedPostProcessors, beanFactory);
//        // gwm_tag: 第二调用实现了 Ordered 注解的 bean 工厂后置处理器的 postProcessBeanFactory() 方法
//        invokeBeanFactoryPostProcessors(orderedPostProcessors, beanFactory);
//
//        List<BeanFactoryPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
//        for (String postProcessorName : nonOrderedPostProcessorNames) {
//            nonOrderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
//        }
//        // gwm_tag: 最后才是调用普通的 bean 工厂后置处理器的 postProcessBeanFactory() 方法
//        invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory);
//
//        // 最后清空缓存就是这个 Map 而已： Map<String, BeanDefinitionHolder> mergedBeanDefinitionHolders = new ConcurrentHashMap<>(256)
//        beanFactory.clearMetadataCache();
//    }
//
//
//}
