
package com.lanxum.jd.mobile.print.modules.busprintpdf.service.impl;

import com.lanxum.jd.mobile.print.common.base.service.impl.BaseServiceImpl;
import com.lanxum.jd.mobile.print.common.utils.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.lanxum.jd.mobile.print.modules.busmobprintstatus.entity.BusMobprintStatus;
import com.lanxum.jd.mobile.print.modules.busmobprintstatus.service.BusMobprintStatusService;
import com.lanxum.jd.mobile.print.modules.busprintpdf.entity.PrintInfoEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanxum.jd.mobile.print.modules.busprintfile.entity.BusPrintFile;
import com.lanxum.jd.mobile.print.modules.busprintfile.mapper.BusPrintFileMapper;
import com.lanxum.jd.mobile.print.modules.busprintpdf.entity.BusPrintPdf;
import com.lanxum.jd.mobile.print.modules.busprintpdf.mapper.BusPrintPdfMapper;
import com.lanxum.jd.mobile.print.modules.busprintpdf.service.BusPrintPdfService;
import com.lanxum.jd.mobile.print.modules.bususer.entity.BusUser;
import com.lanxum.jd.mobile.print.modules.bususer.mapper.BusUserMapper;
import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.entity.BusUserPrintFileMap;
import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.mapper.BusUserPrintFileMapMapper;

/**
 * BusPrintPdf service接口实现类
 *
 * @author
 */
@Service
@Transactional
public class BusPrintPdfServiceImpl extends BaseServiceImpl<BusPrintPdfMapper, BusPrintPdf> implements BusPrintPdfService {

    @Resource
    private BusPrintPdfMapper busPrintPdfMapper;

    @Resource
    private BusPrintFileMapper busPrintFileMapper;

    @Resource
    private BusUserMapper busUserMapper;

    @Resource
    private BusUserPrintFileMapMapper busUserPrintFileMapMapper;

    @Resource
    private BusMobprintStatusService busMobprintStatusService;

    @Resource
    private BusPrintPdfService busPrintPdfService;

    /**
     * 保存pdf信息
     */
    @Override
    public int insertPrintPdf(BusPrintPdf busPrintPdf, BusPrintFile busPrintFile, String token) {
        //对token解密获取用户名称
        String userName = AESUtil.Decrypt(token);
        List<BusUser> busUserList = busUserMapper.queryDataByWhere("WHERE name = '" + userName + "'");
        Integer userId = 0;
        if (busUserList != null && busUserList.size() > 0) {
            userId = busUserList.get(0).getId();
        }
        busPrintPdfMapper.insert(busPrintPdf);
        busPrintFile.setDuplexFlag(busPrintFile.getDuplexFlag());
        busPrintFile.setCollateFlag(0);
        busPrintFile.setDriverName("TOSHIBA Universal PS3");
        busPrintFile.setPrintServerName("BJZB-PRINT-1");
        busPrintFile.setFormat("(A4,彩色)*1");
        busPrintFile.setAnalysisResultSlz("ZWwuUGFnZQIAAAAJCQAAAAUJAAAAOkRvbWFzLkluZnJhc3RydWN0dXJlLkpvYkhhbmRsZXJNb2R1bGUuSW50ZXJmYWNlLk1vZGVsLlBhZ2UCAAAAGjxQYXBlclR5cGU+a19fQmFja2luZ0ZpZWxkGDxJc0NvbG9yPmtfX0JhY2tpbmdGaWVsZAEAAQIAAAAGCgAAAAJBNAEL");
        busPrintFile.setPrintDate(new Date());
        busPrintFile.setBusVirtualPortPrinterId(37);
        busPrintFile.setLangTypeVal(0);
        busPrintFile.setCreateDate(new Date());
        busPrintFile.setExpiredDate(new Date());
        busPrintFile.setA3Num(0);
        busPrintFile.setBwNum(0);
        busPrintFile.setColorNum(0);
        busPrintFile.setDuplexNum(0);
        busPrintFile.setPreviewFlag(0);
        busPrintFile.setArchiveFlag(0);
        busPrintFileMapper.insert(busPrintFile);
        BusUserPrintFileMap busUserPrintFileMap = new BusUserPrintFileMap();
        busUserPrintFileMap.setBusUserId(userId);
        busUserPrintFileMap.setBusPrintFileId(busPrintFile.getId());
        busUserPrintFileMap.setUserPrintFileMapTypeVal(0);
        busUserPrintFileMap.setCreateDate(new Date());
        busUserPrintFileMapMapper.insert(busUserPrintFileMap);

        return busPrintFile.getId();
    }

