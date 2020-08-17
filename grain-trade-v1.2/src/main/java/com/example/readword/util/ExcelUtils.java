package com.example.readword.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * excel 工具类
 *
 * 暂只提供web上产下载
 *
 * @Author: WangGQ
 * @Date: 2020/7/7 21:03
 * @Desc:
 */
public class ExcelUtils {

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>1. 创建excel对应的实体对象 参照{@link CompanyInfoPO}
     * <p>2. 设置返回的 参数
     * <p>3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     *
     * @param response       httpResponse
     * @param exportFileName 导出后文件名
     * @param sheetName      sheet名
     * @param modelClass     实体类class
     * @param data           数据
     * @throws IOException
     */
    public static void export(HttpServletResponse response, String exportFileName,
                              String sheetName, Class modelClass, List<?> data) throws IOException {

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(exportFileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), modelClass)
                .sheet(sheetName)
                .doWrite(data);
    }


    /**
     * 文件导入
     * <p>
     * * 文件上传
     * * <p>1. 创建excel对应的实体对象 参照{@link CompanyInfoPO}
     * * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link CompanyDataListener}
     * * <p>3. 直接读即可
     *
     * @param file     file :前端上传进来的文件流
     * @param clazz    Excel对应实体类
     * @param listener 监听器: 异步读取操作数据
     * @throws IOException
     */
    public static void importFile(MultipartFile file, Class clazz, AnalysisEventListener listener) throws IOException {

        EasyExcel.read(file.getInputStream(), clazz, listener).sheet().doRead();

    }
}
