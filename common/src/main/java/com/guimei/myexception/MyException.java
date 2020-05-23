package com.guimei.myexception;

/**
 * @Author: York
 * @Date: 2020/5/23 0023 11:59
 * @Version:1.0
 * @Description: 自定义异常
 */
public class MyException extends RuntimeException {

    public MyException(ExceptionEnum exception) {
        super(exception.toString());
    }
}
