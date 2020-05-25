package com.guimei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: York
 * @Date: 2020/5/7 000711:08
 * @Version:1.0
 * @Description: 这是网关后台商品服务的的启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
//@MapperScan("com.guimei.mapper")
public class AppItemService8081 {
    public static void main(String[] args) {
        SpringApplication.run(AppItemService8081.class,args);
    }
}
