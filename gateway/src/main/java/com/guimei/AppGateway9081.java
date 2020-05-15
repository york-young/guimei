package com.guimei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: York
 * @Date: 2020/5/7 00029:55
 * @Version:1.0
 * @Description: 这是网关zuul的启动类
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class AppGateway9081 {
    public static void main(String[] args) {
        SpringApplication.run(AppGateway9081.class,args);
    }
}
