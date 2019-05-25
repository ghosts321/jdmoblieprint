/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.bususer.controller;

import java.io.*;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.lanxum.jd.mobile.print.common.base.controller.BaseController;
import com.lanxum.jd.mobile.print.common.base.page.PagedResult;
import com.lanxum.jd.mobile.print.common.constant.Constant;
import com.lanxum.jd.mobile.print.common.utils.*;
import com.lanxum.jd.mobile.print.common.web.ResponseMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.lanxum.jd.mobile.print.modules.bususer.entity.BusUser;
import com.lanxum.jd.mobile.print.modules.bususer.service.BusUserService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import static com.lanxum.jd.mobile.print.common.utils.DownLoadHttpUrlFiles.downLoadFromUrl;
import static com.lanxum.jd.mobile.print.common.utils.DownLoadHttpUrlFiles.downloadFile;

/**
 * BusUser controller
 *
 * @author horizoner
 */
@CrossOrigin
@RestController
@RequestMapping("/busUser")
public class BusUserController extends BaseController {

    @Resource
    private BusUserService busUserService;

    /**
     * 根据参数，查询分页数据
     *
     * @param request
     * @param busUser
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping("/getPageDataByParam")
    public ResponseEntity<Map<String, Object>> getPageDataByParam(HttpServletRequest request, BusUser busUser, String pageNumber, String pageSize) {
        /**
         * 前端统一响应的US码
         */
        String us = USUtil.getUS();

