package com.example.readword.wordtest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Qin
 * @version 1.0
 * @description
 * @since 2020/8/16
 */
@Slf4j
public class WordUtils {

    /**
     * @param srcPath  word模板数据源路径
     * @param destPath word导出路径
     * @param map      关键字键值对映射
     * @throws Exception
     */
    public static void replaceWord(String srcPath, String destPath, Map<String, String> map) throws Exception {
        FileOutputStream out = null;
        FileInputStream input = null;
        try {
            if ("doc".equals(srcPath.split("\\.")[1])) {
                input = new FileInputStream(new File(srcPath));
                HWPFDocument document = new HWPFDocument(input);
                Range range = document.getRange();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (entry.getValue() == null) {
                        entry.setValue("  ");
                    }
                    range.replaceText(entry.getKey(), entry.getValue(),32);
                }
                ByteArrayOutputStream ostream = new ByteArrayOutputStream();
                out = new FileOutputStream(new File(destPath));
                document.write(out);
                out.write(ostream.toByteArray());
                out.flush();
            } else {
                XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(srcPath));
                // 替换段落中的指定文字
                Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
                while (itPara.hasNext()) {
                    XWPFParagraph paragraph = itPara.next();
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        String oneparaString = run.getText(run.getTextPosition());
                        if (StringUtils.isBlank(oneparaString)) {
                            continue;
                        }
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
                        }
                        run.setText(oneparaString, 0);
                    }
                }
                ByteArrayOutputStream ostream = new ByteArrayOutputStream();
                out = new FileOutputStream(new File(destPath));
                document.write(out);
                out.write(ostream.toByteArray());
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (input != null) {
                input.close();
            }
        }
    }
}
