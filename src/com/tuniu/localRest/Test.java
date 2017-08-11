package com.tuniu.localRest;

/**
 *
 * @Description: 这里用一句话描述这个类的作用
 * @author zhudongyang 
 * @date 2015-12-3 
 * @version 1.0
 */
public class Test {
	public static void main(String[] args) {
		String str1 = null;
		String str2 = "CESHI";
		Test test = new Test();
		test.excute(str1);
		test.excute(str2);
	}
	public void excute(String str){
		if("CESHI".equals(str)){
			System.out.println("excute  CESHI ");
		} else {
			System.out.println("excute  Other ");
		}
	}
}
