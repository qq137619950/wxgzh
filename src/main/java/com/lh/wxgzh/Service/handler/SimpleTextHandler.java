package com.lh.wxgzh.Service.handler;

import com.lh.wxgzh.Entity.ArticleItem;
import com.lh.wxgzh.Utils.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.lh.wxgzh.Constants.WechatConstants.*;
import static com.lh.wxgzh.Constants.WechatConstants.TO_USER_NAME;

/**
 * 对普通文本进行处理
 */
public class SimpleTextHandler extends AbsHandler {

    private static Logger logger = LoggerFactory.getLogger(SimpleTextHandler.class);

    @Override
    public String handle(Map<String, String> requestMap) {
        String msgType = requestMap.get(MSG_TYPE);
        String content = requestMap.get(CONTENT);
        String fromUserName = requestMap.get(FROM_USER_NAME);
        String toUserName = requestMap.get(TO_USER_NAME);
        // 必传字段不能为空
        if(msgType == null || content == null || fromUserName == null || toUserName == null) {
            logger.warn("SimpleTextHandler param illegal! msgType={}, content={}, fromUserName={}, toUserName={}",
                    msgType, content, fromUserName, toUserName);
            return null;
        }
        try {
            if (content.contains("李凯")) {
                String respContent = "李凯是" + (System.currentTimeMillis() % 2 == 0 ? "傻逼" : "沙雕") + "!";
                return WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
            } else if (content.contains("小雨")) {
                String respContent = "小雨听起来是一个" + (System.currentTimeMillis() % 2 == 0 ? "可爱" : "乖乖") + "的女孩!";
                return WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
            } else if (content.contains("美女")) {
                List<ArticleItem> items = new ArrayList<>();
                ArticleItem item = new ArticleItem();
                item.setTitle("想看美女吗?");
                item.setDescription("想看美女自己百度！");
                item.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505100912368&di=69c2ba796aa2afd9a4608e213bf695fb&imgtype=0&src=http%3A%2F%2Ftx.haiqq.com%2Fuploads%2Fallimg%2F170510%2F0634355517-9.jpg");
                item.setUrl("http://www.baidu.com");
                items.add(item);
                return WechatUtils.sendArticleMsg(items, fromUserName, toUserName);
            } else {
                String respContent = "暗号没对上，反弹:\n" + content;
                return WechatUtils.sendTextMsg(respContent, fromUserName, toUserName);
            }
        } catch (Exception e) {
            logger.warn("Exception occurs.", e);
        }
        return null;
    }
}
