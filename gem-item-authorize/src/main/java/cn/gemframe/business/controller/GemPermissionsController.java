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

import java.util.List;

import cn.gemframe.business.domain.GemPermissions;
import cn.gemframe.business.vo.GemPermissionsVo;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

	/**
	 * @Description:添加权限
	 * @param permissionsVo 权限接受参数实体
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("permission/savePermission")
	public ResponseEntity<ResultData> savePermission(GemPermissionsVo permissionsVo) {
		Integer returnCount = permissionsService.savePermission(permissionsVo);
		return ResponseEntity.ok(ResultData.SUCCESS(returnCount));
	}

	/**
	 * @Description:删除权限
	 * @param id 权限主键集合
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("permission/deletePermissionById")
	public ResponseEntity<ResultData> deletePermissionById(Long[] id) {
		Integer returnCount = permissionsService.deletePermissionById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(returnCount));
	}

	/**
	 * @Description:修改权限
	 * @param permissionsVo 权限接受参数实体
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("permission/updatePermission")
	public ResponseEntity<ResultData> updatePermiss(GemPermissionsVo permissionsVo) {
		Integer returnCount = permissionsService.updatePermission(permissionsVo);
		return ResponseEntity.ok(ResultData.SUCCESS(returnCount));
	}

	/**
	 * @Description:查看权限详情
	 * @param id 权限主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("permission/findPermissionById")
	public ResponseEntity<ResultData> findPermissionById(Long id) {
		GemPermissions permission = permissionsService.findPermissionById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(permission));
	}

	/**
	 * @Description: 根据角色获取权限
	 * @param id 角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
    @PostMapping("permission/findPermissionByRoleId")
	public ResponseEntity<ResultData> findPermissionByRoleId(Long id) {
		List<GemPermissions> permissionList = permissionsService.findPermissionByRoleId(id);
		return ResponseEntity.ok(ResultData.SUCCESS(permissionList));
	}

	/**
	 * @Description: 根据角色获取权限-菜单（包含未选中）
	 * @param roleId 角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("permission/findPermissionSelectByRoleId")
	public ResponseEntity<ResultData> findPermissionSelectByRoleId(Long roleId) {
		List<GemPermissions> permissionList = permissionsService.findPermissionSelectByRoleId(roleId);
		return ResponseEntity.ok(ResultData.SUCCESS(permissionList));
	}

	/**
	 * @Description: 根据角色和菜单获取权限-按钮（包含未选中）
	 * @param roleId 角色主键
	 * @param permissionId 权限（菜单）主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("permission/findPermissionSelectByRoleIdAndPermissionId")
	public ResponseEntity<ResultData> findPermissionSelectByRoleIdAndPermissionId(Long roleId,Long permissionId) {
		List<GemPermissions> permissionList = permissionsService.findPermissionSelectByRoleIdAndPermissionId(roleId,permissionId);
		return ResponseEntity.ok(ResultData.SUCCESS(permissionList));
	}

	/**
	 * @Description: 根据角色和菜单获取权限-按钮（仅仅包含选中的）
	 * @param roleId 角色主键
	 * @param permissionId 权限（菜单）主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("permission/findPermissionOnlySelectByRoleIdAndPermissionId")
	public ResponseEntity<ResultData> findPermissionOnlySelectByRoleIdAndPermissionId(Long roleId,Long permissionId) {
		List<GemPermissions> permissionList = permissionsService.findPermissionOnlySelectByRoleIdAndPermissionId(roleId,permissionId);
		return ResponseEntity.ok(ResultData.SUCCESS(permissionList));
	}


	/**
	 * @Description: 根据用户获取权限
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("permission/findPermissionByUserId")
	public ResponseEntity<ResultData> findPermissionByUserId(Long id) {
		List<GemPermissions> permissionList = permissionsService.findPermissionByUserId(id);
		return ResponseEntity.ok(ResultData.SUCCESS(permissionList));
	}

	/**
	 * @Description: 获取系统中的权限-菜单
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("permission/findPermissionMenu")
	public ResponseEntity<ResultData> findPermissionMenu() {
		List<GemPermissions> permissionList = permissionsService.findPermissionMenu();
		return ResponseEntity.ok(ResultData.SUCCESS(permissionList));
	}

	/**
	 * @Description:查看菜单下的按钮
	 * @param parentId 菜单主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("permission/findPermissionButtonByParentId")
	public ResponseEntity<ResultData> findPermissionByParentId(Long parentId) {
		List<GemPermissions> permissionList = permissionsService.findPermissionButtonByParentId(parentId);
		return ResponseEntity.ok(ResultData.SUCCESS(permissionList));
	}

}
