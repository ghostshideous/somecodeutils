package com.example.readword.util.lisener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * excel 文件 监听
 *
 * @Author: WangGQ
 * @Date: 2020/7/8 13:07
 * @Desc:
 */
@Slf4j
public abstract class AbstractAnalysisEventListener<T> extends AnalysisEventListener<T> {

    //错误信息
    public String errorMessage = "";

    //读取的数据
    List<T> list;

    //默认批量保存的条数
    protected static final int BATCH_COUNT = 10;

    ThreadLocal<HttpServletResponse> currentResp;

    //上传失败的内容
    public List errorData;

    public HttpServletResponse getResp() {
        return currentResp.get();
    }

    public boolean setResp(HttpServletResponse response) {
        currentResp.set(response);
        return true;
    }


    @Override
    public void invoke(T data, AnalysisContext context) {
        log.debug("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
        try {
            if (StringUtils.isNotBlank(this.errorMessage))
                this.getResp().getWriter().write("上传失败的内容:" + errorData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract void saveData();

}
