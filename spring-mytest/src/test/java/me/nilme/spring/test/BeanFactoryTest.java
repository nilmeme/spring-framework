package me.nilme.spring.test;

import me.nilme.spring.test.aware.AwareTest;
import me.nilme.spring.test.cyclic.TestA;
import me.nilme.spring.test.event.TestEvent;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author weizhuang
 * Created on 2019-08-26.
 */
public class BeanFactoryTest {


	@Test
	public void testBeanfactoryLoad() {

		//即使加载bean
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanFactoryTest.xml");
		//懒加载bean
//		BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
//		MyTestBean myTestBean = (MyTestBean) bf.getBean("myTestBean");
//
//		AwareTest test = (AwareTest) bf.getBean("test");
//		test.testAware();
//		User user = (User) bf.getBean("user");
//		Assert.assertEquals("testStr", myTestBean.getTestStr());
//		Assert.assertEquals("aaa", user.getUserName());

		//1直接通过容器访问国际化信息
		Object[] params = {"John", new GregorianCalendar().getTime()};
		String strl = applicationContext.getMessage("testmsg", params, Locale.US);
		String str2 = applicationContext.getMessage("testmsg", params, Locale.CHINA);
		System.out.println(strl);
		System.out.println(str2);

//		TestA testA = (TestA) bf.getBean("testA");
//		testA.a();

		///////////////////------------------
		TestEvent event = new TestEvent ("hello","msg");
		applicationContext.publishEvent(event);


		///////////////////------------------
		TestBean bean= (TestBean) applicationContext.getBean ("test");
		bean.test() ;

	}


}
