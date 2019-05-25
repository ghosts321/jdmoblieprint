/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.busprintfile.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * BusPrintFile 实体类
 * @author horizoner
 *
 */
public class BusPrintFile {
    
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * name
	 */
	private String name;
	
	/**
	 * path
	 */
	private String path;
	
	/**
	 * sizeNum
	 */
	private Integer sizeNum;
	
	/**
	 * copyNum
	 */
	private Integer copyNum;
	
	/**
	 * pageNum
	 */
	private Integer pageNum;
	
	/**
	 * duplexFlag
	 */
	private Integer duplexFlag;
	
	/**
	 * collateFlag
	 */
	private Integer collateFlag;
	
	/**
	 * driverName
	 */
	private String driverName;
	
	/**
	 * printServerName
	 */
	private String printServerName;
	
	/**
	 * format
	 */
	private String format;
	
	/**
	 * analysisResultSlz
	 */
	private String analysisResultSlz;
	
	/**
	 * printDate
	 */
	private Date printDate;
	
	/**
	 * busVirtualPortPrinterId
	 */
	private Integer busVirtualPortPrinterId;
	
	/**
	 * langTypeVal
	 */
	private Integer langTypeVal;
	
	/**
	 * createDate
	 */
	private Date createDate;
	
	/**
	 * previewFilePath
	 */
	private String previewFilePath;
	
	/**
	 * archiveFilePath
	 */
	private String archiveFilePath;
	
	/**
	 * expiredDate
	 */
	private Date expiredDate;
	
	/**
	 * a3Num
	 */
	private Integer a3Num;
	
	/**
	 * bwNum
	 */
	private Integer bwNum;
	
	/**
	 * colorNum
	 */
	private Integer colorNum;
	
	/**
	 * duplexNum
	 */
	private Integer duplexNum;
	
	/**
	 * previewFlag
	 */
	private Integer previewFlag;
	
