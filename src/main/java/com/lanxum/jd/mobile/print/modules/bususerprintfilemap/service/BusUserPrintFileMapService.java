/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.bususerprintfilemap.service;

import com.lanxum.jd.mobile.print.common.base.service.BaseService;
import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.mapper.BusUserPrintFileMapMapper;
import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.entity.BusUserPrintFileMap;

/**
 * BusUserPrintFileMap service接口
 * @author horizoner
 *
 */
public interface BusUserPrintFileMapService extends BaseService<BusUserPrintFileMapMapper, BusUserPrintFileMap> {
	
	Integer updatePrintMapByFileId(BusUserPrintFileMap fileMap);
}
