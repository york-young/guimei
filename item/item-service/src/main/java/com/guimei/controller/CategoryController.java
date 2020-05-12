package com.guimei.controller;

import com.guimei.model.Category;

import com.guimei.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @Author: York
* @Date: 2020/5/9 000915:41
* @Version:1.0
* @Description: TODO
*/
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;


    /**
     * @Description:
     *             根据parentId查询类目
     * @Date: 2020/5/8 0008 22:06
     * @Author: York
     * @param pid
     * @return org.springframework.http.ResponseEntity<java.util.List<com.guimei.model.Category>>
     */
    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        try {
            if (pid == null || pid.longValue() < 0){
                // pid为null或者小于等于0，响应400
                return ResponseEntity.badRequest().build();
            }
            // 执行查询操作
            List<Category> categoryList = this.categoryServiceImpl.queryCategoryByPid(pid);
            if (CollectionUtils.isEmpty(categoryList)){
                // 返回结果集为空，响应404
                return ResponseEntity.notFound().build();
            }
            // 响应200
            return ResponseEntity.ok(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}