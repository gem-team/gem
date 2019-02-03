/**
 * @Title:业务实现
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
package cn.gemframe.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import cn.gemframe.business.domain.GemPermissions;
import cn.gemframe.business.vo.GemPermissionsVo;
import cn.gemframe.config.exception.GemFrameException;
import cn.gemframe.config.exception.status.GemFrameErrorStatus;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gemframe.business.dao.GemPermissionsMapper;
import cn.gemframe.business.service.GemPermissionsService;
import cn.gemframe.business.service.GemRolePermissionsService;
import cn.gemframe.business.service.GemRoleService;
import tk.mybatis.mapper.entity.Example;


/**
 * @Title:业务实现
 * @Description:参数管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class GemPermissionsServiceImpl implements GemPermissionsService {

	@Autowired
	private GemPermissionsMapper permissionsMapper;
	@Autowired
	private GemRolePermissionsService rolePermissionsService;
	@Autowired
	private GemRoleService roleService;

	/**
	 * @Description:添加权限
	 * @param permissionsVo 权限接受参数实体
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer savePermission(GemPermissionsVo permissionsVo) {
		permissionsVo.setCreateDate(new Date());
		permissionsVo.setUpdateDate(new Date());
		GemPermissions permissions = GemFrameJsonUtils.classToClass(permissionsVo, GemPermissions.class);
		permissions.setId(GemFrameIdUtlis.Id());
		return permissionsMapper.insert(permissions);
	}

	/**
	 * @Description:删除权限
	 * @param id 权限主键集合
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer deletePermissionById(Long[] id) {
		if(id!=null && id.length>0) {
			for (Long pid : id) {
				rolePermissionsService.deleteByPermissId(pid);
				permissionsMapper.deleteByPrimaryKey(pid);
			}
		}else {
			throw new GemFrameException(GemFrameErrorStatus.PARAMETER_ERROR);
		}
		return null;
	}

	/**
	 * @Description:修改权限
	 * @param permissionsVo 权限接受参数实体
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer updatePermission(GemPermissionsVo permissionsVo) {
		permissionsVo.setUpdateDate(new Date());
		GemPermissions permissions = GemFrameJsonUtils.classToClass(permissionsVo, GemPermissions.class);
		return permissionsMapper.updateByPrimaryKeySelective(permissions);
	}

	/**
	 * @Description:查看权限详情
	 * @param id 权限主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public GemPermissions findPermissionById(Long id) {
		return permissionsMapper.selectByPrimaryKey(id);
	}

	/**
	 * @Description: 根据角色获取权限
	 * @param roleId 角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissions> findPermissionByRoleId(Long roleId) {
		List<GemPermissions> permissionTree = new ArrayList<GemPermissions>();
		if(roleId != null){
			Map<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("roleId", roleId);
			List<GemPermissions> permissions = permissionsMapper.findPermissionByRole(hashMap);
			//list to tree
			permissionTree = permissionListToTree(permissions);
		}
		return permissionTree;
	}
	/**
	 * @Description: 根据角色获取权限（包含未选中）
	 * @param roleId 角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissions> findPermissionSelectByRoleId(Long roleId) {
		List<GemPermissions> permissionTree = new ArrayList<GemPermissions>();
		if(roleId != null){
			//获取全部的菜单数据
			List<GemPermissions> permissionAll = findPermissionMenu(0);
			Map<Long,GemPermissions> permissionsMap = new HashMap<Long,GemPermissions>();
			for (int i = 0; i < permissionAll.size() ; i++) {
				permissionsMap.put(permissionAll.get(i).getId(),permissionAll.get(i));
			}
			//获取角色对应的权限
			Map<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("roleId", roleId);
			List<GemPermissions> permissionRole = permissionsMapper.findPermissionByRole(hashMap);
			//将选中的权限，赋值到全部菜单数据中
			for (int i = 0 ; i < permissionAll.size() ; i++){
				for (int j = 0 ; j < permissionRole.size() ; j++){
					if(permissionAll.get(i).getId().equals(permissionRole.get(j).getId())){
						permissionAll.get(i).setSelected(true);
						continue;
					}
				}
			}
			//list to tree
			permissionTree = permissionListToTree(permissionAll);
		}
		return permissionTree;
	}
	/**
	 * @Description: 根据角色和菜单获取权限-按钮（包含未选中）
	 * @param roleId 角色主键
	 * @param permissionId 权限（菜单）主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissions> findPermissionSelectByRoleIdAndPermissionId(Long roleId,Long permissionId) {
		List<GemPermissions> permissionButtonListByPermissionId = new ArrayList<GemPermissions>();
		if(roleId != null){
			//根据菜单获取对应的按钮
			Example example = new Example(GemPermissions.class);
			Example.Criteria createCriteria = example.createCriteria();
			createCriteria.andEqualTo("menusType",1);
			createCriteria.andEqualTo("parentId",permissionId);
			permissionButtonListByPermissionId = permissionsMapper.selectByExample(example);
			//根据菜单和角色获取对应的按钮
			Map<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("roleId", roleId);
			hashMap.put("permissionId", permissionId);
			List<GemPermissions> permissionButtonListByRoleIdAndPermissionId = permissionsMapper.findPermissionButtonByRoleIdAndPermissionId(hashMap);
			for (int i = 0 ; i < permissionButtonListByPermissionId.size() ; i++){
				for (int j = 0 ; j < permissionButtonListByRoleIdAndPermissionId.size() ; j++){
					if(permissionButtonListByPermissionId.get(i).getId().equals(permissionButtonListByRoleIdAndPermissionId.get(j).getId())){
						permissionButtonListByPermissionId.get(i).setSelected(true);
						continue;
					}
				}
			}
		}else{
			return null;
		}
		return permissionButtonListByPermissionId;
	}
	/**
	 * @Description: 根据角色和菜单获取权限-按钮（仅仅包含选中的）
	 * @param roleId 角色主键
	 * @param permissionId 权限（菜单）主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissions> findPermissionOnlySelectByRoleIdAndPermissionId(Long roleId,Long permissionId) {
		List<GemPermissions> permissionNumListByRoleIdAndPermissionId = new ArrayList<GemPermissions>();
		if(roleId != null){
			//根据菜单和角色获取对应的按钮
			Map<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("roleId", roleId);
			hashMap.put("permissionId", permissionId);
			permissionNumListByRoleIdAndPermissionId = permissionsMapper.findPermissionButtonByRoleIdAndPermissionId(hashMap);
		}else{
			return null;
		}
		return permissionNumListByRoleIdAndPermissionId;
	}

	/**
	 * @Description: 根据用户获取权限
	 * @param userId 用户主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissions> findPermissionByUserId(Long userId) {
		List<GemPermissions> permissionTree = new ArrayList<GemPermissions>();
		if(userId != null){
			Map<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("userId", userId);
			List<GemPermissions> permissions = permissionsMapper.findPermissionByUser(hashMap);
			//list to tree
			permissionTree = permissionListToTree(permissions);
		}
		return permissionTree;
	}

	/**
	 * @Description: 获取系统中的权限-菜单
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissions> findPermissionMenu() {
		//获取全部的菜单数据
		List<GemPermissions> permissionAll = findPermissionMenu(0);
		List<GemPermissions> permissionTree = permissionListToTree(permissionAll);
		return permissionTree;
	}

	/**
	 * @Description: 查看菜单下的按钮
	 * @param parentId 菜单主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissions> findPermissionButtonByParentId(Long parentId) {
		Example example = new Example(GemPermissions.class);
		Example.Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("parentId",parentId);
		createCriteria.andEqualTo("menusType",1);
		List<GemPermissions> selectByExample = permissionsMapper.selectByExample(example);
		return selectByExample;
	}

	/**
	 * @Description: 获取权限数据
	 * @param menusType 菜单0为菜单1按钮
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	private List<GemPermissions> findPermissionMenu(Integer menusType) {
		Example example = new Example(GemPermissions.class);
		Example.Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("menusType",menusType);
		List<GemPermissions> selectByExample = permissionsMapper.selectByExample(example);
		return selectByExample;
	}


	/**
	 * 将list格式是权限数据，转化成tree格式的权限数据。
	 * @param permissions
	 * @return
	 */
	private List<GemPermissions> permissionListToTree(List<GemPermissions> permissions){
		List<GemPermissions> permissionTree = new ArrayList<GemPermissions>();
		//list to tree
		for (GemPermissions t1 : permissions) {
			if(t1.getParentId() == -1L){
				permissionTree.add(t1);
			}
			for (GemPermissions t2 : permissions) {
				if(t2.getParentId().equals(t1.getId())){
					if(t1.getChildren() == null){
						List<GemPermissions> myChildrens = new ArrayList<GemPermissions>();
						myChildrens.add(t2);
						t1.setChildren(myChildrens);
					}else{
						t1.getChildren().add(t2);
					}
				}
			}
		}
		return permissionTree;
	}

}
