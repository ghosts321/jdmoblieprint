/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.bususerprintfilemap.service.impl;

import com.lanxum.jd.mobile.print.common.base.service.impl.BaseServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.entity.BusUserPrintFileMap;
import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.mapper.BusUserPrintFileMapMapper;
import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.service.BusUserPrintFileMapService;

/**
 * BusUserPrintFileMap service接口实现类
 * @author horizoner
 *
 */
@Service
@Transactional
public class BusUserPrintFileMapServiceImpl extends BaseServiceImpl<BusUserPrintFileMapMapper, BusUserPrintFileMap> implements BusUserPrintFileMapService {

	@Resource
	BusUserPrintFileMapMapper busUserPrintFileMapMapper;
	/**
	 * 根据bus_print_File_id修改
	 */
	@Override
	public Integer updatePrintMapByFileId(BusUserPrintFileMap fileMap) {
		return busUserPrintFileMapMapper.updatePrintMapByFileId(fileMap);
	}
	
}
