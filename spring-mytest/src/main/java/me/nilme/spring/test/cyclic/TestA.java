package me.nilme.spring.test.cyclic;

/**
 * @author weizhuang
 * Created on 2019-09-03.
 */
public class TestA {

	private TestB testB;

	public TestA(TestB testB) {
		this.testB = testB;
	}

	public void a(){
		testB.b();
	}

	public TestB getTestB() {
		return testB;
	}

	public void setTestB(TestB testB) {
		this.testB = testB;
	}
}
