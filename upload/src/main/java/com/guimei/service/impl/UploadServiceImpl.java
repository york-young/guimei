package com.guimei.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.guimei.config.UploadProperties;
import com.guimei.service.IUploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;


/**
 * @Author: York
 * @Date: 2020/5/10 001010:17
 * @Version:1.0
 * @Description: 文件上传的实现类
 */
@Service
@Slf4j
//@EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private UploadProperties uploadProperties;
    @Autowired
    private FastFileStorageClient storageClient;

    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    /*支持上传的文件类型*/
    //private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg", "image/jpg");

    /**
     * @param file
     * @Description 上传图片
     * @Author York
     * @Date 2020/5/10 0010 10:19
     * @Param [file]
     * @Return java.lang.String
     */
    @Override
    public String uploadImage(MultipartFile file) {
        /**
         * 1.图片信息校验
         *      1)校验文件类型
         *      2)校验图片内容
         * 2.保存图片
         *      1)生成保存目录
         *      2)保存图片
         *      3)拼接图片地址
         */
        try {
            String type = file.getContentType();
            if (!uploadProperties.getAllowTypes().contains(type)) {
                logger.info("上传文件失败，文件类型不匹配：{}", type);
                return null;
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                logger.info("上传失败，文件内容不符合要求");
                return null;
            }

            StorePath storePath = this.storageClient.uploadFile(
                    file.getInputStream(), file.getSize(), getExtension(file.getOriginalFilename()), null);
            String url = uploadProperties.getBaseUrl() + storePath.getFullPath();
            return url;
        } catch (Exception e) {
            return null;
        }
    }

    public String getExtension(String fileName) {
        return StringUtils.substringAfterLast(fileName, ".");
    }
}
