package com.taiyue.tool.common.process.web;

import com.dinfo.common.model.Response;
import com.taiyue.tool.common.process.DinfoUrlTag;
import com.taiyue.tool.common.process.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *
 * @author rongzihao
 * @date 2018/4/25
 */
@RestController
@RequestMapping("/nlpapp/v2")
@DinfoUrlTag
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentService service;


    /**
     * @param param 被解析的字符串
     * @return
     * @throws Exception
     * @Description: 解析字符串获取电子产品评价
     */
    @ResponseBody
    @RequestMapping("/product")
    public Response getProductComment(@RequestBody Map<String, String> param) {

        logger.info("--the parameter of function wordFrequency is " + param);
        if (param == null) {
            return Response.notOk("parameter must not be null.");
        }
        //得到text值
        String texts = param.get("text");
        Response<List<String>> data = service.getProductComment(texts);
        if (data.isSuccess()) {
            return Response.ok(data.getData());
        } else {
            return Response.notOk(data.getErr());
        }
    }

    /**
     * @param param 被解析的字符串
     * @return
     * @throws Exception
     * @Description: 解析字符串获取商户评价
     */
    @ResponseBody
    @RequestMapping("/customer")
    public Response test(@RequestBody Map<String, String> param) {

        logger.info("--the parameter of function wordFrequency is " + param);

        if (param == null) {
            return Response.notOk("parameter must not be null.");
        }
        //得到text值
        String texts = param.get("text");

        Response<List<String>> data = service.getCustomerComment(texts);
        if (data.isSuccess()) {
            return Response.ok(data.getData());
        } else {
            return Response.notOk(data.getErr());
        }
    }
}
