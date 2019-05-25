package com.lanxum.jd.mobile.print.common.utils;

/**
 *
 * @author JiangJunpeng
 * @date 2019/3/19
 */
public class PdfToPcl {
    public static String pdfToPcl(String EXEPath, String FileInptPath, String FileOutputPath){
        String[] cmd = {EXEPath, "-$", "6884D42A4328V5EFA394", FileInptPath, FileOutputPath};
        Process process;
        try {
            process = new ProcessBuilder(cmd).start();
            process.waitFor();
            return "success";
        }
        catch(Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
