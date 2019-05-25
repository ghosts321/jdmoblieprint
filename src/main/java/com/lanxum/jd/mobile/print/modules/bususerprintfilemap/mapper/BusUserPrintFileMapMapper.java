/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.bususerprintfilemap.mapper;

import com.lanxum.jd.mobile.print.common.base.mapper.BaseMapper;
import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.entity.BusUserPrintFileMap;

/**
 * BusUserPrintFileMapMapper
 * @author horizoner
 *
 */
public interface BusUserPrintFileMapMapper extends BaseMapper<BusUserPrintFileMap> {
	
	Integer updatePrintMapByFileId(BusUserPrintFileMap fileMap);
}