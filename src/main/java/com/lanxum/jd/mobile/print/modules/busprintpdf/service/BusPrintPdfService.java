/**
 * Powered By [国家文物局综合行政管理平台]
 * The company：北京汇金科技有限责任公司
 * Web Site: http://www.nasoft.com.cn/
 * department：技术部
 * Since 2016 - 2019
 */

package com.lanxum.jd.mobile.print.modules.busprintpdf.service;

import com.lanxum.jd.mobile.print.modules.busprintpdf.entity.PrintInfoEntity;
import com.lanxum.jd.mobile.print.modules.busprintpdf.mapper.BusPrintPdfMapper;


import com.lanxum.jd.mobile.print.common.base.service.BaseService;
import com.lanxum.jd.mobile.print.modules.busprintfile.entity.BusPrintFile;
import com.lanxum.jd.mobile.print.modules.busprintpdf.entity.BusPrintPdf;

import java.util.List;

/**
 * BusPrintPdf service接口
 * @author nasoft
 *
 */
public interface BusPrintPdfService extends BaseService<BusPrintPdfMapper, BusPrintPdf> {

    /**
     * 保存pdf信息
     *
     * @param busPrintPdf
     * @param busPrintFile
     * @param token
     * @return
     */
    int insertPrintPdf(BusPrintPdf busPrintPdf, BusPrintFile busPrintFile, String token);

    /**
     * 打印单面文件
     *
     * @param printInfoEntity
     * @param subNewFileName
     * @param flieType
     * @return
     * @throws Exception
     */
    List<String> printPdf(PrintInfoEntity printInfoEntity, String subNewFileName, String flieType) throws Exception;

    /**
     * 生成pcl并上传到指定服务器路径下
     *
     * @param printInfoEntity
     * @param subNewFileName
     * @param data1
     * @param fileType
     * @return
     * @throws Exception
     */
    Integer sendPcl(PrintInfoEntity printInfoEntity, String subNewFileName, String data1, String fileType) throws Exception;

    /**
     * 生成xml并上传到指定服务器路径下
     *
     * @param printInfoEntity
     * @param data1
     * @return
     * @throws Exception
     */
    Integer sendXml(PrintInfoEntity printInfoEntity, String data1) throws Exception;
}