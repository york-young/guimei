package com.guimei.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    /**
     * @Description
     *             上传图片
     * @Author York
     * @Date 2020/5/10 0010 10:19
     * @Param [file]
     * @Return java.lang.String
     **/
    String uploadImage(MultipartFile file);
}
