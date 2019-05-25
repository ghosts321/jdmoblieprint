package com.lanxum.jd.mobile.print.common.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by JiangJunpeng on 2019/3/6.<br>
 */
public class PageRangeUtils {
    /**
     * 截取pdfFile的第beginPage页至第endPage页，组成一个新的文件名
     *
     * @param inputPdfFilePath  需要分割的PDF
     * @param outputPdfFilePath 新PDF
     * @param beginPage         起始页
     * @param endPage           结束页
     */
    public static String splitPdfFile(String inputPdfFilePath, String outputPdfFilePath, int beginPage, int endPage) {
        Document document;
        PdfCopy copy;
        try {
            PdfReader reader = new PdfReader(inputPdfFilePath);
            int n = reader.getNumberOfPages();
            if (endPage==0||endPage>=n){
                endPage = n;
            }
            if (beginPage>=n){
                beginPage = n;
            }
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(outputPdfFilePath));
            document.open();
            for(int j = beginPage; j <= endPage; j++){
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();
            return "success";
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return "error";
        }
    }

    private void rotatePdfFile(String inputPdfFilePath, String outputPdfFilePath){
        try {
            PdfReader reader = new PdfReader(inputPdfFilePath);
            Document document = new Document(PageSize.A4, 36,70, 120, 100);
            PdfCopy copy = new PdfSmartCopy(document, new FileOutputStream(outputPdfFilePath));
            document.open();
            int n = reader.getNumberOfPages();
            PdfDictionary pd;
            for(int j=1; j<=n; j++){
                pd = reader.getPageN(j);
                pd.put(PdfName.ROTATE, new PdfNumber(90));
            }
            for(int page = 1; page <= n; page++){
                copy.addPage(copy.getImportedPage(reader, page));
            }
            document.close();

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
