package com.lanxum.jd.mobile.print.common.base.service;

import com.lanxum.jd.mobile.print.common.base.mapper.BaseMapper;
import com.lanxum.jd.mobile.print.common.base.page.PagedResult;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础Service接口，提供基本增删改查功能
 * @author jingwei
 *
 * @param <T1>	BaseMapper
 * @param <T2>	EntityType
 */
@SuppressWarnings("rawtypes")
public interface BaseService<T1 extends BaseMapper, T2> {
	/**
	 * 
	 * @param baseMapper
	 */
	public void setBaseMapper(T1 baseMapper);
	
	/**
	 * 插入对象
	 * @param entity
	 * @return
	 */
	public Integer addEntity(T2 entity);
	
	/**
	 * 插入对象（部分字段）
	 * @param entity
	 * @return
	 */
	public Integer addEntitySelective(T2 entity);
	
	/**
	 * 批量插入对象
	 * @param entity
	 * @return
	 */
	public Integer addEntityBatch(List<T2> list);
	
	/**
	 * 根据主键，更新对象
	 * @param entity
	 * @return
	 */
	public Integer updateEntityByPK(T2 entity);
	
	/**
	 * 根据主键，更新对象（部分字段）
	 * @param entity
	 * @return
	 */
	public Integer updateEntityByPKSelective(T2 entity);
	
	/**
	 * 根据主键，删除对象
	 * @param entity
	 */
	public <S extends Serializable> Integer deleteEntityByPK(S id);
	
	/**
	 * 批量删除对象
	 * @param entity
	 */
	public Integer deleteEntityByPKs(Map<String, Object> map);
	
	/**
	 * 根据ID，获取对象
	 * @param id
	 * @return
	 */
	public <S extends Serializable> T2 getEntityByPK(S id);
	
	/**
	 * 查询全部结果
	 * @param model 参数对象
	 * @return
	 */
	public List<T2> queryAllData();
	
	/**
	 * 根据参数对象，查询结果
	 * @param model 参数对象
	 * @return
	 */
	public List<T2> queryDataByModel(T2 model);
	
	/**
	 * 根据查询条件对象，查询结果
	 * @param model 参数对象
	 * @return
	 */
	public List<T2> queryDataByParam(T2 model);
	
	/**
	 * 根据Where查询条件，查询结果
	 * @param model 参数对象
	 * @return
	 */
	public List<T2> queryDataByWhere(String where);
	
	/**
	 * 根据查询条件对象及页码和每页条数，获取带翻页信息的结果
	 * @param model 参数对象
	 * @param pageNumber 当前页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagedResult<T2> queryPageDataByParam(T2 model, Integer pageNumber, Integer pageSize);
	
	/**
	 * 根据Where查询条件及页码和每页条数，获取带翻页信息的结果
	 * @param where Where语句
	 * @param pageNumber 当前页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagedResult<T2> queryPageDataByWhere(String where, Integer pageNumber, Integer pageSize);
}