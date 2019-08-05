package com.lh.wxgzh.Service.wx;

import com.lh.wxgzh.Service.handler.*;
import com.lh.wxgzh.Service.handler.Factory.HandlerFactory;
import com.lh.wxgzh.Utils.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.lh.wxgzh.Constants.WechatConstants.*;

/**
 * Service 层
 * @author gwk
 */
@Service
public class WeChatServiceImpl implements WechatService{

    private static Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);

    /**
     * 处理请求消息
     * @param request
     * @return
     */
    @Override
    public String processRequest(HttpServletRequest request) {
        // 处理类
        AbsHandler handler;
        // xml格式的消息数据
        String respXml;
        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = WechatUtils.parseXml(request);
            String content = requestMap.get(CONTENT);
            String fromUserName = requestMap.get(FROM_USER_NAME);
            String toUserName = requestMap.get(TO_USER_NAME);

            handler = HandlerFactory.getHandler(requestMap);
            if(handler == null) {
                return WechatUtils.sendDefultTextMsg(fromUserName, toUserName);
            }
            respXml = handler.handle(requestMap);
            // 确保content为非空
            content = content == null ? DEFAULT_CONTENT : content;
            if (respXml == null) {
                respXml = WechatUtils.sendTextMsg(content, fromUserName, toUserName);
            }
            return respXml;
        } catch (Exception e) {
            logger.warn("Exception occurs.", e);
        }
        return "";
    }

}
