package com.yh.common.lark.generator.provider.java.model;

import junit.framework.TestCase;

public class JavaClassTest extends TestCase {

	public void test() {
		JavaClass c = new JavaClass(JavaClass.class);
		assertEquals(c.getClassName(), "JavaClass");
	}

}
