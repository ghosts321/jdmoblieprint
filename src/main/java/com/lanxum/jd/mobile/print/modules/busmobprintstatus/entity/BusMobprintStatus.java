/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.busmobprintstatus.entity;

import java.util.Date;


import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;

/**
 * BusMobprintStatus 实体类
 * @author horizoner
 *
 */
public class BusMobprintStatus {
    
	/**
	 * taskId
	 */
	private String taskId;
	
	/**
	 * printStatus
	 * 1正在打印2打印成功3取消打印打印失败
	 */
	private String printStatus;
	
	/**
	 * log
	 */
	private String log;
	
	/**
	 * createTime
	 */
	private Date createTime;
	

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return this.taskId == null ? this.taskId : this.taskId.trim();
	}
	
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * @return the printStatus
	 */
	public String getPrintStatus() {
		return this.printStatus == null ? this.printStatus : this.printStatus.trim();
	}
	
	/**
	 * @param printStatus the printStatus to set
	 */
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	
	/**
	 * @return the log
	 */
	public String getLog() {
		return this.log == null ? this.log : this.log.trim();
	}
	
	/**
	 * @param log the log to set
	 */
	public void setLog(String log) {
		this.log = log;
	}
	
	/**
	 * @return the createTime
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

