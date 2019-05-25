/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.bususerprintfilemap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.lanxum.jd.mobile.print.common.base.controller.BaseController;
import com.lanxum.jd.mobile.print.common.base.page.PagedResult;
import com.lanxum.jd.mobile.print.common.constant.Constant;
import com.lanxum.jd.mobile.print.common.utils.DateUtils;
import com.lanxum.jd.mobile.print.common.utils.USUtil;
import com.lanxum.jd.mobile.print.common.web.ResponseMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.entity.BusUserPrintFileMap;
import com.lanxum.jd.mobile.print.modules.bususerprintfilemap.service.BusUserPrintFileMapService;

/**
 * BusUserPrintFileMap controller
 * @author horizoner
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/busUserPrintFileMap")
public class BusUserPrintFileMapController extends BaseController {
	
	@Resource
	private BusUserPrintFileMapService busUserPrintFileMapService;
	
	/**
	 * 根据参数，查询分页数据
	 * @param request
	 * @param busUserPrintFileMap
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getPageDataByParam")
	public ResponseEntity<Map<String, Object>> getPageDataByParam(HttpServletRequest request, BusUserPrintFileMap busUserPrintFileMap, String pageNumber, String pageSize) {
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();
		
		if (pageNumber == null || "".equals(pageNumber)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.PARAMETE_ERROR, "当前页码不能为空！", "").getResponseMap());
		}
		
		if (pageSize == null || "".equals(pageSize)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.PARAMETE_ERROR, "每页显示条数不能为空！", "").getResponseMap());
		}
		
		try {
			PagedResult<BusUserPrintFileMap> pageResult = busUserPrintFileMapService.queryPageDataByParam(busUserPrintFileMap,
					Integer.valueOf(pageNumber), Integer.valueOf(pageSize));
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.SUCCESS_STATE, "获取列表信息成功！", pageResult).getResponseMap());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
		}
	}
	
	/**
	 * 根据参数，查询数据
	 * @param request
	 * @param busUserPrintFileMap
	 * @return
	 */
	@RequestMapping("/getDataByParam")
	public ResponseEntity<Map<String, Object>> getDataByParam(HttpServletRequest request, BusUserPrintFileMap busUserPrintFileMap) {
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();
		
		try {
			List<BusUserPrintFileMap> busUserPrintFileMapList = busUserPrintFileMapService.queryDataByParam(busUserPrintFileMap);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.SUCCESS_STATE, "获取列表数据成功！", busUserPrintFileMapList).getResponseMap());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
		}
	}
	
	/**
	 * 根据主键，查询数据
	 * @param request
	 * @param id
	 * @param departmentCode
	 * @return
	 */
	@RequestMapping("/getEntityByPK")
	public ResponseEntity<Map<String, Object>> getEntityByPK(HttpServletRequest request) {
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();

//		Integer id = 14;

		try {
//			BusUserPrintFileMap busUserPrintFileMap = busUserPrintFileMapService.getEntityByPK(id);

            List<String> list = new ArrayList<>();
            list.add("12345");
            list.add("56788");
            list.add("565774");
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.SUCCESS_STATE, "获取数据成功！", list).getResponseMap());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
		}
	}
	
	/**
	 * 添加记录
	 * @param busUserPrintFileMap
	 * @return
	 */
	@RequestMapping("/saveBusUserPrintFileMap")
	public ResponseEntity<Map<String, Object>> saveBusUserPrintFileMap() {
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();

        BusUserPrintFileMap busUserPrintFileMap = new BusUserPrintFileMap();
        busUserPrintFileMap.setBusUserId(1);
        busUserPrintFileMap.setBusPrintFileId(1);
        busUserPrintFileMap.setUserPrintFileMapTypeVal(0);
        busUserPrintFileMap.setCreateDate(DateUtils.getNow());

		try {
			int i = busUserPrintFileMapService.addEntity(busUserPrintFileMap);
		
			if (1 == i) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseMap(Constant.SUCCESS_STATE, "添加数据成功！", i).getResponseMap());
			} else {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseMap(Constant.UNKNOWN_ERROR, "添加数据失败！", i).getResponseMap());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
		}
	}

	/**
	 * 修改记录
	 * @param busUserPrintFileMap
	 * @return
	 */
	@RequestMapping("/updateBusUserPrintFileMap")
	public ResponseEntity<Map<String, Object>> updateBusUserPrintFileMap(BusUserPrintFileMap busUserPrintFileMap, ObjectMapper mapper) {
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();
		
		try {
			int i =  busUserPrintFileMapService.updateEntityByPKSelective(busUserPrintFileMap);
			
			if (1 == i) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseMap(Constant.SUCCESS_STATE, "修改数据成功！", i).getResponseMap());
			} else {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseMap(Constant.UNKNOWN_ERROR, "修改数据失败！", i).getResponseMap());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
		}
	}
	
	/**
	 * 根据主键，删除数据
	 * @param request
	* @param id
	 * @param departmentCode
	 * @return
	 */
	@RequestMapping("/deleteBusUserPrintFileMapByPK")
	public ResponseEntity<Map<String, Object>> deleteBusUserPrintFileMapByPK(HttpServletRequest request, java.lang.Integer id) {
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();
		
		if (id == null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.PARAMETE_ERROR, "删除数据主键不能为空！", "").getResponseMap());
		}
		
		try {
			int i = busUserPrintFileMapService.deleteEntityByPK(id);
		
			if (1 == i) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseMap(Constant.SUCCESS_STATE, "删除数据成功！", i).getResponseMap());
			} else {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseMap(Constant.UNKNOWN_ERROR, "删除数据失败！", i).getResponseMap());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
		}
	}
	
	/**
	 * 批量删除记录
	 * @param mapper
	 * @return
	 */
	@RequestMapping("/deleteBusUserPrintFileMapsByPKs")
	public ResponseEntity<Map<String, Object>> deleteBusUserPrintFileMapsByPKs(HttpServletRequest request, int[] ids){
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();
		
		Map<String,Object> idsMap = new HashMap<String,Object>();
		//重新定义 mybatis 参数名
		idsMap.put("ids", ids);
		
		try {
			int i = busUserPrintFileMapService.deleteEntityByPKs(idsMap);
			
			if (i != 0) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseMap(Constant.SUCCESS_STATE, "批量删除数据成功！", i).getResponseMap());
			} else {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseMap(Constant.UNKNOWN_ERROR, "批量删除数据失败！", i).getResponseMap());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
		}
	}
	
}
