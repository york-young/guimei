package com.guimei.controller;

import com.guimei.pojo.Category;

import com.guimei.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Author: York
* @Date: 2020/5/9 000915:41
* @Version:1.0
* @Description: 分类的控制层代码区
*/
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;


    /**
     * @Description:
     *             根据parentId查询类目，,以树的形式展示出来，要借助父id
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
            List<Category> categoryList = this.categoryService.queryCategoryByPid(pid);
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

    /**
     * @Description:
     *             用于修改品牌信息时，商品分类信息的回显，根据bid查找数据信息
     * @Author: York
     * @Date: 2020/5/18 0018 22:04
     * @param bid
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.guimei.model.Category>>
     **/
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid){
        List<Category> list = this.categoryService.queryByBrandId(bid);
        if(list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }



    /**
     * @Description:
     *             添加数据信息
     * @Author: York
     * @Date: 2020/5/18 0018 22:10
     * @param category
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    @PostMapping
    public ResponseEntity<Void> saveCategory(Category category){
        this.categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * @Description:
     *             更新数据信息
     * @Author: York
     * @Date: 2020/5/18 0018 22:11
     * @param category
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    @PutMapping
    public ResponseEntity<Void> updateCategory(Category category){
        this.categoryService.updateCategory(category);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * @Description:
     *             根据id删除数据信息
     * @Author: York
     * @Date: 2020/5/18 0018 22:12
     * @param id
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    @DeleteMapping("cid/{cid}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("cid") Long id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * @Description:
     *             根据分类id集合查询分类名称
     * @Author: York
     * @Date: 2020/5/18 0018 22:13
     * @param ids
     * @Return: org.springframework.http.ResponseEntity<java.util.List<java.lang.String>>
     **/
    @GetMapping("names")
    public ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids")List<Long> ids){
        List<String> list = categoryService.queryNameByIds(ids);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(list);
        }
    }

    /**
     * @Description:
     *             根据分类id集合查询分类名称
     * @Author: York
     * @Date: 2020/5/18 0018 22:14
     * @param ids
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.guimei.model.Category>>
     **/
    @GetMapping("all")
    public ResponseEntity<List<Category>> queryCategoryByIds(@RequestParam("ids")List<Long> ids){
        List<Category> list = categoryService.queryCategoryByIds(ids);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(list);
        }
    }

    /**
     * @Description:
     *             根据分类id集合查询分类名称
     * @Author: York
     * @Date: 2020/5/18 0018 22:14
     * @param id
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.guimei.model.Category>>
     **/
    @GetMapping("all/level/{cid3}")
    public ResponseEntity<List<Category>> queryAllCategoryLevelByCid3(@PathVariable("cid3")Long id){
        List<Category> list = categoryService.queryAllCategoryLevelByCid3(id);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(list);
        }
    }
}