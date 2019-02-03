/**
 * @Title:控制器
 * @Description:角色管理
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

import cn.gemframe.business.domain.GemRole;
import cn.gemframe.business.vo.GemRoleVo;
import cn.gemframe.business.vo.GemUserRoleVo;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.gemframe.business.service.GemRoleService;

/**
 * @Title:控制器
 * @Description:角色管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemRoleController {

	@Autowired
	private GemRoleService roleService;

	/**
	 * @Description: 添加角色
	 * @param roleVo 角色接收参数的实体
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("role/saveRole")
	public ResponseEntity<ResultData> saveRole(GemRoleVo roleVo) {
		Integer list=roleService.saveRole(roleVo);
		return ResponseEntity.ok(ResultData.SUCCESS(list));
	}

	/**
	 * @Description: 根据主键删除角色
	 * @param id 角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("role/deleteRoleById")
	public ResponseEntity<ResultData> deleteRoleById(Long id) {
		Integer list=roleService.deleteRoleById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(list));
	}

	/**
	 * @Description: 修改角色
	 * @param roleVo 角色接收参数的实体
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("role/updateRole")
	public ResponseEntity<ResultData> updateRole(GemRoleVo roleVo) {
		Integer list=roleService.updateRole(roleVo);
		return ResponseEntity.ok(ResultData.SUCCESS(list));
	}


	/**
	 * @Description:查询角色详情
	 * @param id 和角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("role/findRoleById")
	public ResponseEntity<ResultData> findRoleById(Long id) {
		GemRole list=roleService.findRoleById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(list));
	}

	/**
	 * @Description:查询用户对应的角色列表
	 * @param userId  用户id
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("role/findRoleListByUserId")
	public ResponseEntity<ResultData> findRoleListByUserId(Long userId) {
		List<GemRole> list=roleService.findRoleByUser(userId);
		return ResponseEntity.ok(ResultData.SUCCESS(list));
	}

	/**
	 * @Description: 删除用户的角色
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("role/deleteRoleByUserId")
	public ResponseEntity<ResultData> deleteRoleByUserId(Long id) {
		Integer list=roleService.deleteRoleByUserId(id);
		return ResponseEntity.ok(ResultData.SUCCESS(list));
	}

	/**
	 * @Description: 添加角色和权限的关联关系
	 * @param roleId 角色主键集合
	 * @param permiss 权限主键集合
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("role/saveRoleAndPermiss")
	public ResponseEntity<ResultData> saveRoleAndPermiss(Long[] roleId,Long[] permiss) {
		Integer flag=roleService.saveRoleAndPermiss(roleId,permiss);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}


	/**
	 * @Description:添加角色和用户的关联关系
	 * @param userRole 角色和用户关联接收参数的实体对象
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("role/saveUserAndRole")
	public ResponseEntity<ResultData> saveUserAndRole(GemUserRoleVo userRole) {
		Integer list=roleService.saveUserAndRole(userRole);
		return ResponseEntity.ok(ResultData.SUCCESS(list));
	}
}
