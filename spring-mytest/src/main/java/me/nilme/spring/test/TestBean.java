package me.nilme.spring.test;

/**
 * @author weizhuang
 * Created on 2019-09-09.
 */
public class TestBean {

	public String testStr = "testStr";


	public String getTestStr() {
		return testStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}

	public void test(){
		System.out.println(testStr);
	}
}
