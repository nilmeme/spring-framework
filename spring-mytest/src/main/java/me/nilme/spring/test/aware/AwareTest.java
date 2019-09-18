package me.nilme.spring.test.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author weizhuang
 * Created on 2019-09-05.
 */
public class AwareTest implements BeanFactoryAware {

	private BeanFactory beanFactory;


	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}


	public void testAware(){
		Hello hello = (Hello)beanFactory.getBean("hello");
		hello.say();
	}
}
