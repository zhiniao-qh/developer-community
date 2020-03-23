package cn.treeshell.gathering.controller;

import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * @Author: panjing
 * @Date: 2020/3/17 20:07
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e) {
        log.error(e.getMessage());

        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}