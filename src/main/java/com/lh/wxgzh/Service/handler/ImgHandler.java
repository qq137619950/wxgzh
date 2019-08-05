package com.lh.wxgzh.Service.handler;

import com.lh.wxgzh.Utils.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.lh.wxgzh.Constants.WechatConstants.*;
import static com.lh.wxgzh.Constants.WechatConstants.TO_USER_NAME;

/**
 * 图片内容处理
 */
public class ImgHandler extends AbsHandler {

    private static Logger logger = LoggerFactory.getLogger(ImgHandler.class);

    private static final String PIC_URL = "PicUrl";

    @Override
    public String handle(Map<String, String> requestMap) {
        // 消息类型
        String msgType = requestMap.get(MSG_TYPE);
        String fromUserName = requestMap.get(FROM_USER_NAME);
        String toUserName = requestMap.get(TO_USER_NAME);
        String PicUrl = requestMap.get(PIC_URL);
        // 必传字段不能为空
        if(msgType == null || fromUserName == null || toUserName == null) {
            logger.warn("ImgHandler param illegal! msgType={}, fromUserName={}, toUserName={}",
                    msgType, fromUserName, toUserName);
            return null;
        }
        try {
            String respContent = "这是一张图片！\n" + "链接如下:\n" + PicUrl;
            return  WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
        } catch (Exception e) {
            logger.warn("Exception occurs.", e);
        }
        return null;
    }

}
