package com.example.readword.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

/**
 * 全局异常处理器
 *
 * @author WangGQ
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return R.fail("路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(NullPointerException.class)
    public R handleNullException(NullPointerException e) {
        log.error(e.getMessage(), e);
        return R.fail("参数为空，请检查参数");
    }
    @ExceptionHandler(SQLException.class)
    public R handleSQLException(SQLException e) {
        log.error(e.getMessage(), e);
        return R.fail("没有对应的内容，请检查参数");
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.fail(e.getMessage());
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error(exception.getMessage());
        return R.fail("Request method not supported:" + exception.getMessage());
    }


}
