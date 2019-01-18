/**
 * @Title:控制器
 * @Description:角色组管理
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

import cn.gemframe.business.domain.GemRoleGroup;
import cn.gemframe.business.vo.GemRoleGroupVo;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.gemframe.business.service.GemRoleGroupService;

/**
 * @Title:控制器
 * @Description:角色组管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemRoleGroupController {
	
	@Autowired
	private GemRoleGroupService roleGroupService;
	
	/**
	 * @Description:添加角色组
	 * @param roleGroupVo 接收参数的实体对象
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@PostMapping("roleGroup/saveRoleGroup")
	public ResponseEntity<ResultData> saveRoleGroup(GemRoleGroupVo roleGroupVo){
		Integer flag=roleGroupService.saveRoleGroup(roleGroupVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:删除角色组
	 * @param id 主键
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@PostMapping("roleGroup/deletRoleGroup")
	public ResponseEntity<ResultData> deletRoleGroup(Long id){
		Integer flag=roleGroupService.deletRoleGroup(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:修改角色组
	 * @param roleGroupVo 接收参数的实体对象
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@PostMapping("roleGroup/updateRoleGroup")
	public ResponseEntity<ResultData> updateRoleGroup(GemRoleGroupVo roleGroupVo){
		Integer flag=roleGroupService.updateRoleGroup(roleGroupVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:查询角色组详情
	 * @param id 主键
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@PostMapping("roleGroup/findRoleGroupById")
	public ResponseEntity<ResultData> findRoleGroupById(Long id){
		GemRoleGroup flag=roleGroupService.findRoleGroupById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:查询角色组下面的所有角色
	 * @param name  角色名称
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@PostMapping("roleGroup/findGroupRoleList")
	public ResponseEntity<ResultData> findGroupRoleList(String name){
		List<GemRoleGroup> flag=roleGroupService.findGroupRoleList(name);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
}
