package com.lanxum.jd.mobile.print.common.utils;

import java.io.FileWriter;

/**
 *
 * @author JiangJunpeng
 * @date 2019/3/18
 */
public class UpdateXMLData {

    public static String updXml(String filePath, String userId, String cJobId, String ipAddr, String computerName,
                              String domain, String domainAccount, String printServer, String jpPrinter,
                              String driverName, String portName, String fileName, String paperSize,
                              String pages, String copies, String duplex, String color, String collate,
                              String processedFileName, String processedFileSize, String processedFileTime,
                              String checkBit){

        try {
            String str = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                    "<Attribute>\n" +
                    "<UserID>"+userId+"</UserID>\n" +
                    "<CJobID>"+cJobId+"</CJobID>\n" +
                    "<IPAddr>"+ipAddr+"</IPAddr>\n" +
                    "<WorkGroup/>\n" +
                    "<ComputerName>"+computerName+"</ComputerName>\n" +
                    "<Domain>"+domain+"</Domain>\n" +
                    "<DomainAccount>"+domainAccount+"</DomainAccount>\n" +
                    "<PrintServer/>\n" +
                    "<PrintServerAccount>"+printServer+"</PrintServerAccount>\n" +
                    "<JPrinterName>"+jpPrinter+"</JPrinterName>\n" +
                    "<DriverName>"+driverName+"</DriverName>\n" +
                    "<PortName>"+portName+"</PortName>\n" +
                    "<FileName>"+fileName+"</FileName>\n" +
                    "<PaperSize>"+paperSize+"</PaperSize>\n" +
                    "<Pages>"+pages+"</Pages>\n" +
                    "<Copies>"+copies+"</Copies>\n" +
                    "<Duplex>"+duplex+"</Duplex>\n" +
                    "<Color>"+color+"</Color>\n" +
                    "<Collate>"+collate+"</Collate>\n" +
                    "<ProcessedFileName>"+processedFileName+"</ProcessedFileName>\n" +
                    "<ProcessedFileSize>"+processedFileSize+"</ProcessedFileSize>\n" +
                    "<ProcessedFileTime>"+processedFileTime+"</ProcessedFileTime>\n" +
                    "<CheckBit>"+checkBit+"</CheckBit>\n" +
                    "</Attribute>";
            // 创建文件输出流
            FileWriter fout = new FileWriter(filePath);
            // 写入文件
            fout.write(str.toCharArray());
            // 关闭输出流
            fout.close();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
