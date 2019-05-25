/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.busprintpdf.controller;


import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.lanxum.jd.mobile.print.common.base.controller.BaseController;
import com.lanxum.jd.mobile.print.common.constant.Constant;
import com.lanxum.jd.mobile.print.common.utils.*;
import com.lanxum.jd.mobile.print.common.web.ResponseMap;
import com.lanxum.jd.mobile.print.modules.busmobprintstatus.entity.BusMobprintStatus;
import com.lanxum.jd.mobile.print.modules.busmobprintstatus.service.BusMobprintStatusService;
import com.lanxum.jd.mobile.print.modules.busprintfile.entity.BusPrintFile;
import com.lanxum.jd.mobile.print.modules.busprintfile.mapper.BusPrintFileMapper;
import com.lanxum.jd.mobile.print.modules.busprintfile.service.BusPrintFileService;
import com.lanxum.jd.mobile.print.modules.busprintpdf.entity.BusPrintPdf;
import com.lanxum.jd.mobile.print.modules.busprintpdf.entity.PrintInfoEntity;
import com.lanxum.jd.mobile.print.modules.busprintpdf.service.BusPrintPdfService;
import com.lanxum.jd.mobile.print.modules.bususer.entity.BusUser;
import com.lanxum.jd.mobile.print.modules.bususer.service.BusUserService;
import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.entity.BusUserPrintFileMap;
import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.mapper.BusUserPrintFileMapMapper;
import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.service.BusUserPrintFileMapService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BusPrintPdf controller
 *
 * @author horizoner
 */
@CrossOrigin
@RestController
@RequestMapping("/busPrintPdf")
public class BusPrintPdfController extends BaseController {
    @Resource
    private BusPrintPdfService busPrintPdfService;

    @Resource
    private BusPrintFileService busPrintFileService;

    @Resource
    private BusMobprintStatusService busMobprintStatusService;

    @Resource
    private BusUserService busUserService;

    @Resource
    private BusUserPrintFileMapMapper busUserPrintFileMapMapper;

    @Resource
    private BusPrintFileMapper busPrintFileMapper;

    @Resource
    private BusUserPrintFileMapService busUserPrintFileMapService;

