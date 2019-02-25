/**
 * @Title:业务实现
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
package cn.gemframe.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gemframe.business.domain.GemRole;
import cn.gemframe.business.domain.GemRolePermissions;
import cn.gemframe.business.domain.GemUserRole;
import cn.gemframe.business.vo.GemRoleVo;
import cn.gemframe.business.vo.GemUserRoleVo;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gemframe.business.dao.GemRoleMapper;
import cn.gemframe.business.service.GemRolePermissionsService;
import cn.gemframe.business.service.GemRoleService;
import cn.gemframe.business.service.GemUserRoleService;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

/**
 * @Title:业务实现
 * @Description:角色管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class GemRoleServiceImpl implements GemRoleService {

	@Autowired
	private GemRoleMapper roleMapper;
	@Autowired
	private GemRolePermissionsService rolePermissionsService;
	@Autowired
	private GemUserRoleService userRoleService;

	/**
	 * @Description: 查询用户拥有的角色
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemRole> findRoleByUser(Long id) {
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("id", id);
		List<GemRole> list=roleMapper.findRoleByUser(hashMap);
		return list;
	}

	/**
	 * @Description: 获取角色主键集合
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月23日
	 */
	@Override
	public List<Long> findRoleIdsByUser(Long id) {
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("id", id);
		return roleMapper.findRoleIdsByUser(hashMap);
	}

	/**
	 * @Description:查询角色详情
	 * @param id 和角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public GemRole findRoleById(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	/**
	 * @Description: 修改角色
	 * @param roleVo 角色接收参数的实体
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer updateRole(GemRoleVo roleVo) {
		roleVo.setUpdateDate(new Date());
		GemRole role = GemFrameJsonUtils.classToClass(roleVo, GemRole.class);
		return roleMapper.updateByPrimaryKeySelective(role);
	}

	/**
	 * @Description: 根据主键删除角色
	 * @param id 角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer deleteRoleById(Long id) {
		rolePermissionsService.deleteByRoleId(id);
		userRoleService.deleteByRoleId(id);
		return roleMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @Description: 删除用户的角色
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer deleteRoleByUserId(Long id) {
		return userRoleService.deleteRoleByUserId(id);
	}

	/**
	 * @Description: 添加角色和权限的关联关系
	 * @param roleId 角色主键集合
	 * @param permission 权限主键集合
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer saveRoleAndPermission(Long[] roleId,Long[] permission) {
		if(roleId!=null && roleId.length>0 && permission!=null && permission.length>0) {
			for (Long rid : roleId) {
				rolePermissionsService.deleteByRoleId(rid);
			}
			for (Long rid : roleId) {
				for (Long pid : permission) {
					GemRolePermissions rolePermissions = new GemRolePermissions();
					rolePermissions.setId(GemFrameIdUtlis.Id());
					rolePermissions.setRoleId(rid);
					rolePermissions.setPermisId(pid);
					rolePermissionsService.saveRolePermiss(rolePermissions);
				}
			}
		}else if(roleId!=null && roleId.length>0) {
			for (Long rid : roleId) {
				rolePermissionsService.deleteByRoleId(rid);
			}
		}
		return null;
	}

	/**
	 * @Description:添加角色和用户的关联关系
	 * @param userRole 用户集合
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer saveUserAndRole(GemUserRoleVo userRole) {
		Long roleId = userRole.getRoleId();
		Long userId = userRole.getUserId();
		if(userId!=null) {
			userRoleService.deleteRoleByUserId(userId);
		}
		if(roleId!=null) {
			GemUserRole classToClass = GemFrameJsonUtils.classToClass(userRole, GemUserRole.class);
			return userRoleService.saveUserAndRole(classToClass);
		}
		return null;
	}

	/**
	 * @Description: 添加角色
	 * @param roleVo 角色接收参数的实体
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Long saveRole(GemRoleVo roleVo) {
		roleVo.setCreateDate(new Date());
		roleVo.setUpdateDate(new Date());
		GemRole role = GemFrameJsonUtils.classToClass(roleVo, GemRole.class);
		Long id = GemFrameIdUtlis.Id();
		role.setId(id);
		roleMapper.insert(role);
		return id;
	}

}
