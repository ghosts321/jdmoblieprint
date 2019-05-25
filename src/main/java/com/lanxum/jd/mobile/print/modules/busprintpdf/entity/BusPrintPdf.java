/**
 * Powered By [国家文物局综合行政管理平台]
 * The company：北京汇金科技有限责任公司
 * Web Site: http://www.nasoft.com.cn/
 * department：技术部
 * Since 2016 - 2019
 */

package com.lanxum.jd.mobile.print.modules.busprintpdf.entity;

import java.util.Date;


import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;


/**
 * BusPrintPdf 实体类
 * @author nasoft
 *
 */
public class BusPrintPdf {
    
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * importUrl
	 */
	private String importUrl;
	
	/**
	 * importType
	 */
	private String importType;
	
	/**
	 * exportUrl
	 */
	private String exportUrl;
	
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
	 * @return the importUrl
	 */
	public String getImportUrl() {
		return this.importUrl == null ? this.importUrl : importUrl.trim();
	}
	
	/**
	 * @param importUrl the importUrl to set
	 */
	public void setImportUrl(String importUrl) {
		this.importUrl = importUrl;
	}
	
	/**
	 * @return the importType
	 */
	public String getImportType() {
		return this.importType == null ? this.importType : importType.trim();
	}
	
	/**
	 * @param importType the importType to set
	 */
	public void setImportType(String importType) {
		this.importType = importType;
	}
	
	/**
	 * @return the exportUrl
	 */
	public String getExportUrl() {
		return this.exportUrl == null ? this.exportUrl : exportUrl.trim();
	}
	
	/**
	 * @param exportUrl the exportUrl to set
	 */
	public void setExportUrl(String exportUrl) {
		this.exportUrl = exportUrl;
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

