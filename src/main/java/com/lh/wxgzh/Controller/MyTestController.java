package com.lh.wxgzh.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于检测接口
 */
@RestController
public class MyTestController {
    @GetMapping("/test")
    public String test() {
        return "test success!";
    }
}
