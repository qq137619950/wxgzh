package com.lh.wxgzh.Service.handler;

import com.lh.wxgzh.Utils.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.lh.wxgzh.Constants.WechatConstants.*;
import static com.lh.wxgzh.Constants.WechatConstants.TO_USER_NAME;

/**
 * 链接处理
 */
public class LinkHandler extends AbsHandler {

    private static Logger logger = LoggerFactory.getLogger(LinkHandler.class);

    private static final String TITLE = "Title";
    private static final String DESC = "Description";
    private static final String URL = "Url";

    @Override
    public String handle(Map<String, String> requestMap) {
        // 消息类型
        String msgType = requestMap.get(MSG_TYPE);
        String fromUserName = requestMap.get(FROM_USER_NAME);
        String toUserName = requestMap.get(TO_USER_NAME);
        // 必传字段不能为空
        if(msgType == null || fromUserName == null || toUserName == null) {
            logger.warn("LinkHandler param illegal! msgType={}, fromUserName={}, toUserName={}",
                    msgType, fromUserName, toUserName);
            return null;
        }
        try {
            String title = "标题:" + requestMap.get(TITLE);
            String desc = "描述：" + requestMap.get(DESC);
            String url = "链接地址:" + requestMap.get(URL);
            String respContent = "这是一个链接！\n" + title + "\n"
                    + desc + "\n" + url;
            return  WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
        } catch (Exception e) {
            logger.warn("Exception occurs.", e);
        }
        return null;
    }
}
