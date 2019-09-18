package me.nilme.spring.test;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author weizhuang
 * Created on 2019-09-02.
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		// 当前示例中只有支持<rnyname:user 的写法，你也可以在这里注册多个解析器，
		// 如<rnynarne:A、<rnyname:B 等 ，使得 rnynarne 的命 名空间中可以支持多种标签解析。
		registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
	}
}
