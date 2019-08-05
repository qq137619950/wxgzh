package com.lh.wxgzh.Service.handler;

import com.lh.wxgzh.Utils.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.lh.wxgzh.Constants.WechatConstants.*;
import static com.lh.wxgzh.Constants.WechatConstants.TO_USER_NAME;

/**
 * 地理位置处理
 */
public class LocationHandler extends AbsHandler {

    private static Logger logger = LoggerFactory.getLogger(LocationHandler.class);

    private static final String LOCATION_X = "Location_X";
    private static final String LOCATION_Y = "Location_Y";
    private static final String SCALE = "Scale";
    private static final String LABEL = "Label";

    @Override
    public String handle(Map<String, String> requestMap) {
        String msgType = requestMap.get(MSG_TYPE);
        String fromUserName = requestMap.get(FROM_USER_NAME);
        String toUserName = requestMap.get(TO_USER_NAME);
        // 必传字段不能为空
        if(msgType == null || fromUserName == null || toUserName == null) {
            logger.warn("LocationHandler param illegal! msgType={}, fromUserName={}, toUserName={}",
                    msgType, fromUserName, toUserName);
            return null;
        }
        try {
            String location = "纬度:" +  requestMap.get(LOCATION_X) + ", 经度:" + requestMap.get(LOCATION_Y);
            String info = "位置信息:" + requestMap.get(LABEL);
            String scale = "地图缩放大小为:" + requestMap.get(SCALE);
            String respContent = "这是一个地理位置！\n" + location + "\n"
                    + info + "\n" + scale;
            return  WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
        } catch (Exception e) {
            logger.warn("Exception occurs.", e);
        }
        return null;
    }

}
