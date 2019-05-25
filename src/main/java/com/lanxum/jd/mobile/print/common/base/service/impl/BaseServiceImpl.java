package com.lanxum.jd.mobile.print.common.base.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.lanxum.jd.mobile.print.common.base.mapper.BaseMapper;
import com.lanxum.jd.mobile.print.common.base.page.PageBeanUtil;
import com.lanxum.jd.mobile.print.common.base.page.PagedResult;
import com.lanxum.jd.mobile.print.common.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;


/**
 * 基础Service实现类，提供基本增删改查功能
 * @author jingwei
 *
 * @param <T1>
 * @param <T2>
 */
@SuppressWarnings("rawtypes")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public abstract class BaseServiceImpl<T1 extends BaseMapper, T2> implements BaseService<T1, T2> {
	
	protected T1 baseMapper;
	
	/**
	 * @param baseMapper
	 */
	@Autowired
	public void setBaseMapper(T1 baseMapper) {
		this.baseMapper = baseMapper;
	}
	
	/**
	 * 插入对象
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer addEntity(T2 entity) {
		Integer row = baseMapper.insert(entity);
		return row;
	}
	
	/**
	 * 插入对象（部分字段）
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer addEntitySelective(T2 entity) {
		Integer row = baseMapper.insertSelective(entity);
		return row;
	}
	
	/**
	 * 批量插入对象
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer addEntityBatch(List<T2> list) {
		Integer row = baseMapper.insertBatch(list);
		return row;
	}
	
	/**
	 * 根据主键，更新对象
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer updateEntityByPK(T2 entity) {
		Integer row = baseMapper.updateByPrimaryKey(entity);
		return row;
	}
	
	/**
	 * 根据主键，更新对象（部分字段）
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer updateEntityByPKSelective(T2 entity) {
		Integer row = baseMapper.updateByPrimaryKeySelective(entity);
		return row;
	}
	
	/**
	 * 根据主键，删除对象
	 * @param entity
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public <ID extends Serializable> Integer deleteEntityByPK(ID id) {
		Integer row = baseMapper.deleteByPrimaryKey(id);
		return row;
	}
	
	/**
	 * 批量删除对象
	 * @param entity
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteEntityByPKs(Map<String, Object> map) {
		Integer row = baseMapper.deleteBatchByPKs(map);
		return row;
	}
	
	/**
	 * 根据ID，获取对象
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <S extends Serializable> T2 getEntityByPK(S id) {
		return (T2) baseMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 查询全部结果
	 * @param model 参数对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T2> queryAllData() {
		return baseMapper.queryAllData();
	}
	
	/**
	 * 根据参数对象，查询结果
	 * @param model 参数对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T2> queryDataByModel(T2 model) {
		return baseMapper.queryDataByModel(model);
	}
	
	/**
	 * 根据查询条件对象，查询结果
	 * @param model 参数对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T2> queryDataByParam(T2 model) {
		return baseMapper.queryDataByParam(model);
	}
	
	/**
	 * 根据Where查询条件，查询结果
	 * @param model 参数对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T2> queryDataByWhere(String where) {
		return baseMapper.queryDataByWhere(where);
	}
	
	/**
	 * 根据查询条件对象及页码和每页条数，获取带翻页信息的结果
	 * @param model 参数对象
	 * @param pageNumber 当前页码
	 * @param pageSize 每页条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PagedResult<T2> queryPageDataByParam(T2 model, Integer pageNumber, Integer pageSize) {
//		pageNumber = pageNumber == null ? 1 : pageNumber;
//		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNumber, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
		
		return PageBeanUtil.toPagedResult(baseMapper.queryDataByParam(model));
	}
	
	/**
	 * 根据Where查询条件及页码和每页条数，获取带翻页信息的结果
	 * @param where Where语句
	 * @param pageNumber 当前页码
	 * @param pageSize 每页条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PagedResult<T2> queryPageDataByWhere(String where, Integer pageNumber, Integer pageSize) {
		pageNumber = pageNumber == null ? 1 : pageNumber;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNumber, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
		
		return PageBeanUtil.toPagedResult(baseMapper.queryDataByWhere(where));
	}
	
}
