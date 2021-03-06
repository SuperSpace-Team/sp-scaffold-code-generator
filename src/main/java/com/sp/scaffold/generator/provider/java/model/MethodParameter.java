package com.sp.scaffold.generator.provider.java.model;

import java.util.List;

import com.sp.scaffold.generator.util.StringHelper;

public class MethodParameter {
	int paramIndex = -1;
	JavaClass clazz;

	public MethodParameter(int paramIndex, JavaClass clazz) {
		super();
		this.paramIndex = paramIndex;
		this.clazz = clazz;
	}

	public String getName() {
		return StringHelper.uncapitalize(clazz.getClassName());
		//		return "param"+paramIndex;
	}

	public String getAsType() {
		return clazz.getAsType();
	}

	public String getClassName() {
		return clazz.getClassName();
	}

	public String getJavaType() {
		return clazz.getJavaType();
	}

	public String getPackageName() {
		return clazz.getPackageName();
	}

	public String getPackagePath() {
		return clazz.getPackagePath();
	}

	public String getParentPackageName() {
		return clazz.getParentPackageName();
	}

	public String getParentPackagePath() {
		return clazz.getParentPackagePath();
	}

	public List getProperties() throws Exception {
		return clazz.getProperties();
	}

	public String getSuperclassName() {
		return clazz.getSuperclassName();
	}

	@Override
	public String toString() {
		return "MethodParameter:" + getName() + "=" + getJavaType();
	}
}
