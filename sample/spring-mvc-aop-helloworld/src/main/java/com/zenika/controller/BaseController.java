package com.zenika.controller;

import com.dinfo.common.model.Response;
import com.taiyue.tool.common.FluentProperties;
import com.taiyue.tool.common.process.DinfoUrlTag;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//测试aop功能
@DinfoUrlTag
public class BaseController {
 
	private static int counter = 0;
	private static final String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);


	@RequestMapping("/")
	public Response test(){

		System.out.print("======================");
//		System.out.print(FluentProperties.getProjectame()+" "+FluentProperties.getFluentUrl()+":"+FluentProperties.getFluentPort());
//        业务中记录日志
//		FluentProperties.getLog().log("hello11","testMethod","helloworld");
		return Response.ok("helloworld");
	}


	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {
 
		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);
		logger.debug("[welcome] counter : {}", counter);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;
 
	}*/
 
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {
 
		model.addAttribute("message", "Welcome " + name);
		model.addAttribute("counter", ++counter);
		logger.debug("[welcomeName] counter : {}", counter);
		return VIEW_INDEX;
 
	}
 
}
