package com.guimei.controller;

import com.guimei.common.pojo.PageResult;
import com.guimei.model.Brand;
import com.guimei.parameter.pojo.BrandQueryByPageParameter;
import com.guimei.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: York
 * @Date: 2020/5/9 000915:41
 * @Version:1.0
 * @Description: 品牌的控制层代码区
 */
@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;


    /**
     * @Description
     *             根据关键词查询信息
     * @Author York
     * @Date 2020/5/9 0009 23:32
     * @Param [brandQueryByPageParameter]
     * @Return org.springframework.http.ResponseEntity<com.guimei.common.pojo.PageResult<com.guimei.model.Brand>>
     **/
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(BrandQueryByPageParameter brandQueryByPageParameter){
        PageResult<Brand> result = this.brandService.queryBrandByPage(brandQueryByPageParameter);
        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(result);
    }

    /**
     * @Description:
     *             新增商品信息
     * @Author: York
     * @Date: 2020/5/15 0015 10:25
     * @param brand
     * @param categories
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    @PostMapping
    public ResponseEntity<Void> addBrand(Brand brand, @RequestParam("cids")List<Long> categories){
        this.brandService.saveBrand(brand, categories);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @Description:
     *             修改商品信息
     * @Author: York
     * @Date: 2020/5/15 0015 10:25
     * @param brand
     * @param categories
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    public ResponseEntity<Void> updateBrand(Brand brand, @RequestParam("cids")List<Long> categories){
        this.brandService.updateBrand(brand, categories);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @Description:
     *             根据CategoryId查询商品品牌信息
     * @Author: York
     * @Date: 2020/5/15 0015 10:35
     * @param cid
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.guimei.model.Brand>>
     **/
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCategoryId(@PathVariable("cid") Long cid){
        List<Brand> list = this.brandService.queryBrandByCategoryId(cid);
        if (list == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

    /**
     * @Description:
     *             根据BrandId查询商品品牌信息
     * @Author: York
     * @Date: 2020/5/15 0015 10:36
     * @param ids
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.guimei.model.Brand>>
     **/
    @GetMapping("list")
    public ResponseEntity<List<Brand>> queryBrandByIds(@RequestParam("ids") List<Long> ids){
        List<Brand> list = this.brandService.queryBrandByBrandIds(ids);
        if (list == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }

    /**
     * @Description:
     *             删除和批量删除二合一
     * @Author: York
     * @Date: 2020/5/15 0015 10:36
     * @param bid
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     **/
    @DeleteMapping("bid/{bid}")
    public ResponseEntity<Void> deleteBrand(@PathVariable("bid") String bid){
        String separator="-";
        if(bid.contains(separator)){
            String[] ids=bid.split(separator);
            for (String id:ids){
                this.brandService.deleteBrand(Long.parseLong(id));
            }
        }
        else {
            this.brandService.deleteBrand(Long.parseLong(bid));
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
