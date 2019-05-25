package com.lanxum.jd.mobile.print.common.base.page;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * 功能概要：数据转换类
 * 
 * @author hjw
 * @since  2016年12月29日 
 */
public class PageBeanUtil {
	
	/**
	 * 将结果列表转为分页结果
	 * @param datas
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> PagedResult<T> toPagedResult(List<T> datas) {
		PagedResult<T> result = new PagedResult<T>();
		if (datas instanceof Page) {
			Page page = (Page) datas;
			result.setPageNumber(page.getPageNum());
			result.setPageSize(page.getPageSize());
			result.setDataList(page.getResult());
			result.setTotal(page.getTotal());
			result.setPages(page.getPages());
		} else {
			result.setPageNumber(1);
			result.setPageSize(datas.size());
			result.setDataList(datas);
			result.setTotal(datas.size());
		}

		return result;
	}

}
