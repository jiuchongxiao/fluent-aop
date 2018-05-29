package com.taiyue.tool.common.process.service;

import com.dinfo.common.model.Response;

import java.util.List;

/**
 *
 * @author rongzihao
 * @date 2018/4/25
 */
public interface CommentService {

    Response<List<String>>  getProductComment(String texts);

    Response<List<String>> getCustomerComment(String texts);
}
