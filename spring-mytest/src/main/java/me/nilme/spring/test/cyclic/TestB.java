package me.nilme.spring.test.cyclic;

/**
 * @author weizhuang
 * Created on 2019-09-03.
 */
public class TestB {

	private TestC testC;

	public TestB(TestC testC) {
		this.testC = testC;
	}

	public void b() {
		testC.c();
	}

	public TestC getTestC() {
		return testC;
	}

	public void setTestC(TestC testC) {
		this.testC = testC;
	}
}
