package com.taiyue.tool.common.process.web;

import com.dinfo.common.model.Response;
import com.taiyue.tool.common.FluentProperties;
import com.taiyue.tool.common.process.ControllerProcessAop;
import com.taiyue.tool.common.process.DinfoUrlTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ljb on 2018/5/28.
 */
@RestController
@DinfoUrlTag
public class TestController {

    @RequestMapping("hello")
    public Response test(){

        System.out.print("======================");
        System.out.print(FluentProperties.getProjectame()+" "+FluentProperties.getFluentUrl()+":"+FluentProperties.getFluentPort());
//        业务中记录日志
        FluentProperties.getLog().log("hello","testMethod","helloworld");
        return Response.ok("helloworld");
    }

    @RequestMapping("input")
    public Response testInput(String h){
        return Response.ok(h);
    }


}
