package com.guimei.myexception;

/**
 * @Author: York
 * @Date: 2020/5/23 0023 11:57
 * @Version:1.0
 * @Description: 枚举类定义类型信息
 */
public enum ExceptionEnum {
    /**
     * 定义枚举数据类型
     */
    CATEGORY_NOT_FOUND(404,"商品分类没找到"),
    BRAND_NOT_FOUND(404,"品牌不存在"),
    SPEC_GROUP_NOT_FOUND(404,"商品规格组不存在"),
    SPEC_PARAM_NOT_FOUND(404,"商品规格参数不存在"),
    GOODS_NOT_FOUND(404,"商品不存在"),
    BRAND_SAVA_ERROR(500,"品牌新增失败"),
    GOODS_SAVA_ERROR(500,"商品新增失败"),
    UPLOAD_FILE_ERROR(500,"文件上传失败"),
    INVALID_FILE_TYPE(400,"无效的文件类型"),
    GOODS_DETAIL_NOT_FOUND(404,"商品详情不存在"),
    GOODS_SKU_NOT_FOUND(404,"商品SKU不存在"),
    GOODS_STOCK_NOT_FOUND(404,"商品库存不存在"),
    GOODS_UPDATE_ERROR(500,"商品更新失败"),
    GOODS_ID_NOT_NULL(400,"商品ID不能为空"),
    ;

    private int code;
    private String msg;

    ExceptionEnum() {
    }

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
