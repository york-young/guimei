package com.guimei.mapper;

import com.guimei.pojo.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;

/**
 * @Author: York
 * @Date: 2020/5/22 0022 20:46
 * @Version:1.0
 * @Description: Category表数据库访问层
 */
@org.apache.ibatis.annotations.Mapper
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {

    /**
     * @Description:
     *             根据品牌id查询商品分类
     * @Author: York
     * @Date: 2020/5/19 0019 10:06
     * @param bid
     * @Return: java.util.List<com.guimei.model.Category>
     **/
    @Select("SELECT * FROM tb_category WHERE id IN (SELECT category_id FROM tb_category_brand WHERE brand_id = #{bid}) ")
    List<Category> queryByBrandId(@Param("bid") Long bid);

    /**
     * @Description:
     *             根据category id删除中间表相关数据
     * @Author: York
     * @Date: 2020/5/19 0019 10:06
     * @param cid
     * @Return: void
     **/
    @Delete("DELETE FROM tb_category_brand WHERE category_id = #{cid}")
    void deleteByCategoryIdInCategoryBrand(@Param("cid") Long cid);

    /**
     * @Description:
     *             根据id查名字
     * @Author: York
     * @Date: 2020/5/19 0019 10:06
     * @param id
     * @Return: java.lang.String
     **/
    @Select("SELECT name FROM tb_category WHERE id = #{id}")
    String queryNameById(Long id);

    /**
     * @Description:
     *             查询最后一条数据
     * @Author: York
     * @Date: 2020/5/19 0019 10:06
     * @param
     * @Return: java.util.List<com.guimei.model.Category>
     **/
    @Select("SELECT * FROM `tb_category` WHERE id = (SELECT MAX(id) FROM tb_category)")
    List<Category> selectLast();
}