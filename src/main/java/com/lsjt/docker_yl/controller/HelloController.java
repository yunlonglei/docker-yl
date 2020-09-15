package com.lsjt.docker_yl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leiyunlong
 * @version 1.0
 * @since 2020/9/15 11:40 上午
 */
@RestController
public class HelloController {
    /**
     * hello world测试接口
     * @return 字符串
     */
    @GetMapping("hello")
    public String hello1(){
        return "hello world!";
    }
}
