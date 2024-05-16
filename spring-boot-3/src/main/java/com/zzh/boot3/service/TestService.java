package com.zzh.boot3.service;

import com.zzh.boot3.event.ApplicationEventAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/1/14 18:17
 */
@Slf4j
@Service
public class TestService implements BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;


    public void test() {
        log.info("test method");
    }


    @EventListener
    public void eventListener(ApplicationEventAware applicationEventAware) {
        log.info("收到事件->{}", applicationEventAware);
    }


    // FactoryBean 获取示例会返回 FactoryBean 中的obj对象。
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }
    /**
     *   	protected Object getObjectForBeanInstance(
     * 			Object beanInstance, String name, String beanName, @Nullable RootBeanDefinition mbd) {
     *
     * 		// Don't let calling code try to dereference the factory if the bean isn't a factory.
     * 		if (BeanFactoryUtils.isFactoryDereference(name)) {
     * 			if (beanInstance instanceof NullBean) {
     * 				return beanInstance;
     *                        }
     * 			if (!(beanInstance instanceof FactoryBean)) {
     * 				throw new BeanIsNotAFactoryException(beanName, beanInstance.getClass());
     *            }
     * 			if (mbd != null) {
     * 				mbd.isFactoryBean = true;
     *            }
     * 			return beanInstance;* 		}
     *
     * 		// Now we have the bean instance, which may be a normal bean or a FactoryBean.
     * 		// If it's a FactoryBean, we use it to create a bean instance, unless the
     * 		// caller actually wants a reference to the factory.
     * 		if (!(beanInstance instanceof FactoryBean<?> factoryBean)) {
     * 			return beanInstance;
     *        }
     *
     * 		Object object = null;
     * 		if (mbd != null) {
     * 			mbd.isFactoryBean = true;
     *        }
     * 		else {
     * 			object = getCachedObjectForFactoryBean(beanName);
     *        }
     * 		if (object == null) {
     * 			// Return bean instance from factory.
     * 			// Caches object obtained from FactoryBean if it is a singleton.
     * 			if (mbd == null && containsBeanDefinition(beanName)) {
     * 				mbd = getMergedLocalBeanDefinition(beanName);
     *            }
     * 			boolean synthetic = (mbd != null && mbd.isSynthetic());
     * 			object = getObjectFromFactoryBean(factoryBean, beanName, !synthetic);
     *        }
     * 		return object;
     *    }
     * */
}
