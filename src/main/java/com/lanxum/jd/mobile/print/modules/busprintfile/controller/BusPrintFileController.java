/**
 * Powered By [京东集团移动打印DOMAS]
 * The company：天孚科技（北京）有限公司
 * Web Site: http://www.horizoner.com.cn/
 * department：技术部
 * Since 2019 - 2019
 */

package com.lanxum.jd.mobile.print.modules.busprintfile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.lanxum.jd.mobile.print.common.base.controller.BaseController;
import com.lanxum.jd.mobile.print.common.base.page.PagedResult;
import com.lanxum.jd.mobile.print.common.constant.Constant;
import com.lanxum.jd.mobile.print.common.utils.USUtil;
import com.lanxum.jd.mobile.print.common.web.ResponseMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.lanxum.jd.mobile.print.modules.busprintfile.entity.BusPrintFile;
import com.lanxum.jd.mobile.print.modules.busprintfile.service.BusPrintFileService;

/**
 * BusPrintFile controller
 * @author horizoner
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/busPrintFile")
public class BusPrintFileController extends BaseController {
	
	@Resource
	private BusPrintFileService busPrintFileService;
	
	/**
	 * 根据参数，查询分页数据
	 * @param request
	 * @param busPrintFile
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getPageDataByParam")
	public ResponseEntity<Map<String, Object>> getPageDataByParam(HttpServletRequest request, BusPrintFile busPrintFile, String pageNumber, String pageSize) {
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
			PagedResult<BusPrintFile> pageResult = busPrintFileService.queryPageDataByParam(busPrintFile,
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
	 * @param busPrintFile
	 * @return
	 */
	@RequestMapping("/getDataByParam")
	public ResponseEntity<Map<String, Object>> getDataByParam(HttpServletRequest request, BusPrintFile busPrintFile) {
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();
		
		try {
			List<BusPrintFile> busPrintFileList = busPrintFileService.queryDataByParam(busPrintFile);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.SUCCESS_STATE, "获取列表数据成功！", busPrintFileList).getResponseMap());
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

		int id = 13;

		try {
			BusPrintFile busPrintFile = busPrintFileService.getEntityByPK(id);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.SUCCESS_STATE, "获取数据成功！", busPrintFile).getResponseMap());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.UNKNOWN_ERROR, e.getMessage(), "").getResponseMap());
		}
	}
	
	/**
	 * 添加记录
	 * @param busPrintFile
	 * @return
	 */
	@RequestMapping("/saveBusPrintFile")
	public ResponseEntity<Map<String, Object>> saveBusPrintFile(BusPrintFile busPrintFile) {
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();
		
		try {
			int i = busPrintFileService.addEntity(busPrintFile);
		
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
	 * @param busPrintFile
	 * @return
	 */
	@RequestMapping("/updateBusPrintFile")
	public ResponseEntity<Map<String, Object>> updateBusPrintFile(BusPrintFile busPrintFile, ObjectMapper mapper) {
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();
		
		try {
			int i =  busPrintFileService.updateEntityByPKSelective(busPrintFile);
			
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
	@RequestMapping("/deleteBusPrintFileByPK")
	public ResponseEntity<Map<String, Object>> deleteBusPrintFileByPK(HttpServletRequest request) {
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();
        Integer id = 13;
		if (id == null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMap(Constant.PARAMETE_ERROR, "删除数据主键不能为空！", "").getResponseMap());
		}
		
		try {
			int i = busPrintFileService.deleteEntityByPK(id);
		
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
	@RequestMapping("/deleteBusPrintFilesByPKs")
	public ResponseEntity<Map<String, Object>> deleteBusPrintFilesByPKs(HttpServletRequest request, int[] ids){
		/**
		 * 前端统一响应的US码
		 */
		String us = USUtil.getUS();
		
		Map<String,Object> idsMap = new HashMap<String,Object>();
		//重新定义 mybatis 参数名
		idsMap.put("ids", ids);
		
		try {
			int i = busPrintFileService.deleteEntityByPKs(idsMap);
			
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
