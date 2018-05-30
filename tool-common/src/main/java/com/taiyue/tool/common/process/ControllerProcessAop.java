/*  
 * project tool-process
 * fileName ControllerProcessAop.java 
 * package com.taiyue.tool.process
 * description TODO(用一句话描述该文件做什么) 
 * copyright © 2017 www.99114.com Inc. All rights reserved.	
 */
package com.taiyue.tool.common.process;

import com.dinfo.common.model.Response;
import com.taiyue.tool.common.MagicExistsCondition;
import com.taiyue.tool.common.MethodObject;
import com.taiyue.tool.common.FluentProperties;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.fluentd.logger.FluentLogger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * description: TODO(这里用一句话描述这个类的作用) <br>
 * date: 2017年1月21日 下午5:49:29 <br>
 * 
 * @author: ljb
 * @since: v1.0.0
 * @version v1.0.0
 */

@Aspect
@Order(AopOrder.PROCESS)
@Component
//@ConditionalOnProperty(name = "fluent.aopStatus", havingValue = "true")
@Conditional(MagicExistsCondition.class)
public class ControllerProcessAop {

	//定义log 指向fluent remote地址
//	private static FluentLogger log = FluentLogger.getLogger(FluentProperties.getProjectame(), FluentProperties.getFluentUrl(), FluentProperties.getFluentPort());
	private static FluentLogger log = FluentProperties.getLog();

	@Around(value = "@within(com.taiyue.tool.common.process.DinfoUrlTag)")
	public Object around(ProceedingJoinPoint joinPoint) {

		MethodObject methodObject = new MethodObject(joinPoint);
		String className = methodObject.getClassName();
		String methodName = methodObject.getMethodName();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type","controller");
		map.put("className",className);
		map.put("methodName",methodName);
		map.put("paramsArgsName",methodObject.getParamsArgsName());
		map.put("paramsArgsValue",methodObject.getParamsArgsValue());
		try {
			map.put("status","success");
			Object object = joinPoint.proceed();
			if(object instanceof Response) {
				Response data = (Response) object;
				map.put("data", data.getData());
				log.log("info", map);
				if (data.isSuccess()) {
					return Response.ok(data.getData());
				} else {
					return Response.notOk(data.getErr());
				}
			}else{
				return Response.ok(object);
			}
		} catch (Throwable exception) {
			map.put("status","erro");
			map.put("message",exception.getMessage());
			log.log("info",map);
			return Response.notOk(exception.getMessage());
		}
//		finally {
//		}
	}
}