package com.lanxum.jd.mobile.print.common.base.page;



import com.lanxum.jd.mobile.print.common.base.entity.BaseEntity;

import java.util.List;

/**
 * 功能概要：分页结果类
 * 
 * @author hjw
 * @since 2016-12-19
 */
public class PagedResult<T> extends BaseEntity {

	/* serialVersionUID */
	private static final long serialVersionUID = 1L;

	private List<T> dataList; // 数据

	private long pageNumber; // 当前页

	private long pageSize; // 每页条数

	private long total; // 总条数

	private long pages; // 总页面数目

	/**
	 * @return the dataList
	 */
	public List<T> getDataList() {
		return dataList;
	}

	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	/**
	 * @return the pageNumber
	 */
	public long getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the pageSize
	 */
	public long getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * @return the pages
	 */
	public long getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(long pages) {
		this.pages = pages;
	}
	
}
