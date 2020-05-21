package com.guimei.service;

import com.guimei.model.Category;

import java.util.List;

public interface ICategoryService {
    /**
     * @Description:
     *             根据ID查找数据信息
     * @Author: York
     * @Date: 2020/5/18 0018 22:07
     * @param pid
     * @Return: java.util.List<com.guimei.model.Category>
     **/
    List<Category> queryCategoryByPid(Long pid);

    /**
     * @Description:
     *             根据ID查找数据信息,回显数据的
     * @Author: York
     * @Date: 2020/5/18 0018 22:09
     * @param bid
     * @Return: java.util.List<com.guimei.model.Category>
     **/
    List<Category> queryByBrandId(Long bid);

    /**
     * @Description:
     *             添加数据信息
     * @Author: York
     * @Date: 2020/5/18 0018 22:10
     * @param category
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    void saveCategory(Category category);

    /**
     * @Description:
     *             更新数据信息
     * @Author: York
     * @Date: 2020/5/18 0018 22:11
     * @param category
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    void updateCategory(Category category);

    /**
     * @Description:
     *             根据id删除数据信息
     * @Author: York
     * @Date: 2020/5/18 0018 22:12
     * @param id
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    void deleteCategory(Long id);

    /**
     * @Description:
     *             根据分类id集合查询分类名称
     * @Author: York
     * @Date: 2020/5/18 0018 22:13
     * @param ids
     * @Return: org.springframework.http.ResponseEntity<java.util.List<java.lang.String>>
     **/
    List<String> queryNameByIds(List<Long> ids);

    /**
     * @Description:
     *             根据分类id集合查询分类名称
     * @Author: York
     * @Date: 2020/5/18 0018 22:14
     * @param ids
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.guimei.model.Category>>
     **/
    List<Category> queryCategoryByIds(List<Long> ids);

    /**
     * @Description:
     *             根据分类id集合查询分类名称
     * @Author: York
     * @Date: 2020/5/18 0018 22:14
     * @param id
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.guimei.model.Category>>
     **/
    List<Category> queryAllCategoryLevelByCid3(Long id);
}
