package com.example.readword.util;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Optional;

/**
 * restful风格返回
 *
 * @Author: WangGQ
 * @Date: 2020/6/25 16:47
 * @Desc:
 */
@Builder
@Getter
public class R<T> implements Serializable {


    private int code;
    private String msg;
    private T data;

    public static R ok(int code, String msg, Object data) {
        return R.builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }

    public static R ok() {
        return ok("ok");
    }


    public static R ok(Object data) {
        return ok("ok", data);
    }

    public static R ok(String msg, Object data) {
        return ok(200, msg, data);
    }


    public static R ok(String msg) {
        return ok(msg, null);
    }

    public static R fail(int code, String msg, Object data) {

        return R.builder().code(code)
                .msg(Optional.ofNullable(msg).orElse(""))
                .data(null == data ? "" : data)
                .build();
    }

    public static R fail(String msg, Object data) {
        return fail(500, msg, data);
    }


    public static R fail(String msg) {
        return fail(msg, null);
    }

    public static R fail() {
        return fail("Request Fail");
    }


    @Override
    public String toString() {
        return JSON.toJSONString(R.builder().code(code).msg(msg).data(data).build());
    }
}
