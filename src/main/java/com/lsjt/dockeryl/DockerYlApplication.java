package com.lsjt.dockeryl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * @author leiyunlong
 */
@SpringBootApplication
public class DockerYlApplication {
    /**
     * 启动类传参： java -jar docker_yl.jar username=root
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        SpringApplication.run(DockerYlApplication.class, args);
    }

}
