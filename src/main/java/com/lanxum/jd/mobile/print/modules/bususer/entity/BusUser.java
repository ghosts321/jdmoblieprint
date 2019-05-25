/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.bususer.entity;


import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;


/**
 * BusUser 实体类
 * @author horizoner
 *
 */
public class BusUser {
    
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * name
	 */
	private String name;
	
	/**
	 * displayName
	 */
	private String displayName;
	
	/**
	 * passwordHash
	 */
	private String passwordHash;
	
	/**
	 * emailAddress
	 */
	private String emailAddress;
	
	/**
	 * domainName
	 */
	private String domainName;
	
	/**
	 * domainAccount
	 */
	private String domainAccount;
	
	/**
	 * computerName
	 */
	private String computerName;
	
	/**
	 * createDate
	 */
	private Date createDate;
	
	/**
	 * busCostCenterId
	 */
	private Integer busCostCenterId;
	
	/**
	 * deletedFlag
	 */
	private Integer deletedFlag;
	
	/**
	 * enabledFlag
	 */
	private Integer enabledFlag;
	
	/**
	 * deleteDate
	 */
	private Date deleteDate;
	
	/**
	 * telno
	 */
	private String telno;
	
	/**
	 * workNo
	 */
	private String workNo;
	
	/**
	 * cardIsn
	 */
	private String cardIsn;
	
	/**
	 * introduction
	 */
	private String introduction;
	
	/**
	 * busCostControlRuleId
	 */
	private Integer busCostControlRuleId;
	
	/**
	 * busChargeContextId
	 */
	private Integer busChargeContextId;
	
	/**
	 * token
	 */
	
	private Timestamp token;
	
	/**
	 * domainValidationFlag
	 */
	private Integer domainValidationFlag;
	
	/**
	 * guid
	 */
	private String guid;
	
	/**
	 * parentGuid
	 */
	private String parentGuid;
	

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
	 * @return the displayName
	 */
	public String getDisplayName() {
		return this.displayName == null ? this.displayName : this.displayName.trim();
	}
	
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return this.passwordHash == null ? this.passwordHash : this.passwordHash.trim();
	}
	
	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return this.emailAddress == null ? this.emailAddress : this.emailAddress.trim();
	}
	
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return this.domainName == null ? this.domainName : this.domainName.trim();
	}
	
	/**
	 * @param domainName the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	/**
	 * @return the domainAccount
	 */
	public String getDomainAccount() {
		return this.domainAccount == null ? this.domainAccount : this.domainAccount.trim();
	}
	
	/**
	 * @param domainAccount the domainAccount to set
	 */
	public void setDomainAccount(String domainAccount) {
		this.domainAccount = domainAccount;
	}
	
	/**
	 * @return the computerName
	 */
	public String getComputerName() {
		return this.computerName == null ? this.computerName : this.computerName.trim();
	}
	
	/**
	 * @param computerName the computerName to set
	 */
	public void setComputerName(String computerName) {
		this.computerName = computerName;
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
	 * @return the busCostCenterId
	 */
	public Integer getBusCostCenterId() {
		return this.busCostCenterId;
	}

	/**
	 * @param busCostCenterId the busCostCenterId to set
	 */
	public void setBusCostCenterId(Integer busCostCenterId) {
		this.busCostCenterId = busCostCenterId;
	}
	
	/**
	 * @return the deletedFlag
	 */
	public Integer getDeletedFlag() {
		return this.deletedFlag;
	}

	/**
	 * @param deletedFlag the deletedFlag to set
	 */
	public void setDeletedFlag(Integer deletedFlag) {
		this.deletedFlag = deletedFlag;
	}
	
	/**
	 * @return the enabledFlag
	 */
	public Integer getEnabledFlag() {
		return this.enabledFlag;
	}

	/**
	 * @param enabledFlag the enabledFlag to set
	 */
	public void setEnabledFlag(Integer enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	/**
	 * @return the deleteDate
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDeleteDate() {
		return this.deleteDate;
	}

	/**
	 * @param deleteDate the deleteDate to set
	 */
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	
	/**
	 * @return the telno
	 */
	public String getTelno() {
		return this.telno == null ? this.telno : this.telno.trim();
	}
	
	/**
	 * @param telno the telno to set
	 */
	public void setTelno(String telno) {
		this.telno = telno;
	}
	
	/**
	 * @return the workNo
	 */
	public String getWorkNo() {
		return this.workNo == null ? this.workNo : this.workNo.trim();
	}
	
	/**
	 * @param workNo the workNo to set
	 */
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	
	/**
	 * @return the cardIsn
	 */
	public String getCardIsn() {
		return this.cardIsn == null ? this.cardIsn : this.cardIsn.trim();
	}
	
	/**
	 * @param cardIsn the cardIsn to set
	 */
	public void setCardIsn(String cardIsn) {
		this.cardIsn = cardIsn;
	}
	
	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return this.introduction == null ? this.introduction : this.introduction.trim();
	}
	
	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	/**
	 * @return the busCostControlRuleId
	 */
	public Integer getBusCostControlRuleId() {
		return this.busCostControlRuleId;
	}

	/**
	 * @param busCostControlRuleId the busCostControlRuleId to set
	 */
	public void setBusCostControlRuleId(Integer busCostControlRuleId) {
		this.busCostControlRuleId = busCostControlRuleId;
	}
	
	/**
	 * @return the busChargeContextId
	 */
	public Integer getBusChargeContextId() {
		return this.busChargeContextId;
	}

	/**
	 * @param busChargeContextId the busChargeContextId to set
	 */
	public void setBusChargeContextId(Integer busChargeContextId) {
		this.busChargeContextId = busChargeContextId;
	}
	
	/**
	 * @return the domainValidationFlag
	 */
	public Integer getDomainValidationFlag() {
		return this.domainValidationFlag;
	}

	/**
	 * @param domainValidationFlag the domainValidationFlag to set
	 */
	public void setDomainValidationFlag(Integer domainValidationFlag) {
		this.domainValidationFlag = domainValidationFlag;
	}
	
	/**
	 * @return the guid
	 */
	public String getGuid() {
		return this.guid == null ? this.guid : this.guid.trim();
	}
	
	/**
	 * @param guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	/**
	 * @return the parentGuid
	 */
	public String getParentGuid() {
		return this.parentGuid == null ? this.parentGuid : this.parentGuid.trim();
	}
	
	/**
	 * @param parentGuid the parentGuid to set
	 */
	public void setParentGuid(String parentGuid) {
		this.parentGuid = parentGuid;
	}

	public Timestamp getToken() {
		return token;
	}

	public void setToken(Timestamp token) {
		this.token = token;
	}
	
}

