package me.nilme.spring.test.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author weizhuang
 * Created on 2019-09-09.
 */
public class TestListener implements ApplicationListener {
	@Override
	public void onApplicationEvent(ApplicationEvent event) {

		if (event instanceof TestEvent){
			TestEvent testEvent = (TestEvent)event;
			testEvent.print();
		}

	}
}
