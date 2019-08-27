package me.nilme.spring.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author weizhuang
 * Created on 2019-08-26.
 */
public class BeanFactoryTest {


	@Test
	public void testBeanfactoryLoad(){

		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
		MyTestBean myTestBean = (MyTestBean) bf.getBean("myTestBean");

		Assert.assertEquals("testStr", myTestBean.getTestStr());
	}


}
