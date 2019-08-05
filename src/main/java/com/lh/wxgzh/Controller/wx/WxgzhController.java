package com.lh.wxgzh.Controller.wx;

import com.lh.wxgzh.Service.wx.WeChatServiceImpl;
import com.lh.wxgzh.Utils.WechatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信消息接口
 * @author gwk
 */
@RestController
public class WxgzhController {

    @Autowired
    WeChatServiceImpl weChatService;

    private static final String TOKEN = "weixin";

    /**
     * 处理微信服务器发来的get请求，进行签名的验证
     *
     * signature 微信端发来的签名
     * timestamp 微信端发来的时间戳
     * nonce     微信端发来的随机字符串
     * echostr   微信端发来的验证字符串
     */
    @GetMapping("wechat")
    public String check(@RequestParam("signature") String sig,
                      @RequestParam("timestamp") String timestamp,
                      @RequestParam("nonce") String nonce,
                      @RequestParam("echostr") String echostr){
        return WechatUtils.checkSignature(sig, timestamp, nonce) ? echostr : null;
    }
    /**
     * 此处是处理微信服务器的消息转发的
     */
    @PostMapping("wechat")
    public String sendMsg(HttpServletRequest request) {
        // 调用核心服务类接收处理请求
        return weChatService.processRequest(request);
    }

}
