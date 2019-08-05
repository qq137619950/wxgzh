package com.lh.wxgzh.Service.handler;

import com.lh.wxgzh.Utils.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.lh.wxgzh.Constants.WechatConstants.*;

/**
 * 事件处理
 * see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140454
 */
public class EventHandler extends AbsHandler{

    private static Logger logger = LoggerFactory.getLogger(EventHandler.class);

    @Override
    public String handle(Map<String, String> requestMap) {
        // 消息类型
        String msgType = requestMap.get(MSG_TYPE);
        String fromUserName = requestMap.get(FROM_USER_NAME);
        String toUserName = requestMap.get(TO_USER_NAME);
        String eventType = requestMap.get(EVENT);
        // 必传字段不能为空
        if(msgType == null || fromUserName == null || toUserName == null || eventType == null) {
            logger.warn("EventHandler param illegal! msgType={}, fromUserName={}, toUserName={}, eventType={}",
                    msgType, fromUserName, toUserName, eventType);
            return null;
        }
        try {
            // 关注
            if (eventType.equals(EVENT_TYPE_SUBSCRIBE)) {
                String respContent = "此处预留发红包接口，关注发红包! \n";
                return WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
            }
            // 取消关注
            else if (eventType.equals(EVENT_TYPE_UNSUBSCRIBE)) {
                // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
            }
            // 扫描带参数二维码（已关注扫描，如果是未关注扫描，则是EVENT_TYPE_SUBSCRIBE）
            else if (eventType.equals(EVENT_TYPE_SCAN)) {
                String respContent = "扫描二维码!";
                return WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
            }
            // 上报地理位置
            else if (eventType.equals(EVENT_TYPE_LOCATION)) {
                String respContent = "上报地理信息!";
                return WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
            }
            // 自定义菜单
            else if (eventType.equals(EVENT_TYPE_CLICK)) {
                String respContent = "自定义菜单!";
                return WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
            }
        } catch (Exception e) {
            logger.warn("Exception occurs.", e);
        }
        return null;
    }
}
