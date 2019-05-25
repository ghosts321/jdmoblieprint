/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.busmobprintstatus.service.impl;

import com.lanxum.jd.mobile.print.common.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanxum.jd.mobile.print.modules.busmobprintstatus.entity.BusMobprintStatus;
import com.lanxum.jd.mobile.print.modules.busmobprintstatus.mapper.BusMobprintStatusMapper;
import com.lanxum.jd.mobile.print.modules.busmobprintstatus.service.BusMobprintStatusService;

/**
 * BusMobprintStatus service接口实现类
 * @author horizoner
 *
 */
@Service
@Transactional
public class BusMobprintStatusServiceImpl extends BaseServiceImpl<BusMobprintStatusMapper, BusMobprintStatus> implements BusMobprintStatusService {
	
}
