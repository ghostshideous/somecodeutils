package com.example.readword.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: WangGQ
 * @Date: 2020/7/20 23:18
 * @Desc:
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExcelEnum {

    EXCEL_EXPORT_BOOTH_INFO(1,"展位信息"),


    ;

    private int code;

    private String msg;



}
