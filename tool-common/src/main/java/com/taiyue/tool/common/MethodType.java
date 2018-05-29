package com.taiyue.tool.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class MethodType {
	private String className;
	private String name;

	private Class<?> declaringType;

	public Class<?> getDeclaringType() {
		return declaringType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MethodType(ProceedingJoinPoint joinPoint) {
		try {
			Signature sig = joinPoint.getSignature();
			MethodSignature msig = null;
			if (sig instanceof MethodSignature) {
				msig = (MethodSignature) sig;
				Object target = joinPoint.getTarget();
				Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
				this.name = currentMethod.getName();
				this.className = currentMethod.getDeclaringClass().getName();
//
//				Annotation[] annotations = currentMethod.getDeclaredAnnotations();
//
//				
//				
//				for (int i = 0; i < annotations.length; i++) {
//					annotations[0].annotationType().getName();
//				}

				currentMethod.getDeclaredAnnotations();
			}
		} catch (Exception ex) {

		}
	}
}
