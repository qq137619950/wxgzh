package com.lh.wxgzh.Service.handler.Factory;

import com.lh.wxgzh.Service.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.lh.wxgzh.Constants.WechatConstants.*;

/**
 * 简单工厂，创建Handler
 */
public class HandlerFactory {

    private static Logger logger = LoggerFactory.getLogger(HandlerFactory.class);

    public static AbsHandler getHandler(Map<String, String> requestMap) {
        String msgType = requestMap.get(MSG_TYPE);
        switch (msgType) {
            // 文字消息
            case REQ_MESSAGE_TYPE_TEXT : return new SimpleTextHandler();
            // 图片消息
            case REQ_MESSAGE_TYPE_IMAGE : return new ImgHandler();
            // 语音消息
            case REQ_MESSAGE_TYPE_VOICE : return new VoiceHandler();
            // 视频消息
            case REQ_MESSAGE_TYPE_VIDEO : return new VideoHandler();
            // 小视频消息
            case REQ_MESSAGE_TYPE_SHORT_VIDEO : return new ShortVideoHandler();
            // 地理位置消息
            case REQ_MESSAGE_TYPE_LOCATION : return new LocationHandler();
            // 链接消息
            case REQ_MESSAGE_TYPE_LINK : return new LinkHandler();
            // 事件推送
            case REQ_MESSAGE_TYPE_EVENT : return new EventHandler();
            // 默认为null
            default: return null;
        }
    }
}
