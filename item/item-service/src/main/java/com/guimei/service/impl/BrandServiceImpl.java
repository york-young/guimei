package com.guimei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guimei.common.pojo.PageResult;
import com.guimei.mapper.BrandMapper;
import com.guimei.model.Brand;
import com.guimei.parameter.pojo.BrandQueryByPageParameter;
import com.guimei.service.IBrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: York
 * @Date: 2020/5/9 000914:23
 * @Version:1.0
 * @Description: 品牌的业务层代码
 */
@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * @Description
     *             分页查询数据
     * @Author York
     * @Date 2020/5/10 0010 9:00
     * @Param [brandQueryByPageParameter]
     * @Return com.guimei.common.pojo.PageResult<com.guimei.model.Brand>
     **/
    @Override
    public PageResult<Brand> queryBrandByPage(BrandQueryByPageParameter brandQueryByPageParameter) {
        /**
         * 1.分页
         */
        PageHelper.startPage(brandQueryByPageParameter.getPage(),brandQueryByPageParameter.getRows());

        /**
         *  2.排序
         */
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(brandQueryByPageParameter.getSortBy())){
            example.setOrderByClause(brandQueryByPageParameter.getSortBy()+(brandQueryByPageParameter.getDesc()? " DESC":" ASC"));
        }
        /**
         * 3.查询
         */
        if(StringUtils.isNotBlank(brandQueryByPageParameter.getKey())) {
            example.createCriteria().orLike("name", brandQueryByPageParameter.getKey()+"%").orEqualTo("letter", brandQueryByPageParameter.getKey().toUpperCase());
        }
        List<Brand> list=this.brandMapper.selectByExample(example);

        /**
         * 4.创建PageInfo
         */
        PageInfo<Brand> pageInfo = new PageInfo<>(list);
        /**
         * 5.返回分页结果
         */
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }
    /**
     * @Description
     *             新增品牌数据，并维护中间表
     * @Author York
     * @Date 2020/5/10 0010 9:00
     * @Param [brand, categories]
     * @Return void
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBrand(Brand brand, List<Long> categories) {
        // 新增品牌信息
        this.brandMapper.insertSelective(brand);
        // 新增品牌和分类中间表
        for (Long cid : categories) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }
}
