package com.lanxum.jd.mobile.print.common.base.mapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Mapper基础类
 * @author jingwei
 *
 * @param <T>
 */
public interface BaseMapper<T> {
	/**
	 * 根据主键查询记录
	 * @param id
	 * @return
	 */
	public <ID extends Serializable> T selectByPrimaryKey(ID id);
	
	/**
	 * 插入一条记录
	 * @param record
	 * @return
	 */
	public Integer insert(T record);
	
	/**
	 * 插入一条记录（部分字段）
	 * @param record
	 * @return
	 */
	public Integer insertSelective(T record);
	
	/**
	 * 批量插入记录
	 * @param record
	 * @return
	 */
	public Integer insertBatch(List<T> list);
	
	/**
	 * 根据主键删除数据
	 * @param id
	 * @return
	 */
	public <ID extends Serializable> Integer deleteByPrimaryKey(ID id);
	
	/**
	 * 根据多个主键，批量删除数据
	 * @param map
	 * @return
	 */
	public Integer deleteBatchByPKs(Map<String, Object> map);
	
	/**
	 * 根据主键，修改一条记录
	 * @param record
	 * @return
	 */
	public Integer updateByPrimaryKey(T record);
	
	/**
	 * 根据主键，修改一条记录（部分字段）
	 * @param record
	 * @return
	 */
	public Integer updateByPrimaryKeySelective(T record);
	
	/**
	 * 查询全部结果
	 * @param model
	 * @return
	 */
	public List<T> queryAllData();
	
	/**
	 * 根据参数对象，查询结果
	 * @param model 参数对象
	 * @return
	 */
	public List<T> queryDataByModel(@Param("model") T model);

	/**
	 * 根据传入Model，查询结果列表
	 * @param model
	 * @return
	 */
	public List<T> queryDataByParam(@Param("model") T model);

	/**
	 * 根据传入where语句，查询结果列表
	 * @param where
	 * @return
	 */
	public List<T> queryDataByWhere(@Param("where") String where);

	/**
	 * 根据传入Model，查询数据记录数
	 * @param 参数对象
	 * @return
	 */
	public Integer queryCountByParam(@Param("model") T model);

	/**
	 * 根据传入where语句，查询数据记录数
	 * @param 参数对象
	 * @return
	 */
	public Integer queryCountByWhere(@Param("where") String where);
}
