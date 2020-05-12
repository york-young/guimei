package com.guimei.controller;

import com.guimei.service.IUploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: York
 * @Date: 2020/5/10 001010:11
 * @Version:1.0
 * @Description: 图片上传的控制层代码
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private IUploadService uploadService;

    /**
     * @Description
     *             上传图片控制
     * @Author York
     * @Date 2020/5/10 0010 10:14
     * @Param [file]
     * @Return org.springframework.http.ResponseEntity<java.lang.String>
     **/
    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file){
        String url= this.uploadService.uploadImage(file);
        if(StringUtils.isBlank(url)){
            //url为空，证明上传失败
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(url);
    }

}
