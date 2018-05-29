package com.taiyue.tool.common.process.service;

import com.dinfo.common.model.Response;

import com.taiyue.tool.common.process.DinfoServiceTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rongzihao
 * @date 2018/4/25
 */
@Service
@DinfoServiceTag
public class CommentServiceImpl implements CommentService{

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Override
    public Response<List<String>> getProductComment(String text) {
        logger.info("--execute text...");
        try {
            //分析
            String content = text;
            //数据封装
            List<String> stringList = new ArrayList<String>();
            stringList.add("test1getProductComment");
            stringList.add("test2getProductComment");
            stringList.add("test3getProductComment");
            return Response.ok(stringList);
        } catch (Exception e) {
            logger.error("execute innerSegmentCn error.", e);
            return Response.notOk(e.getMessage());
        }
    }

    @Override
    public Response<List<String>> getCustomerComment(String text) {
        logger.info("--execute text...");
        try {
            //分析
            String content = text;
            //数据封装
            List<String> stringList = new ArrayList<String>();
            stringList.add("getCustomerCommenttest1");
            stringList.add("getCustomerCommenttest2");
            stringList.add("getCustomerCommenttest3");
            return Response.ok(stringList);
        } catch (Exception e) {
            logger.error("execute innerSegmentCn error.", e);
            return Response.notOk(e.getMessage());
        }
    }



}
