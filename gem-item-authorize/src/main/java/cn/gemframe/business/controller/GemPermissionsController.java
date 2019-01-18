/**
 * @Title:控制器
 * @Description:权限管理
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

import java.util.HashMap;
import java.util.List;

import cn.gemframe.business.domain.GemPermissions;
import cn.gemframe.business.vo.GemPermissionsVo;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.gemframe.business.service.GemPermissionsAttributeService;
import cn.gemframe.business.service.GemPermissionsParameterService;
import cn.gemframe.business.service.GemPermissionsService;

/**
 * @Title:控制器
 * @Description:权限管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemPermissionsController {

	@Autowired
	private GemPermissionsService permissionsService;
	@Autowired
	private GemPermissionsAttributeService permissionsAttributeService;
    @Autowired
    private GemPermissionsParameterService permissionsParameterService;
    
    
	/**
	 * @Description: 根据角色获取权限菜单
	 * @param id 角色主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
    @PostMapping("permiss/findPermissByRoleId")
	public ResponseEntity<ResultData> findPermissByRoleId(Long id) {
		List<GemPermissions> list = permissionsService.findPermissByRoleId(id);
		return ResponseEntity.ok(ResultData.SUCCESS(list));
	}

	/**
	 * @Description: 根据角色获取权限按钮
	 * @param id 角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("permiss/findPermissMunByRoleId")
	public ResponseEntity<ResultData> findPermissMunByRoleId(Long id) {
		List<Long> list = permissionsService.findPermissMunByRoleId(id);
		return ResponseEntity.ok(ResultData.SUCCESS(list));
	}

	/**
	 * @Description: 根据用户获取菜单
	 * @param id 用户主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("permiss/findPermissByUserId")
	public ResponseEntity<ResultData> findPermissByUserId(Long id) {
		List<GemPermissions> findPermissByUser = permissionsService.findPermissByUserId(id);
		return ResponseEntity.ok(ResultData.SUCCESS(findPermissByUser));
	}
	
	/**
	 * @Description: 根据用户获取所有权限（包含参数，属性和子权限）
	 * @param id 用户主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("permiss/findUserPermiss")
	public ResponseEntity<ResultData> findUserPermiss(Long id) {
		List<GemPermissions> findPermissByUser = permissionsService.findUserPermiss(id);
		return ResponseEntity.ok(ResultData.SUCCESS(findPermissByUser));
	}
	
	/**
	 * @Description:查看权限详情
	 * @param id 权限主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("permiss/findPermissById")
	public ResponseEntity<ResultData> findPermissById(Long id) {
		GemPermissions permissions = permissionsService.findPermissById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(permissions));
	}
	
	/**
	 * @Description:查看菜单下的子权限
	 * @param id 菜单主键
	 * @param roleId 角色主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("permiss/findPermissAffiliated")
	public ResponseEntity<ResultData> findPermissByParentId(Long id,Long roleId) {
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		//获取权限
		hashMap.put("permiss", permissionsService.findPermissByParentId(id,roleId));
		//获取属性
		hashMap.put("attr", permissionsAttributeService.findAttrByPermissId(id,roleId));
		//获取条件值
		hashMap.put("param", permissionsParameterService.findParamByPermissId(id,roleId));
		return ResponseEntity.ok(ResultData.SUCCESS(hashMap));
	}
	
	/**
	 * @Description:查看菜单下的按钮
	 * @param id 菜单主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("permiss/findPermissChilds")
	public ResponseEntity<ResultData> findPermissByParentId(Long id) {
		List<GemPermissions> findPermissByParentId = permissionsService.findPermissByParentId(id,null);
		return ResponseEntity.ok(ResultData.SUCCESS(findPermissByParentId));
	}
	
	/**
	 * @Description:修改权限
	 * @param permissionsVo 权限接受参数实体
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("permiss/updatePermiss")
	public ResponseEntity<ResultData> updatePermiss(GemPermissionsVo permissionsVo) {
		Integer permissions = permissionsService.updatePermiss(permissionsVo);
		return ResponseEntity.ok(ResultData.SUCCESS(permissions));
	}
	
	/**
	 * @Description:添加权限
	 * @param permissionsVo 权限接受参数实体
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("permiss/savePermiss")
	public ResponseEntity<ResultData> savePermiss(GemPermissionsVo permissionsVo) {
		Integer permissions = permissionsService.savePermiss(permissionsVo);
		return ResponseEntity.ok(ResultData.SUCCESS(permissions));
	}
	
	/**
	 * @Description:删除权限
	 * @param id 权限主键集合
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("permiss/deletePermissById")
	public ResponseEntity<ResultData> deletePermissById(Long[] id) {
		Integer permissions = permissionsService.deletePermissById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(permissions));
	}
	
	
	
}
