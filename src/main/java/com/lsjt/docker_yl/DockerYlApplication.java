package com.lsjt.docker_yl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * @author leiyunlong
 */
@SpringBootApplication
public class DockerYlApplication {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        SpringApplication.run(DockerYlApplication.class, args);
    }

}
