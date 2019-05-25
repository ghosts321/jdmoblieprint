/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.bususerprintfilemap.entity;


import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * BusUserPrintFileMap 实体类
 * @author horizoner
 *
 */
public class BusUserPrintFileMap {
    
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * busUserId
	 */
	private Integer busUserId;
	
	/**
	 * busPrintFileId
	 */
	private Integer busPrintFileId;
	
	/**
	 * userPrintFileMapTypeVal
	 */
	private Integer userPrintFileMapTypeVal;
	
	/**
	 * createDate
	 */
	private Date createDate;
	

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the busUserId
	 */
	public Integer getBusUserId() {
		return this.busUserId;
	}

	/**
	 * @param busUserId the busUserId to set
	 */
	public void setBusUserId(Integer busUserId) {
		this.busUserId = busUserId;
	}
	
	/**
	 * @return the busPrintFileId
	 */
	public Integer getBusPrintFileId() {
		return this.busPrintFileId;
	}

	/**
	 * @param busPrintFileId the busPrintFileId to set
	 */
	public void setBusPrintFileId(Integer busPrintFileId) {
		this.busPrintFileId = busPrintFileId;
	}
	
	/**
	 * @return the userPrintFileMapTypeVal
	 */
	public Integer getUserPrintFileMapTypeVal() {
		return this.userPrintFileMapTypeVal;
	}

	/**
	 * @param userPrintFileMapTypeVal the userPrintFileMapTypeVal to set
	 */
	public void setUserPrintFileMapTypeVal(Integer userPrintFileMapTypeVal) {
		this.userPrintFileMapTypeVal = userPrintFileMapTypeVal;
	}
	
	/**
	 * @return the createDate
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}

