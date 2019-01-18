/**
 * @Title:控制器
 * @Description:权限处理
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

import cn.gemframe.business.domain.GemPermissionsAttribute;
import cn.gemframe.business.vo.GemPermissionsAttributeVo;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.gemframe.business.service.GemPermissionsAttributeService;

/**
 * @Title:控制器
 * @Description:权限处理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemPermissionsAttributeController {

	@Autowired
	private GemPermissionsAttributeService permissionsAttributeService;
	
	/**
	 * @Description:添加权限的属性值
	 * @param permissionsAttributeVo 权限的属性对象
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("attr/savePermissAttr")
	public ResponseEntity<ResultData> savePermissAttr(GemPermissionsAttributeVo permissionsAttributeVo){
		Integer flag=permissionsAttributeService.savePermissAttr(permissionsAttributeVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:删除权限的属性值
	 * @param id 权限的属性值主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("attr/deletePermissAttrById")
	public ResponseEntity<ResultData> deletePermissAttrById(Long[] id){
		Integer flag=permissionsAttributeService.deletePermissAttrById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:修改权限的属性值
	 * @param permissionsAttributeVo 权限的属性对象
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("attr/updatePermissAttr")
	public ResponseEntity<ResultData> updatePermissAttr(GemPermissionsAttributeVo permissionsAttributeVo){
		Integer flag=permissionsAttributeService.updatePermissAttr(permissionsAttributeVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:属性列表
	 * @param id 权限主键
	 * @author: Ryan  
	 * @date 2018年11月14日
	 */
	@PostMapping("attr/findPermissAttrList")
	public ResponseEntity<ResultData> findPermissAttrList(Long  id){
		List<GemPermissionsAttribute> flag=permissionsAttributeService.findPermissAttrList(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:根据主键查询权限的属性值
	 * @param id 权限的属性值主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@PostMapping("attr/findPermissAttrById")
	public ResponseEntity<ResultData> findPermissAttrById(Long id){
		GemPermissionsAttribute flag=permissionsAttributeService.findPermissAttrById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	

	/**
	 * @Description: 关联属性值
	 * @param permissid 权限主键
	 * @param roleId 角色主键
	 * @param attrs 属性主键集合
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	@PostMapping("attr/updateAssociatedAttr")
	public ResponseEntity<ResultData> updateAssociatedAttr(Long permissid,Long roleId,Long[] attrs){
		Integer flag=permissionsAttributeService.updateAssociatedAttr(permissid,roleId,attrs);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	
}
