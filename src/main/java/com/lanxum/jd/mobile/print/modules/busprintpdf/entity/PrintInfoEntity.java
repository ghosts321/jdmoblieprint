package com.lanxum.jd.mobile.print.modules.busprintpdf.entity;

/**
 * Created by JiangJunpeng on 2019/3/29.<br>
 *
 *     String taskId = request.getParameter("taskId");
 * 		String token = request.getParameter("token");
 * 		String fileUrl = request.getParameter("fileUrl");
 * 		String fileName = request.getParameter("fileName");
 * 		String extName = request.getParameter("extName");
 * 		String duplex = request.getParameter("duplex");
 * 		String copies = request.getParameter("copies");
 * 		String pageRangeStart = request.getParameter("pageRangeStart");
 * 		String pageRangeEnd = request.getParameter("pageRangeEnd");
 */
public class PrintInfoEntity {

    private String taskId;
    private String token;
    private String fileUrl;
    private String fileName;
    private String extName;
    private String dupLex;
    private Integer copies;
    private String pageRangeStart;
    private String pageRangeEnd;
    private String newFileName;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public String getDupLex() {
        return dupLex;
    }

    public void setDupLex(String dupLex) {
        this.dupLex = dupLex;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public String getPageRangeStart() {
        return pageRangeStart;
    }

    public void setPageRangeStart(String pageRangeStart) {
        this.pageRangeStart = pageRangeStart;
    }

    public String getPageRangeEnd() {
        return pageRangeEnd;
    }

    public void setPageRangeEnd(String pageRangeEnd) {
        this.pageRangeEnd = pageRangeEnd;
    }
}
