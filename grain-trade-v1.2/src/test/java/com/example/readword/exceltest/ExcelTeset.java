package com.example.readword.exceltest;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Qin
 * @version 1.0
 * @description
 * @since 2020/7/23
 */
public class ExcelTeset {


    @Test
    public void exportExcel() {
        String path = "E:\\/src.xlsx";
        List<List<Object>> list = new ArrayList<>();
        list.add(Arrays.asList(1, "John", 12));
        list.add(Arrays.asList(0, "Amy", 159));
        list.add(Arrays.asList(2, "Sasiki", 1289));
        List<String> head = Arrays.asList("编号", "姓名", "年龄");
        ExcelUtils.writeBySimple(path, list, head);
    }

    @Test
    public void readXls() {

        String filePath = "E:\\/src.xls";
        List<Object> objects = ExcelUtils.readLessThan1000Row(filePath);
        objects.forEach(System.out::println);

    }

}
