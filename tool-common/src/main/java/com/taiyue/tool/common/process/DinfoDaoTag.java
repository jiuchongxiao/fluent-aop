package com.taiyue.tool.common.process;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface DinfoDaoTag {
	String value() default "";
}