	/**
	 * archiveFlag
	 */
	private Integer archiveFlag;
	

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
	 * @return the name
	 */
	public String getName() {
		return this.name == null ? this.name : this.name.trim();
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the path
	 */
	public String getPath() {
		return this.path == null ? this.path : this.path.trim();
	}
	
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * @return the sizeNum
	 */
	public Integer getSizeNum() {
		return this.sizeNum;
	}

	/**
	 * @param sizeNum the sizeNum to set
	 */
	public void setSizeNum(Integer sizeNum) {
		this.sizeNum = sizeNum;
	}
	
	/**
	 * @return the copyNum
	 */
	public Integer getCopyNum() {
		return this.copyNum;
	}

	/**
	 * @param copyNum the copyNum to set
	 */
	public void setCopyNum(Integer copyNum) {
		this.copyNum = copyNum;
	}
	
	/**
	 * @return the pageNum
	 */
	public Integer getPageNum() {
		return this.pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	/**
	 * @return the duplexFlag
	 */
	public Integer getDuplexFlag() {
		return this.duplexFlag;
	}

	/**
	 * @param duplexFlag the duplexFlag to set
	 */
	public void setDuplexFlag(Integer duplexFlag) {
		this.duplexFlag = duplexFlag;
	}
	
	/**
	 * @return the collateFlag
	 */
	public Integer getCollateFlag() {
		return this.collateFlag;
	}

	/**
	 * @param collateFlag the collateFlag to set
	 */
	public void setCollateFlag(Integer collateFlag) {
		this.collateFlag = collateFlag;
	}
	
	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return this.driverName == null ? this.driverName : this.driverName.trim();
	}
	
	/**
	 * @param driverName the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	/**
	 * @return the printServerName
	 */
	public String getPrintServerName() {
		return this.printServerName == null ? this.printServerName : this.printServerName.trim();
	}
	
	/**
	 * @param printServerName the printServerName to set
	 */
	public void setPrintServerName(String printServerName) {
		this.printServerName = printServerName;
	}
	
	/**
	 * @return the format
	 */
	public String getFormat() {
		return this.format == null ? this.format : this.format.trim();
	}
	
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
	/**
	 * @return the analysisResultSlz
	 */
	public String getAnalysisResultSlz() {
		return this.analysisResultSlz == null ? this.analysisResultSlz : this.analysisResultSlz.trim();
	}
	
	/**
	 * @param analysisResultSlz the analysisResultSlz to set
	 */
	public void setAnalysisResultSlz(String analysisResultSlz) {
		this.analysisResultSlz = analysisResultSlz;
	}
	
	/**
	 * @return the printDate
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPrintDate() {
		return this.printDate;
	}

	/**
	 * @param printDate the printDate to set
	 */
	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	
	/**
	 * @return the busVirtualPortPrinterId
	 */
	public Integer getBusVirtualPortPrinterId() {
		return this.busVirtualPortPrinterId;
	}

	/**
	 * @param busVirtualPortPrinterId the busVirtualPortPrinterId to set
	 */
	public void setBusVirtualPortPrinterId(Integer busVirtualPortPrinterId) {
		this.busVirtualPortPrinterId = busVirtualPortPrinterId;
	}
	
	/**
	 * @return the langTypeVal
	 */
	public Integer getLangTypeVal() {
		return this.langTypeVal;
	}

	/**
	 * @param langTypeVal the langTypeVal to set
	 */
	public void setLangTypeVal(Integer langTypeVal) {
		this.langTypeVal = langTypeVal;
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
	
	/**
	 * @return the previewFilePath
	 */
	public String getPreviewFilePath() {
		return this.previewFilePath == null ? this.previewFilePath : this.previewFilePath.trim();
	}
	
	/**
	 * @param previewFilePath the previewFilePath to set
	 */
	public void setPreviewFilePath(String previewFilePath) {
		this.previewFilePath = previewFilePath;
	}
	
	/**
	 * @return the archiveFilePath
	 */
	public String getArchiveFilePath() {
		return this.archiveFilePath == null ? this.archiveFilePath : this.archiveFilePath.trim();
	}
	
	/**
	 * @param archiveFilePath the archiveFilePath to set
	 */
	public void setArchiveFilePath(String archiveFilePath) {
		this.archiveFilePath = archiveFilePath;
	}
	
	/**
	 * @return the expiredDate
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpiredDate() {
		return this.expiredDate;
	}

	/**
	 * @param expiredDate the expiredDate to set
	 */
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	/**
	 * @return the a3Num
	 */
	public Integer getA3Num() {
		return this.a3Num;
	}

	/**
	 * @param a3Num the a3Num to set
	 */
	public void setA3Num(Integer a3Num) {
		this.a3Num = a3Num;
	}
	
	/**
	 * @return the bwNum
	 */
	public Integer getBwNum() {
		return this.bwNum;
	}

	/**
	 * @param bwNum the bwNum to set
	 */
	public void setBwNum(Integer bwNum) {
		this.bwNum = bwNum;
	}
	
	/**
	 * @return the colorNum
	 */
	public Integer getColorNum() {
		return this.colorNum;
	}

	/**
	 * @param colorNum the colorNum to set
	 */
	public void setColorNum(Integer colorNum) {
		this.colorNum = colorNum;
	}
	
	/**
	 * @return the duplexNum
	 */
	public Integer getDuplexNum() {
		return this.duplexNum;
	}

	/**
	 * @param duplexNum the duplexNum to set
	 */
	public void setDuplexNum(Integer duplexNum) {
		this.duplexNum = duplexNum;
	}
	
	/**
	 * @return the previewFlag
	 */
	public Integer getPreviewFlag() {
		return this.previewFlag;
	}

	/**
	 * @param previewFlag the previewFlag to set
	 */
	public void setPreviewFlag(Integer previewFlag) {
		this.previewFlag = previewFlag;
	}
	
	/**
	 * @return the archiveFlag
	 */
	public Integer getArchiveFlag() {
		return this.archiveFlag;
	}

	/**
	 * @param archiveFlag the archiveFlag to set
	 */
	public void setArchiveFlag(Integer archiveFlag) {
		this.archiveFlag = archiveFlag;
	}
	
}

