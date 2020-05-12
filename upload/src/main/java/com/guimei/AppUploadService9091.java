package com.guimei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: York
 * @Date: 2020/5/10 0010 9:50
 * @Version:1.0
 * @Description: 文件上传的启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AppUploadService9091 {
    public static void main(String[] args) {
        SpringApplication.run(AppUploadService9091.class, args);
    }
}