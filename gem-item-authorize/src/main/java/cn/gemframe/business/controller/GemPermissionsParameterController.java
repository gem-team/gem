/**
 * @Title:控制器
 * @Description:参数管理
 * Copyright 2018 GemFrame技术团队 http://www.gemframe.cn
 * Company: DianShiKongJian (Beijing) Technology Co., Ltd.
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 *
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package cn.gemframe.business.controller;

import java.util.List;

import cn.gemframe.business.domain.GemPermissionsParameter;
import cn.gemframe.business.vo.GemPermissionsParameterVo;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.gemframe.business.service.GemPermissionsParameterService;

/**
 * @Title:控制器
 * @Description:参数管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemPermissionsParameterController {

	@Autowired
	private GemPermissionsParameterService permissionsParameterService;
	
	/**
	 * @Description:添加权限的参数值
	 * @param permissionsParameterVo 权限的参数对象
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("per/savePermissParam")
	public ResponseEntity<ResultData> savePermissParam(GemPermissionsParameterVo permissionsParameterVo){
		Integer flag=permissionsParameterService.savePermissParam(permissionsParameterVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:删除权限的参数
	 * @param id 权限的参数值主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("per/deletePermissParamById")
	public ResponseEntity<ResultData> deletePermissParamById(Long[] id){
		Integer flag=permissionsParameterService.deletePermissParamById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:修改权限的参数值
	 * @param permissionsParameterVo 权限的参数对象
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("per/updatePermissParam")
	public ResponseEntity<ResultData> updatePermissParam(GemPermissionsParameterVo permissionsParameterVo){
		Integer flag=permissionsParameterService.updatePermissParam(permissionsParameterVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:根据主键查询权限的参数值
	 * @param id 权限的参数值主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("per/findPermissParamById")
	public ResponseEntity<ResultData> findPermissParamById(Long id){
		GemPermissionsParameter flag=permissionsParameterService.findPermissParamById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:查询权限的参数值列表
	 * @param id 权限主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("per/findPermissParamList")
	public ResponseEntity<ResultData> findPermissParamList(Long id){
		List<GemPermissionsParameter> flag=permissionsParameterService.findPermissParamList(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
}
