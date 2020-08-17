package com.example.readword.wordtest;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Qin
 * @version 1.0
 * @description
 * @since 2020/8/16
 */
public class WordTest {


    @Test
    public void testWord() throws Exception {
        String srcPath = "E:\\/src.docx";
        String desPath = "E:\\/des.docx";
        Map<String, String> map = new HashMap<>();
        map.put("_alibaba", "好了可以了");
        WordUtils.replaceWord(srcPath, desPath, map);
    }


    @Test
    public void poiControlDoc() {
        String srcPath = "E:\\/src.docx";
        String desPath = "E:\\/des.doc";
        System.out.println("========");
        try (FileInputStream fis = new FileInputStream(desPath)) {
            HWPFDocument doc = new HWPFDocument(fis);
            System.out.println(doc.getTextTable());
            System.out.println(doc.getListTables());

            Range range = doc.getRange();
            range.replaceText("alibaba", "shawanyi",32);
            String text = range.text();
            System.out.println(text);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void controlDocx() {

        String srcPath = "E:\\/src.docx";
        String desPath = "E:\\/des.doc";
        try (FileInputStream fis = new FileInputStream(srcPath)) {
            XWPFDocument doc = new XWPFDocument(fis);
            XWPFWordExtractor wordExtractor = new XWPFWordExtractor(doc);
            String text = wordExtractor.getText();


            System.out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
