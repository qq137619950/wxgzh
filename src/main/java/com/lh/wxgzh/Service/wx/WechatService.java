package com.lh.wxgzh.Service.wx;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义微信消息处理服务
 * @author gwk
 */
public interface WechatService {
    public String processRequest(HttpServletRequest request);
}
