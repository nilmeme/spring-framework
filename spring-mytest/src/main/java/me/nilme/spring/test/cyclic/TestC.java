package me.nilme.spring.test.cyclic;

/**
 * @author weizhuang
 * Created on 2019-09-03.
 */
public class TestC {

	private TestA testA;

	public TestC(TestA testA) {
		this.testA = testA;
	}

	public void c() {
		testA.a();
	}

	public TestA getTestA() {
		return testA;
	}

	public void setTestA(TestA testA) {
		this.testA = testA;
	}


}