    /**
     * @param printInfoEntity
     * @param subNewFileName
     * @param flieType
     * @return
     * @throws Exception
     */
    @Override
    public List<String> printPdf(PrintInfoEntity printInfoEntity, String subNewFileName, String fileType) throws Exception {
        Integer copies = printInfoEntity.getCopies();
        String duplex = printInfoEntity.getDupLex();
        String taskId = printInfoEntity.getTaskId();

        List<String> list = new ArrayList<>();

        String data = DateUtils.getNowFormat(new Date());
        //生成pcl文件
        Integer pclReturn = busPrintPdfService.sendPcl(printInfoEntity, subNewFileName, data, fileType);
        Integer xmlReturn = busPrintPdfService.sendXml(printInfoEntity, data);
        if (pclReturn == 0 || xmlReturn == 0) {
            return list;
        }

        //检查是否有取消打印操作
        BusMobprintStatus busMobprintStatus = busMobprintStatusService.getEntityByPK(taskId);
        String printStatus = busMobprintStatus.getPrintStatus();

        if (!"1".equals(printStatus)) {
            return list;
        }
        for (int j = 0; j < copies; j++) {
            //保存打印表
            BusPrintFile busPrintFile = new BusPrintFile();
            busPrintFile.setName(printInfoEntity.getFileName());
            busPrintFile.setPath("D:\\domas\\input\\" + data + ".pcl.xml");
            busPrintFile.setSizeNum(0);
            busPrintFile.setCopyNum(copies);
            busPrintFile.setPageNum(Integer.valueOf(printInfoEntity.getPageRangeEnd()) - Integer.valueOf(printInfoEntity.getPageRangeStart()));
            busPrintFile.setDuplexFlag(Integer.valueOf(duplex));

            BusPrintPdf busPrintPdf = new BusPrintPdf();
            busPrintPdf.setImportUrl(printInfoEntity.getFileUrl());
            busPrintPdf.setImportType(printInfoEntity.getExtName());
            busPrintPdf.setCreateDate(new Date());

            int i = busPrintPdfService.insertPrintPdf(busPrintPdf, busPrintFile, printInfoEntity.getToken());

            list.add(String.valueOf(i));
        }
        return list;
    }

    /**
     * @param printInfoEntity
     * @param subNewFileName
     * @param data1
     * @param fileType
     * @return
     * @throws Exception
     */
    @Override
    public Integer sendPcl(PrintInfoEntity printInfoEntity, String subNewFileName, String data, String fileType) throws Exception {
        String newFileName = printInfoEntity.getNewFileName();
        //生成pcl文件
        if (subNewFileName != null && !"".equals(subNewFileName)) {
            newFileName = subNewFileName;
        }
        boolean aa = subNewFileName == null || "".equals(subNewFileName);
        if ("1".equals(fileType) && aa) {
            String pclRes = PdfToPcl.pdfToPcl(
                    "D:\\pdf2vec_cmd\\pdf2vec.exe",
                    "D:\\nginx\\html\\" + newFileName,
                    "D:\\nginx\\html\\" + data + ".pcl");
            if ("error".equals(pclRes)) {
                return 0;//失败
            }
        } else {
            String pclRes = PdfToPcl.pdfToPcl(
                    "D:\\pdf2vec_cmd\\pdf2vec.exe",
                    "D:\\nginx\\html\\" + newFileName + ".pdf",
                    "D:\\nginx\\html\\" + data + ".pcl");
            if ("error".equals(pclRes)) {
                return 0;//失败
            }
        }
        // 启动客户端连接
        SocketSend socketSend = new SocketSend();
        // 传输文件
        String socketRes = socketSend.sendFile("D:\\nginx\\html\\" + data + ".pcl", "");
        if ("error".equals(socketRes)) {
            return 0;//0是失败
        } else {
            return 1;//1是成功
        }
    }

    /**
     * @param printInfoEntity
     * @param data1
     * @return
     * @throws Exception
     */
    @Override
    public Integer sendXml(PrintInfoEntity printInfoEntity, String data) throws Exception {
        //生成xml文件
        String xmlRes = UpdateXMLData.updXml(
                "D:\\nginx\\html\\" + data + ".pcl.xml",
                "no",
                "00",
                "10.2.255.34",
                "0.0.0.0",
                "360BUYAD",
                printInfoEntity.getUserName(),
                printInfoEntity.getUserName(),
                "35",
                "TOSHIBA Universal Printer 2",
                "DS_10.2.153.27",
                printInfoEntity.getNewFileName(),
                "9",
                "1",
                String.valueOf(printInfoEntity.getCopies()),
                printInfoEntity.getDupLex(),
                "1",
                "1",
                data + ".pcl",
                "100000",
                String.valueOf(DateUtils.getNow()),
                "00");
        if ("error".equals(xmlRes)) {
            return 0;//0是失败
        }
        // 启动客户端连接
        SocketSend socketSend = new SocketSend();
        // 传输文件
        String socketRes = socketSend.sendFile("D:\\nginx\\html\\" + data + ".pcl.xml", "");
        if ("error".equals(socketRes)) {
            return 0;//0是失败
        } else {
            return 1;//1是成功
        }
    }
}
