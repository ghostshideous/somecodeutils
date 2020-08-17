package com.example.readword.exceltest;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Qin
 * @version 1.0
 * @description
 * @since 2020/7/23
 */
@Data
@AllArgsConstructor
public class ExcelModel{

    @ExcelProperty(value = "ID", index = 0)
    private Integer id;
    @ExcelProperty(value = "姓名", index = 1)
    private String name;
    @ExcelProperty(value = "年龄", index = 2)
    private Integer age;

}
