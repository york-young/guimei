package com.guimei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: York
 * @Date: 2020/5/7 00079:51
 * @Version:1.0
 * @Description: 这是eureka02的的启动类
 */
@SpringBootApplication
@EnableEurekaServer
public class Application7082 {

    public static void main(String[] args) {
        SpringApplication.run(Application7082.class, args);
    }

}
