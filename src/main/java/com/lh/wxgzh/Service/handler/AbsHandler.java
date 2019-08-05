package com.lh.wxgzh.Service.handler;

import java.util.Map;

/**
 * see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140453
 */
public abstract class AbsHandler {

    /**
     * 定义抽象类，对具体方法进行构造
     * @param requestMap 请求参数
     * @return
     */
    public abstract String handle(Map<String, String> requestMap);
}