    /**
     * @param token
     * @param fileUrl待打印文件网络地址
     * @param pageRangeStart起始
     * @param pageRangeEnd终止
     * @param copies份数(默认1份)
     * @param duplex单双面0单面，1双面
     * @param fileName文件名称
     * @param extName扩展名
     * @return
     */
    @SuppressWarnings("resource")
    @RequestMapping("/printPdf")
    public ResponseEntity<Map<String, Object>> printPdf(HttpServletRequest request) {

        String taskId = request.getParameter("taskId");
        String token = request.getParameter("token");
        String fileUrl = request.getParameter("fileUrl");
        String fileName = request.getParameter("fileName");
        String extName = request.getParameter("extName");
        String duplex = request.getParameter("duplex");
        String copies = request.getParameter("copies");
        String pageRangeStart = request.getParameter("pageRangeStart");
        String pageRangeEnd = request.getParameter("pageRangeEnd");

        if (taskId == null || "".equals(taskId)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "taskId不能为空！", "").getResponseMap());
        }
        if (token == null || "".equals(token)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "token不能为空！", "").getResponseMap());
        }

        if (fileUrl == null || "".equals(fileUrl)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "url参数不能为空！", "").getResponseMap());
        }
        if (fileName == null || "".equals(fileName)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "fileName参数不能为空！", "").getResponseMap());
        }
        fileName.replace(" ", "");

        if (duplex == null || "".equals(duplex)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "duplex参数不能为空！", "").getResponseMap());
        }
        if (copies == null || "".equals(copies)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "copies参数不能为空！", "").getResponseMap());
        }
        if (pageRangeStart == null || "".equals(pageRangeStart)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "起始页不能为空！", "").getResponseMap());
        }
        if (pageRangeEnd == null || "".equals(pageRangeEnd)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "终止页不能为空！", "").getResponseMap());
        }
        //判断起始页和终止页是否为数字
        if (!CommonUtil.isNumeric(pageRangeStart)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "起始页必须为数字！", "").getResponseMap());
        }
        if (!CommonUtil.isNumeric(pageRangeEnd)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "终止页必须为数字！", "").getResponseMap());
        }
        if (Integer.valueOf(pageRangeStart) > Integer.valueOf(pageRangeEnd)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "起始页不能大于终止页！", "").getResponseMap());
        }
        if (extName == null || "".equals(extName)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "文件扩展名不能为空！", "").getResponseMap());
        }

        System.out.println(fileName + ",开始打印文件，当前时间：" + DateUtils.getNow());

        PrintInfoEntity printInfoEntity = new PrintInfoEntity();
        printInfoEntity.setCopies(Integer.valueOf(copies));
        printInfoEntity.setDupLex(duplex);
        printInfoEntity.setExtName(extName);
        printInfoEntity.setFileName(fileName);
        printInfoEntity.setFileUrl(fileUrl);
        printInfoEntity.setPageRangeEnd(pageRangeEnd);
        printInfoEntity.setPageRangeStart(pageRangeStart);
        printInfoEntity.setTaskId(taskId);
        printInfoEntity.setToken(token);

        List<String> list = new ArrayList<>();

        try {
            fileUrl = URLDecoder.decode(fileUrl,"utf-8");
            String userName = AESUtil.Decrypt(token);
            printInfoEntity.setUserName(userName);
            List<BusUser> busUserList = busUserService.queryDataByWhere("WHERE name = '" + userName + "'");
            Integer userId = 0;
            if (busUserList != null && busUserList.size() > 0) {
                userId = busUserList.get(0).getId();
            }
            int cop = Integer.valueOf(copies);

            //根据taskId查询是否有正在进行的打印任务
            BusMobprintStatus busMobprintStatus = busMobprintStatusService.getEntityByPK(taskId);
            if ("".equals(busMobprintStatus) || busMobprintStatus == null) {
                BusMobprintStatus mobprintStatus = new BusMobprintStatus();
                mobprintStatus.setTaskId(taskId);
                mobprintStatus.setPrintStatus("1");
                mobprintStatus.setCreateTime(DateUtils.getNow());
                int j = busMobprintStatusService.addEntity(mobprintStatus);
                if (j == 0) {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMap(Constant.UNKNOWN_ERROR, "保存taskId失败！", "").getResponseMap());
                }
                //生成新文件名称
                String date = DateUtils.getNowFormat(new Date());
                String newFileName = date + extName;
                printInfoEntity.setNewFileName(newFileName);
                //下载文件
                System.out.println("文件链接：" + fileUrl);
                DownLoadFile httpMulitThreadDownload = new DownLoadFile(1, fileUrl, newFileName, "D:\\nginx\\html\\");
                String downs = httpMulitThreadDownload.MulitThreadDownload();
                if ("error".equals(downs)) {
                    BusMobprintStatus bus = new BusMobprintStatus();
                    bus.setTaskId(taskId);
                    bus.setPrintStatus("3");
                    busMobprintStatusService.updateEntityByPKSelective(bus);
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMap(Constant.UNKNOWN_ERROR, "下载文件失败！", "").getResponseMap());
                }

                //转换url文件为pdf格式
                switch (extName) {
                    case ".jpg":
                    case ".png":
                    case ".jpeg":
                    case ".gif": {
                        String res = OfficeToPDFUtils.pic2Pdf(
                                "D:\\nginx\\html\\" + newFileName,
                                "D:\\nginx\\html\\" + newFileName + ".pdf");
                        if ("error".equals(res)) {
                            BusMobprintStatus bus = new BusMobprintStatus();
                            bus.setTaskId(taskId);
                            bus.setPrintStatus("3");
                            busMobprintStatusService.updateEntityByPKSelective(bus);
                            return ResponseEntity.status(HttpStatus.OK)
                                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, "img转换PDF文件失败！", "").getResponseMap());
                        }
                        break;
                    }
                    case ".doc":
                    case ".docx": {
                        OfficeToPDFUtils.doc2Pdf(
                                "D:\\nginx\\html\\" + newFileName,
                                "D:\\nginx\\html\\" + newFileName + ".pdf");
                        break;
                    }
                    case ".ppt":
                    case ".pptx": {
                        OfficeToPDFUtils.ppt2Pdf(
                                "D:\\nginx\\html\\" + newFileName,
                                "D:\\nginx\\html\\" + newFileName + ".pdf");
                        break;
                    }
                    case ".xls":
                    case ".xlsx": {
                        OfficeToPDFUtils.excel2Pdf(
                                "D:\\nginx\\html\\" + newFileName,
                                "D:\\nginx\\html\\" + newFileName + ".pdf");
                        break;
                    }
                    case ".txt":
                        String res1 = OfficeToPDFUtils.text2pdf("D:\\nginx\\html\\" + newFileName,
                                "D:\\nginx\\html\\" + newFileName + ".pdf");
                        if ("error".equals(res1)) {
                            BusMobprintStatus bus = new BusMobprintStatus();
                            bus.setTaskId(taskId);
                            bus.setPrintStatus("3");
                            busMobprintStatusService.updateEntityByPKSelective(bus);
                            return ResponseEntity.status(HttpStatus.OK)
                                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, "txt转换PDF文件失败！", "").getResponseMap());
                        }
                        break;

                    //pdf特殊处理
                    case ".pdf":

                        String defPage = "0";
                        String subNewFileName = null;

                        //截取指定页数范围
                        if (!defPage.equals(pageRangeStart) && !defPage.equals(pageRangeEnd)) {
                            int pageStart = Integer.valueOf(pageRangeStart);
                            int pageEnd = Integer.valueOf(pageRangeEnd);
                            subNewFileName = newFileName + "1";
                            String res2 = PageRangeUtils.splitPdfFile(
                                    "D:\\nginx\\html\\" + newFileName,
                                    "D:\\nginx\\html\\" + subNewFileName + ".pdf",
                                    pageStart,
                                    pageEnd);
                            if ("error".equals(res2)) {
                                BusMobprintStatus bus = new BusMobprintStatus();
                                bus.setTaskId(taskId);
                                bus.setPrintStatus("3");
                                busMobprintStatusService.updateEntityByPKSelective(bus);
                                return ResponseEntity.status(HttpStatus.OK)
                                        .body(new ResponseMap(Constant.UNKNOWN_ERROR, "截取指定页数失败！", "").getResponseMap());
                            }
                        }

                        if ("1".equals(duplex)) {
                            list = duplexPrint(printInfoEntity, subNewFileName, "1");
                            if (list == null || list.size() == 0) {
                                BusMobprintStatus bus = new BusMobprintStatus();
                                bus.setTaskId(taskId);
                                bus.setPrintStatus("3");
                                busMobprintStatusService.updateEntityByPKSelective(bus);
                                System.out.println(fileName + ",打印文件失败，1113当前时间：" + DateUtils.getNow());
                                return ResponseEntity.status(HttpStatus.OK)
                                        .body(new ResponseMap(Constant.UNKNOWN_ERROR, "打印失败", "").getResponseMap());
                            }
                            BusMobprintStatus bus = new BusMobprintStatus();
                            bus.setTaskId(taskId);
                            bus.setPrintStatus("2");
                            busMobprintStatusService.updateEntityByPKSelective(bus);

                            //将待打印文件ids存入数据库
                            StringBuffer sb = new StringBuffer();
                            for (String val:list) {
                                sb.append(val);
                                sb.append(",");
                            }
                            sb.deleteCharAt(sb.length()-1);
                            String ids = sb.toString();
                            BusMobprintStatus busMobprintStatus1 = new BusMobprintStatus();
                            busMobprintStatus1.setTaskId(taskId);
                            busMobprintStatus1.setLog(ids);
                            busMobprintStatusService.updateEntityByPK(busMobprintStatus1);

                            System.out.println(fileName + ",打印文件成功，1114当前时间：" + DateUtils.getNow());
                            return ResponseEntity.status(HttpStatus.OK)
                                    .body(new ResponseMap(Constant.SUCCESS_STATE, "打印成功！", list).getResponseMap());

                        }
                        if (cop > 0) {
                            list = busPrintPdfService.printPdf(printInfoEntity, subNewFileName, "1");
                            if (list == null || list.size() == 0) {
                                BusMobprintStatus bus = new BusMobprintStatus();
                                bus.setTaskId(taskId);
                                bus.setPrintStatus("3");
                                busMobprintStatusService.updateEntityByPKSelective(bus);
                                System.out.println(fileName + ",打印文件失败，1115当前时间：" + DateUtils.getNow());
                                return ResponseEntity.status(HttpStatus.OK)
                                        .body(new ResponseMap(Constant.UNKNOWN_ERROR, "打印失败", "").getResponseMap());
                            }
                            BusMobprintStatus bus = new BusMobprintStatus();
                            bus.setTaskId(taskId);
                            bus.setPrintStatus("2");
                            busMobprintStatusService.updateEntityByPKSelective(bus);

                            //将待打印文件ids存入数据库
                            StringBuffer sb = new StringBuffer();
                            for (String val:list) {
                                sb.append(val);
                                sb.append(",");
                            }
                            sb.deleteCharAt(sb.length()-1);
                            String ids = sb.toString();
                            BusMobprintStatus busMobprintStatus1 = new BusMobprintStatus();
                            busMobprintStatus1.setTaskId(taskId);
                            busMobprintStatus1.setLog(ids);
                            busMobprintStatusService.updateEntityByPK(busMobprintStatus1);

                            System.out.println(fileName + ",打印文件成功，1116当前时间：" + DateUtils.getNow());
                            return ResponseEntity.status(HttpStatus.OK)
                                    .body(new ResponseMap(Constant.SUCCESS_STATE, "打印成功！", list).getResponseMap());

                        }
                    default:
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseMap(Constant.UNKNOWN_ERROR, "请打印规定格式文件！", "").getResponseMap());
                }

                //截取指定页数范围，默认值为0
                String defPage = "0";
                String subNewFileName = null;
                if (!defPage.equals(pageRangeStart) && !defPage.equals(pageRangeEnd)) {
                    int pageStart = Integer.valueOf(pageRangeStart);
                    int pageEnd = Integer.valueOf(pageRangeEnd);
                    subNewFileName = newFileName + "1";
                    String res = PageRangeUtils.splitPdfFile(
                            "D:\\nginx\\html\\" + newFileName + ".pdf",
                            "D:\\nginx\\html\\" + subNewFileName + ".pdf",
                            pageStart,
                            pageEnd);
                    if ("error".equals(res)) {
                        BusMobprintStatus bus = new BusMobprintStatus();
                        bus.setTaskId(taskId);
                        bus.setPrintStatus("3");
                        busMobprintStatusService.updateEntityByPKSelective(bus);
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseMap(Constant.UNKNOWN_ERROR, "截取指定页数失败！", "").getResponseMap());
                    }
                }

                if ("1".equals(duplex)) {
                    list = duplexPrint(printInfoEntity, subNewFileName, "");
                    if (list == null || list.size() == 0) {
                        BusMobprintStatus bus = new BusMobprintStatus();
                        bus.setTaskId(taskId);
                        bus.setPrintStatus("3");
                        busMobprintStatusService.updateEntityByPKSelective(bus);
                        System.out.println(fileName + ",打印文件失败，1119当前时间：" + DateUtils.getNow());
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseMap(Constant.UNKNOWN_ERROR, "打印失败", "").getResponseMap());
                    }
                    BusMobprintStatus bus = new BusMobprintStatus();
                    bus.setTaskId(taskId);
                    bus.setPrintStatus("2");
                    busMobprintStatusService.updateEntityByPKSelective(bus);

                    //将待打印文件ids存入数据库
                    StringBuffer sb = new StringBuffer();
                    for (String val:list) {
                        sb.append(val);
                        sb.append(",");
                    }
                    sb.deleteCharAt(sb.length()-1);
                    String ids = sb.toString();
                    BusMobprintStatus busMobprintStatus1 = new BusMobprintStatus();
                    busMobprintStatus1.setTaskId(taskId);
                    busMobprintStatus1.setLog(ids);
                    busMobprintStatusService.updateEntityByPK(busMobprintStatus1);

                    System.out.println(fileName + ",打印文件成功，1120当前时间：" + DateUtils.getNow());
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMap(Constant.SUCCESS_STATE, "打印成功！", list).getResponseMap());

                }

                if (cop > 0) {
                    list = busPrintPdfService.printPdf(printInfoEntity, subNewFileName, "");
                    if (list == null || list.size() == 0) {
                        BusMobprintStatus bus = new BusMobprintStatus();
                        bus.setTaskId(taskId);
                        bus.setPrintStatus("3");
                        busMobprintStatusService.updateEntityByPKSelective(bus);
                        System.out.println(fileName + ",打印文件失败，1121当前时间：" + DateUtils.getNow());
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseMap(Constant.UNKNOWN_ERROR, "打印失败", "").getResponseMap());
                    }
                    BusMobprintStatus bus = new BusMobprintStatus();
                    bus.setTaskId(taskId);
                    bus.setPrintStatus("2");
                    busMobprintStatusService.updateEntityByPKSelective(bus);

                    //将待打印文件ids存入数据库
                    StringBuffer sb = new StringBuffer();
                    for (String val:list) {
                        sb.append(val);
                        sb.append(",");
                    }
                    sb.deleteCharAt(sb.length()-1);
                    String ids = sb.toString();
                    BusMobprintStatus busMobprintStatus1 = new BusMobprintStatus();
                    busMobprintStatus1.setTaskId(taskId);
                    busMobprintStatus1.setLog(ids);
                    busMobprintStatusService.updateEntityByPK(busMobprintStatus1);

                    System.out.println(fileName + ",打印文件成功，1122当前时间：" + DateUtils.getNow());
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMap(Constant.SUCCESS_STATE, "打印成功！", list).getResponseMap());

                }
                //如果打印任务不为空
            } else {
                for (int i = 0; i <= 1000; i++) {
                    Thread.sleep(2000);
                    BusMobprintStatus busMobprint = busMobprintStatusService.getEntityByPK(taskId);
                    if ("".equals(busMobprint) || busMobprint == null) {
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseMap(Constant.UNKNOWN_ERROR, "程序未知错误！", "").getResponseMap());
                    } else {
                        String printStatus = busMobprint.getPrintStatus();
                        if ("2".equals(printStatus)) {

                            BusMobprintStatus busMobprintStatus1 = busMobprintStatusService.getEntityByPK(taskId);
                            String ids = busMobprintStatus1.getLog();
                            String aa [] = ids.split(",");
                            List list1 = new ArrayList();
                            for(String val:aa){
                                list1.add(val);
                            }

                            System.out.println(fileName + ",打印文件成功，1123当前时间：" + DateUtils.getNow());
                            return ResponseEntity.status(HttpStatus.OK)
                                    .body(new ResponseMap(Constant.SUCCESS_STATE, "打印成功！", list1).getResponseMap());
                        } else if ("3".equals(printStatus)) {
                            System.out.println(fileName + ",打印文件失败，1124当前时间：" + DateUtils.getNow());
                            return ResponseEntity.status(HttpStatus.OK)
                                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, "打印失败！", "").getResponseMap());
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
        }
        System.out.println(fileName + ",打印文件filed，UNKNOWN_ERROR，1125当前时间：" + DateUtils.getNow());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMap(Constant.UNKNOWN_ERROR, "打印失败!", "").getResponseMap());
    }

    /**
     * 待打印文件预览接口
     *
     * @param token
     * @param fileUrl待打印文件网络地址
     * @param pageRangeStart起始
     * @param pageRangeEnd终止
     * @param copies份数(默认1份)
     * @param duplex单双面0单面，1双面
     * @param fileName文件名称
     * @param extName扩展名
     * @return
     */
    @RequestMapping("/previewPdf")
    public ResponseEntity<Map<String, Object>> previewPdf(HttpServletRequest request) {

        String token = request.getParameter("token");
        String fileUrl = request.getParameter("fileUrl");
        String fileName = request.getParameter("fileName");
        String extName = request.getParameter("extName");
        String pageRangeStart = request.getParameter("pageRangeStart");
        String pageRangeEnd = request.getParameter("pageRangeEnd");

        if (token == null || "".equals(token)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "token不能为空！", "").getResponseMap());
        }

        if (fileUrl == null || "".equals(fileUrl)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "url参数不能为空！", "").getResponseMap());
        }
        if (fileName == null || "".equals(fileName)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "fileName参数不能为空！", "").getResponseMap());
        }
        if (pageRangeStart == null || "".equals(pageRangeStart)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "起始页不能为空！", "").getResponseMap());
        }
        if (pageRangeEnd == null || "".equals(pageRangeEnd)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "终止页不能为空！", "").getResponseMap());
        }
        //判断起始页和终止页是否为数字
        if (!CommonUtil.isNumeric(pageRangeStart)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "起始页必须为数字！", "").getResponseMap());
        }
        if (!CommonUtil.isNumeric(pageRangeEnd)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "终止页必须为数字！", "").getResponseMap());
        }
        if (Integer.valueOf(pageRangeStart) > Integer.valueOf(pageRangeEnd)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "起始页不能大于终止页！", "").getResponseMap());
        }
        if (extName == null || "".equals(extName)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "文件扩展名不能为空！", "").getResponseMap());
        }

        System.out.println(fileName + ",开始文件预览，1126当前时间：" + DateUtils.getNow());

        //获取本机ip
        InetAddress addr = null;
        try {
            fileUrl = URLDecoder.decode(fileUrl,"utf-8");
            addr = InetAddress.getLocalHost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String addre = addr.toString();
        String address = addre.substring(addre.indexOf("/") + 1, addre.length());
        String ip = "59.151.64.103";
        String port = null;
        if ("172.31.14.67".equals(address)) {
            port = ":38080";
        }
        if ("172.31.14.68".equals(address)) {
            port = ":48080";
        }
        if ("172.31.14.69".equals(address)) {
            port = ":58080";
        }

        //生成新文件名称
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = formatter.format(new Date());
        String newFileName = date + extName;

        System.out.println("文件链接：" + fileUrl);
        DownLoadFile httpMulitThreadDownload = new DownLoadFile(1, fileUrl, newFileName, "D:\\nginx\\html\\");
        String downs = httpMulitThreadDownload.MulitThreadDownload();
        if ("error".equals(downs)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, "下载文件失败！", "").getResponseMap());
        }

        //转换url文件为pdf格式
        switch (extName) {
            case ".jpg":
            case ".png":
            case ".jpeg":
            case ".gif": {
                String res = OfficeToPDFUtils.pic2Pdf("D:\\nginx\\html\\" + newFileName, "D:\\nginx\\html\\" + newFileName + ".pdf");
                if ("error".equals(res)) {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMap(Constant.UNKNOWN_ERROR, "转换PDF文件失败！", "").getResponseMap());
                }
                break;
            }
            case ".doc":
            case ".docx": {
                OfficeToPDFUtils.doc2Pdf("D:\\nginx\\html\\" + newFileName, "D:\\nginx\\html\\" + newFileName + ".pdf");
                break;
            }
            case ".ppt":
            case ".pptx": {
                OfficeToPDFUtils.ppt2Pdf("D:\\nginx\\html\\" + newFileName, "D:\\nginx\\html\\" + newFileName + ".pdf");
                break;
            }
            case ".xls":
            case ".xlsx": {
                OfficeToPDFUtils.excel2Pdf("D:\\nginx\\html\\" + newFileName, "D:\\nginx\\html\\" + newFileName + ".pdf");
                break;
            }

            case ".txt":
                String res1 = OfficeToPDFUtils.text2pdf("D:\\nginx\\html\\" + newFileName,
                        "D:\\nginx\\html\\" + newFileName + ".pdf");
                if ("error".equals(res1)) {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMap(Constant.UNKNOWN_ERROR, "txt转换PDF文件失败！", "").getResponseMap());
                }
                break;

            case ".pdf":

                String defPage = "0";
                if (!defPage.equals(pageRangeStart) && !defPage.equals(pageRangeEnd)) {
                    int pageStart = Integer.valueOf(pageRangeStart);
                    int pageEnd = Integer.valueOf(pageRangeEnd);
                    String res2 = PageRangeUtils.splitPdfFile("D:\\nginx\\html\\" + newFileName, "D:\\nginx\\html\\" + newFileName + ".pdf", pageStart, pageEnd);
                    if ("error".equals(res2)) {
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseMap(Constant.UNKNOWN_ERROR, "截取指定页数失败！", "").getResponseMap());
                    }
                    System.out.println(fileName + ",文件预览成功，1128当前时间：" + DateUtils.getNow());
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseMap(Constant.SUCCESS_STATE, "success!", "http://" + ip + port + "/pdf-viewer/web/viewer.html?url=http://" + ip + port + "/" + newFileName + ".pdf").getResponseMap());
                }
                System.out.println(fileName + ",文件预览成功，1127当前时间：" + DateUtils.getNow());
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.SUCCESS_STATE, "success!", "http://" + ip + port + "/pdf-viewer/web/viewer.html?url=http://" + ip + port + "/" + newFileName).getResponseMap());
            default:
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.PARAMETE_ERROR, "请打印规定格式文件！", "").getResponseMap());
        }

        //截取指定页数范围，默认值为0
        String defPage = "0";
        if (!defPage.equals(pageRangeStart) && !defPage.equals(pageRangeEnd)) {
            int pageStart = Integer.valueOf(pageRangeStart);
            int pageEnd = Integer.valueOf(pageRangeEnd);
            String res = PageRangeUtils.splitPdfFile("D:\\nginx\\html\\" + newFileName + ".pdf", "D:\\nginx\\html\\" + newFileName + "1.pdf", pageStart, pageEnd);
            if ("error".equals(res)) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.UNKNOWN_ERROR, "截取指定页数失败！", "").getResponseMap());
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.SUCCESS_STATE, "success!", "http://" + ip + port + "/pdf-viewer/web/viewer.html?url=http://" + ip + port + "/" + newFileName + "1.pdf").getResponseMap());
        }
        System.out.println(fileName + ",文件预览成功，1129当前时间：" + DateUtils.getNow());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMap(Constant.SUCCESS_STATE, "success!", "http://" + ip + port + "/pdf-viewer/web/viewer.html?url=http://" + ip + port + "/" + newFileName + ".pdf").getResponseMap());
    }

    /**
     * 取消打印文件接口
     *
     * @return
     */
    @RequestMapping("/cancelPrinting")
    public ResponseEntity<Map<String, Object>> cancelPrinting(HttpServletRequest request) {

        String taskId =request.getParameter("taskId");

        String ids = request.getParameter("ids");

        if (taskId == null || "".equals(taskId)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "taskId不能为空！", "").getResponseMap());
        }

        try {
            if (ids != null && !"".equals(ids)) {
                JSONArray json = JSONArray.fromObject(ids);
                for(int i= 0;i<json.size();i++){
                    String val = json.get(i).toString();
                    int j = busPrintFileService.deleteEntityByPK(val);
                    if (j != 1) {
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(new ResponseMap(Constant.UNKNOWN_ERROR, "error！", i).getResponseMap());
                    }
                }
            }

            BusMobprintStatus bus = new BusMobprintStatus();
            bus.setTaskId(taskId);
            bus.setPrintStatus("3");
            int i = busMobprintStatusService.updateEntityByPKSelective(bus);

            if (1 == i) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.SUCCESS_STATE, "success！", i).getResponseMap());
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.UNKNOWN_ERROR, "error！", i).getResponseMap());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
        }
    }


    /**
     * @param printInfoEntity
     * @param subNewFileName
     * @param fileType
     * @return
     * @throws Exception
     */
    private List<String> duplexPrint(PrintInfoEntity printInfoEntity, String subNewFileName, String fileType) throws Exception {

        List<String> list = new ArrayList<>();
        int copies = printInfoEntity.getCopies();
        String taskId = printInfoEntity.getTaskId();
        String newFileName = printInfoEntity.getNewFileName();

        if (subNewFileName != null && !"".equals(subNewFileName)) {
            newFileName = subNewFileName;
        }

        String printFileName = newFileName + ".pdf";

        List<BusUser> busUserList = busUserService.queryDataByWhere("WHERE name = '" + printInfoEntity.getUserName() + "'");
        Integer userId = 0;
        if (busUserList != null && busUserList.size() > 0) {
            userId = busUserList.get(0).getId();
        }

        List<BusPrintFile> busPrinrFileList = new ArrayList<>();

        boolean aa = subNewFileName == null || "".equals(subNewFileName);

        String socketRes;
        // 启动客户端连接
        SocketSend socketSend = new SocketSend();
        if (aa && "1".equals(fileType)) {
            // 传输文件
            socketRes = socketSend.sendFile("D:\\nginx\\html\\" + newFileName, "1");
            if ("error".equals(socketRes)) {
                return list;
            }
        } else {
            // 传输文件
            socketRes = socketSend.sendFile("D:\\nginx\\html\\" + printFileName , "1");
            if ("error".equals(socketRes)) {
                return list;
            }
        }

        //查询是否有取消打印
        BusMobprintStatus busMobprintStatus = busMobprintStatusService.getEntityByPK(taskId);
        String printStatus = busMobprintStatus.getPrintStatus();
        if ("1".equals(printStatus)) {
            //打印成功通过新文件名去表里查询数据
            if (aa && "1".equals(fileType)) {
                for (int k = 0; k < 1000; k++) {
                    System.out.println("查询双面打印数据1::" + newFileName);
                    Thread.sleep(2000);
                    busPrinrFileList = busPrintFileService.queryDataByWhere("WHERE name = '" + newFileName + "'");
                    if (busPrinrFileList != null && busPrinrFileList.size() > 0) {
                        break;
                    }
                }
            } else {
                for (int k = 0; k < 1000; k++) {
                    System.out.println("查询双面打印数据2::" + printFileName);
                    Thread.sleep(2000);
                    busPrinrFileList = busPrintFileService.queryDataByWhere("WHERE name = '" + printFileName + "'");
                    System.out.println(busPrinrFileList);
                    if (busPrinrFileList != null && busPrinrFileList.size() > 0) {
                        break;
                    }
                }
            }
            //查询是否有取消打印
            BusMobprintStatus busMobprintStatus1 = busMobprintStatusService.getEntityByPK(taskId);
            String printStatus1 = busMobprintStatus1.getPrintStatus();
            if ("1".equals(printStatus1)) {
                //保存表中新文件名称为传过来的文件名称
                BusPrintFile busFile = new BusPrintFile();
                busFile.setId(busPrinrFileList.get(0).getId());
                busFile.setName(printInfoEntity.getFileName());
                busPrintFileService.updateEntityByPKSelective(busFile);

                int printFileId = busPrinrFileList.get(0).getId();
                //通过printFileId去bus_print_file_map中查询
                for (int k = 0; k < 1000; k++) {
                    Thread.sleep(500);
                    List<BusUserPrintFileMap> printFileMapList = busUserPrintFileMapService.
                            queryDataByWhere("WHERE bus_print_file_id = '" + printFileId + "'");
                    if (printFileMapList != null && printFileMapList.size() > 0) {
                        break;
                    }
                }
                BusUserPrintFileMap fileMap = new BusUserPrintFileMap();
                fileMap.setBusUserId(userId);
                fileMap.setBusPrintFileId(printFileId);
                int i = busUserPrintFileMapService.updatePrintMapByFileId(fileMap);

                //将修改后的数据id放入list，返回
                list.add(String.valueOf(printFileId));

                if (copies > 1) {
                    for (int j = 1; j < copies; j++) {
                        BusPrintFile printFile = new BusPrintFile();
                        printFile.setName(printInfoEntity.getFileName());
                        printFile.setPath(busPrinrFileList.get(0).getPath());
                        printFile.setSizeNum(busPrinrFileList.get(0).getSizeNum());
                        printFile.setCopyNum(busPrinrFileList.get(0).getCopyNum());
                        printFile.setPageNum(busPrinrFileList.get(0).getPageNum());
                        printFile.setDuplexFlag(busPrinrFileList.get(0).getDuplexFlag());
                        printFile.setCollateFlag(busPrinrFileList.get(0).getCollateFlag());
                        printFile.setDriverName(busPrinrFileList.get(0).getDriverName());
                        printFile.setPrintServerName(busPrinrFileList.get(0).getPrintServerName());
                        printFile.setFormat(busPrinrFileList.get(0).getFormat());
                        printFile.setAnalysisResultSlz(busPrinrFileList.get(0).getAnalysisResultSlz());
                        printFile.setPrintDate(busPrinrFileList.get(0).getPrintDate());
                        printFile.setBusVirtualPortPrinterId(busPrinrFileList.get(0).getBusVirtualPortPrinterId());
                        printFile.setLangTypeVal(busPrinrFileList.get(0).getLangTypeVal());
                        printFile.setCreateDate(busPrinrFileList.get(0).getCreateDate());
                        printFile.setPreviewFilePath(busPrinrFileList.get(0).getPreviewFilePath());
                        printFile.setArchiveFilePath(busPrinrFileList.get(0).getArchiveFilePath());
                        printFile.setExpiredDate(busPrinrFileList.get(0).getExpiredDate());
                        printFile.setA3Num(busPrinrFileList.get(0).getA3Num());
                        printFile.setBwNum(busPrinrFileList.get(0).getBwNum());
                        printFile.setColorNum(busPrinrFileList.get(0).getColorNum());
                        printFile.setDuplexNum(busPrinrFileList.get(0).getDuplexNum());
                        printFile.setPreviewFlag(busPrinrFileList.get(0).getPreviewFlag());
                        printFile.setArchiveFlag(busPrinrFileList.get(0).getArchiveFlag());
                        busPrintFileMapper.insert(printFile);
                        BusUserPrintFileMap busUserPrintFileMap = new BusUserPrintFileMap();
                        busUserPrintFileMap.setBusUserId(userId);
                        busUserPrintFileMap.setBusPrintFileId(printFile.getId());
                        busUserPrintFileMap.setUserPrintFileMapTypeVal(0);
                        busUserPrintFileMap.setCreateDate(new Date());
                        busUserPrintFileMapMapper.insert(busUserPrintFileMap);

                        //将修改后的数据id放入list，返回
                        list.add(String.valueOf(printFile.getId()));
                    }
                }
                if (1 == i) {
                    BusMobprintStatus bus = new BusMobprintStatus();
                    bus.setTaskId(taskId);
                    bus.setPrintStatus("2");
                    busMobprintStatusService.updateEntityByPKSelective(bus);
                    return list;
                } else {
                    BusMobprintStatus bus = new BusMobprintStatus();
                    bus.setTaskId(taskId);
                    bus.setPrintStatus("3");
                    busMobprintStatusService.updateEntityByPKSelective(bus);
                    return list;
                }
            }
            return list;
        }
        return list;
    }
}
