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
}
