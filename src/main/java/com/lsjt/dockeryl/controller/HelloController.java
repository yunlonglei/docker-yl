package com.lsjt.dockeryl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author leiyunlong
 * @version 1.0
 * @since 2020/9/15 11:40 上午
 */
@RestController
@Slf4j
public class HelloController {
    @Autowired
    ApplicationArguments applicationArguments;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * hello world测试接口
     *
     * @return 字符串
     */
    @GetMapping("hello")
    public String hello1() {
        log.info("启动类传的参数「{}」",Arrays.toString(applicationArguments.getSourceArgs()));
        log.info("访问hello接口=====");
        return "hello, Add CI / CD here in docker,thanks!4 ";
    }

    /**
     * hello world测试接口
     *
     * @return 字符串
     */
    @GetMapping("/")
    public String index() {
        return "hello";
    }

    @GetMapping("/count")
    public String incountdex() {
        // 使用redis计数累加
        Long count = stringRedisTemplate.opsForValue().increment("count");
        return "有" + count + "人访问了我空间";
    }
}