        if (pageNumber == null || "".equals(pageNumber)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "当前页码不能为空！", "").getResponseMap());
        }

        if (pageSize == null || "".equals(pageSize)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "每页显示条数不能为空！", "").getResponseMap());
        }

        try {
            PagedResult<BusUser> pageResult = busUserService.queryPageDataByParam(busUser,
                    Integer.valueOf(pageNumber), Integer.valueOf(pageSize));

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.SUCCESS_STATE, "获取列表信息成功！", pageResult).getResponseMap());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
        }
    }

    /**
     * 根据参数，查询数据
     *
     * @param request
     * @param busUser
     * @return
     */
    @RequestMapping("/getDataByParam")
    public ResponseEntity<Map<String, Object>> getDataByParam(HttpServletRequest request, BusUser busUser) {
        /**
         * 前端统一响应的US码
         */
        String us = USUtil.getUS();

        try {
            List<BusUser> busUserList = busUserService.queryDataByParam(busUser);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.SUCCESS_STATE, "获取列表数据成功！", busUserList).getResponseMap());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
        }
    }

    /**
     * 根据主键，查询数据
     *
     * @param request
     * @param id
     * @param departmentCode
     * @return
     */
    @RequestMapping("/getEntityByPK")
    public ResponseEntity<Map<String, Object>> getEntityByPK(HttpServletRequest request, java.lang.Integer id) {
        /**
         * 前端统一响应的US码
         */
        String us = USUtil.getUS();

        try {
            BusUser busUser = busUserService.getEntityByPK(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.SUCCESS_STATE, "获取数据成功！", busUser).getResponseMap());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
        }
    }

    /**
     * 添加记录
     *
     * @param busUser
     * @return
     */
    @RequestMapping("/saveBusUser")
    public ResponseEntity<Map<String, Object>> saveBusUser(BusUser busUser) {
        /**
         * 前端统一响应的US码
         */
        String us = USUtil.getUS();

        try {
            int i = busUserService.addEntity(busUser);

            if (1 == i) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.SUCCESS_STATE, "添加数据成功！", i).getResponseMap());
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.UNKNOWN_ERROR, "添加数据失败！", i).getResponseMap());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
        }
    }

    /**
     * 修改记录
     *
     * @param busUser
     * @return
     */
    @RequestMapping("/updateBusUser")
    public ResponseEntity<Map<String, Object>> updateBusUser(BusUser busUser, ObjectMapper mapper) {
        /**
         * 前端统一响应的US码
         */
        String us = USUtil.getUS();

        try {
            int i = busUserService.updateEntityByPKSelective(busUser);

            if (1 == i) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.SUCCESS_STATE, "修改数据成功！", i).getResponseMap());
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.UNKNOWN_ERROR, "修改数据失败！", i).getResponseMap());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
        }
    }

    /**
     * 根据主键，删除数据
     *
     * @param request
     * @param id
     * @param departmentCode
     * @return
     */
    @RequestMapping("/deleteBusUserByPK")
    public ResponseEntity<Map<String, Object>> deleteBusUserByPK(HttpServletRequest request, java.lang.Integer id) {
        /**
         * 前端统一响应的US码
         */
        String us = USUtil.getUS();

        if (id == null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.PARAMETE_ERROR, "删除数据主键不能为空！", "").getResponseMap());
        }

        try {
            int i = busUserService.deleteEntityByPK(id);

            if (1 == i) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.SUCCESS_STATE, "删除数据成功！", i).getResponseMap());
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.UNKNOWN_ERROR, "删除数据失败！", i).getResponseMap());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
        }
    }

    /**
     * 批量删除记录
     *
     * @param mapper
     * @return
     */
    @RequestMapping("/deleteBusUsersByPKs")
    public ResponseEntity<Map<String, Object>> deleteBusUsersByPKs(HttpServletRequest request, int[] ids) {
        /**
         * 前端统一响应的US码
         */
        String us = USUtil.getUS();

        Map<String, Object> idsMap = new HashMap<String, Object>();
        //重新定义 mybatis 参数名
        idsMap.put("ids", ids);

        try {
            int i = busUserService.deleteEntityByPKs(idsMap);

            if (i != 0) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.SUCCESS_STATE, "批量删除数据成功！", i).getResponseMap());
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseMap(Constant.UNKNOWN_ERROR, "批量删除数据失败！", i).getResponseMap());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
        }
    }

    @RequestMapping("/printFiles")
    public ResponseEntity<Map<String, Object>> printFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {

//	    String sSrc = "zH3FgfzKko1MflWE5VkQiDJw8YwUcHuVsIKC1LylQCM=";
//
//	    String user = AESUtil.Decrypt(sSrc);
//        FileInputStream in = null;
//        ServletOutputStream outputStream = null;
//        try {
//            in = new FileInputStream(new File("E:\\domas.pdf"));
//            outputStream = response.getOutputStream();
//            IOUtils.copyLarge(in, outputStream);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            IOUtils.closeQuietly(in);
//            IOUtils.closeQuietly(outputStream);
//        }

        boolean aa = downloadFile("https://developers.hp.com/system/files/BootloaderMenu-FailedDeviceRecoverySteps.pdf", "E:\\test1.pdf");
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMap(Constant.SUCCESS_STATE, "success！", "").getResponseMap());
    }

    @RequestMapping("/scanFiles")
    public ResponseEntity<Map<String, Object>> scanFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String savePath = "E:\\abc";

        //这里的代码是我为了把文件分类保存到不同的文件夹中

        File file = new File(savePath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        String fileName = null;
        try {
            Collection<Part> parts = request.getParts();
            if (parts.size() == 1) {
                Part part = request.getPart("file");
                String header = part.getHeader("content-disposition");
                fileName = getFileName(header);
                part.write(savePath + File.separatorChar + fileName);
            } else {
                for (Part part : parts) {
                    String header = part.getHeader("content-disposition");
                    if(header.contains("filename")) {
                        fileName = getFileName(header);
                        part.write(savePath + File.separatorChar + fileName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMap(Constant.UNKNOWN_ERROR, "", "").getResponseMap());
    }

    @RequestMapping("/updXml")
    public ResponseEntity<Map<String, Object>> updXml() throws Exception {



        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMap(Constant.UNKNOWN_ERROR, "", "").getResponseMap());
    }

    public static String getFileName(String header) {//这个方法通过header获取文件的名字
        String fileName = null;
        fileName = header.substring(header.indexOf("filename") + 2 + "filename".length(), header.length() - 1);
        return fileName;
    }
}

