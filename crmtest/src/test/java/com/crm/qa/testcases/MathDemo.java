package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MathDemo {
	@Test
	public void sum() {
		System.out.println("SUM");
		int a=10;
		int b=20;
		Assert.assertEquals(30, a+b);
	}
	
	@Test
	public void division() {
		System.out.println("DIVISION");
		int a=10;
		int b=20;
		Assert.assertEquals(3, b/a);
	}

}
