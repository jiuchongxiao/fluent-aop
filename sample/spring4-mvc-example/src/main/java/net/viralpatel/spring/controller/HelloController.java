package net.viralpatel.spring.controller;

import com.dinfo.common.model.Response;
import com.taiyue.tool.common.FluentProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@GetMapping("/hello2")
	public String hello(Model model) {

		model.addAttribute("name", "John Doe");

		return "welcome";
	}

	@RequestMapping("hello")
	public Response test(){

		System.out.print("======================");
		System.out.print(FluentProperties.getProjectame()+" "+FluentProperties.getFluentUrl()+":"+FluentProperties.getFluentPort());
//        业务中记录日志
		FluentProperties.getLog().log("hello","testMethod","helloworld");
		return Response.ok("helloworld");
	}

}
