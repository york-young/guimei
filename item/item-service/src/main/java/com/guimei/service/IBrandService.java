package com.guimei.service;

import com.guimei.common.pojo.PageResult;
import com.guimei.model.Brand;
import com.guimei.parameter.pojo.BrandQueryByPageParameter;

import java.util.List;

public interface IBrandService {
    /**
     * @Description
     *             分页查询数据
     * @Author York
     * @Date 2020/5/10 0010 9:00
     * @Param [brandQueryByPageParameter]
     * @Return com.guimei.common.pojo.PageResult<com.guimei.model.Brand>
     **/
    PageResult<Brand> queryBrandByPage(BrandQueryByPageParameter brandQueryByPageParameter);
    /**
     * @Description
     *             新增品牌数据，并维护中间表
     * @Author York
     * @Date 2020/5/10 0010 9:00
     * @Param [brand, categories]
     * @Return void
     **/
    void saveBrand(Brand brand, List<Long> categories);

    /**
     * @Description:
     *             修改商品信息
     * @Author: York
     * @Date: 2020/5/15 0015 10:25
     * @param brand
     * @param categories
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    void updateBrand(Brand brand, List<Long> categories);

    /**
     * @Description:
     *             根据CategoryId查询商品品牌信息
     * @Author: York
     * @Date: 2020/5/15 0015 10:35
     * @param cid
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.guimei.model.Brand>>
     **/
    List<Brand> queryBrandByCategoryId(Long cid);

    /**
     * @Description:
     *             根据BrandId查询商品品牌信息
     * @Author: York
     * @Date: 2020/5/15 0015 10:36
     * @param ids
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.guimei.model.Brand>>
     **/
    List<Brand> queryBrandByBrandIds(List<Long> ids);

    /**
     * @Description:
     *             删除和批量删除二合一
     * @Author: York
     * @Date: 2020/5/15 0015 10:36
     * @param bid
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    void deleteBrand(long bid);
}
