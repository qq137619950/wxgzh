package com.lh.wxgzh.Service.handler;

import com.lh.wxgzh.Utils.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.lh.wxgzh.Constants.WechatConstants.*;

/**
 * 小视频消息
 */
public class ShortVideoHandler extends AbsHandler {

    private static Logger logger = LoggerFactory.getLogger(ShortVideoHandler.class);

    @Override
    public String handle(Map<String, String> requestMap) {
        String msgType = requestMap.get(MSG_TYPE);
        String fromUserName = requestMap.get(FROM_USER_NAME);
        String toUserName = requestMap.get(TO_USER_NAME);
        // 必传字段不能为空
        if(msgType == null  || fromUserName == null || toUserName == null) {
            logger.warn("ShortVideoHandler param illegal! msgType={}, fromUserName={}, toUserName={}",
                    msgType, fromUserName, toUserName);
            return null;
        }
        try {
            String respContent = "这是一段小视频";
            return  WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
        } catch (Exception e) {
            logger.warn("Exception occurs.", e);
        }
        return null;
    }
}
