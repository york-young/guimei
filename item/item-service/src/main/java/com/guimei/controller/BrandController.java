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
    @PostMapping
    public ResponseEntity<Void> addBrand(Brand brand, @RequestParam("cids")List<Long> categories){
        this.brandService.saveBrand(brand, categories);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
