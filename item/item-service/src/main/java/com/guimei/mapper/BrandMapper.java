package com.guimei.mapper;

import com.guimei.model.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: York
 * @Date: 2020/5/22 0022 20:45
 * @Version:1.0
 * @Description: BrandMapper表数据库访问层
 */

public interface BrandMapper extends Mapper<Brand> {
    /**
     * @Description
     *             新增品牌的中间表的维护
     * @Author York
     * @Date 2020/5/10 0010 9:09
     * @Param [cid, bid]
     * @Return void
     **/
    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    void insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);
    /**
     * @Description
     *             根据brand id删除中间表相关数据
     * @Author York
     * @Date 2020/5/10 0010 9:14
     * @Param [bid]
     * @Return void
     **/
    @Delete("DELETE FROM tb_category_brand WHERE brand_id = #{bid}")
    void deleteByBrandIdInCategoryBrand(@Param("bid") Long bid);

    /**
     * @Description
     *             根据category id查询brand,左连接
     * @Author York
     * @Date 2020/5/10 0010 9:13
     * @Param [cid]
     * @Return java.util.List<com.guimei.model.Brand>
     **/
    @Select("SELECT b.* FROM tb_brand b LEFT JOIN tb_category_brand cb ON b.id=cb.brand_id WHERE cb.category_id=#{cid}")
    List<Brand> queryBrandByCategoryId(Long cid);

    /**
     * @param ids
     * @Description: 根据BrandId查询商品品牌信息
     * @Author: York
     * @Date: 2020/5/15 0015 10:36
     * @Return: org.springframework.http.ResponseEntity<java.util.List < com.guimei.model.Brand>>
     **/
    List<Brand> selectByIdList(List<Long> ids);
}
