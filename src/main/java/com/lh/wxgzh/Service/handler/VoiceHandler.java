package com.lh.wxgzh.Service.handler;

import com.lh.wxgzh.Utils.HttpUtils;
import com.lh.wxgzh.Utils.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.lh.wxgzh.Constants.WechatConstants.*;
import static com.lh.wxgzh.Constants.WechatConstants.TO_USER_NAME;

/**
 * 语音消息处理
 */
public class VoiceHandler extends AbsHandler {

    private static Logger logger = LoggerFactory.getLogger(VoiceHandler.class);

    private static final String FORMAT = "Format";
    private static final String MEDIA_ID = "MediaId";
    private static final String RECOGNITION = "Recognition";

    @Override
    public String handle(Map<String, String> requestMap) {
        String msgType = requestMap.get(MSG_TYPE);
        String fromUserName = requestMap.get(FROM_USER_NAME);
        String toUserName = requestMap.get(TO_USER_NAME);
        String format = requestMap.get(FORMAT);
        String mediaId = requestMap.get(MEDIA_ID);
        // 必传字段不能为空
        if(msgType == null || fromUserName == null || toUserName == null) {
            logger.warn("VoiceHandler param illegal! msgType={}, fromUserName={}, toUserName={}",
                    msgType, fromUserName, toUserName);
            return null;
        }
        try {
            String respContent = "这是一段语音！\n" + "语音格式为: " + format;
            String recognition = requestMap.get(RECOGNITION);
            if(recognition != null) {
                respContent += "\n" + recognition;
            }
            try {
                // 加入语音识别
                final String url = "http://api.weixin.qq.com/cgi-bin/media/voice/queryrecoresultfortext";
                Map<String, String> params = new HashMap<>();
                params.put("access_token", "weixin");
                params.put("voice_id", mediaId);
                params.put("lang", "zh_CN");
                String result = HttpUtils.post(url, params);
                if(logger.isDebugEnabled()) {
                    logger.debug("语音识别结果为: content={}, voiceId={}", result, mediaId);
                }
                if(result != null) {
                    respContent += "\n" + result;
                }
            } catch (Exception e) {
                logger.warn("语音识别失败! mediaId={}", mediaId);
            }
            return  WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
        } catch (Exception e) {
            logger.warn("Exception occurs.", e);
        }
        return null;
    }
}
