/**
 * @Title:业务实现
 * @Description:角色和权限的关联管理
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

import java.util.ArrayList;
import java.util.List;

import cn.gemframe.business.domain.GemRolePermissionsAttr;
import cn.gemframe.config.exception.GemFrameException;
import cn.gemframe.config.exception.status.GemFrameErrorStatus;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gemframe.business.dao.GemRolePermissionsAttrMapper;
import cn.gemframe.business.service.GemRolePermissionsAttrService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @Title:业务实现
 * @Description:角色和权限的关联管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class GemRolePermissionsAttrServiceImpl implements GemRolePermissionsAttrService {

	@Autowired
	private GemRolePermissionsAttrMapper rolePermissionsAttrMapper;
	/**
	 * @Description:获取角色在菜单的所有属性值的id
	 * @param id 菜单主键
	 * @param roleId 角色主键
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	@Override
	public List<Long> findAttrIds(Long id, Long roleId) {
		List<Long> arrayList = new ArrayList<Long>();
		Example example = new Example(GemRolePermissionsAttr.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("permisId", id);
		createCriteria.andEqualTo("roleId", roleId);
		List<GemRolePermissionsAttr> selectByExample = rolePermissionsAttrMapper.selectByExample(example);
		if(selectByExample!=null && selectByExample.size()>0) {
			for (GemRolePermissionsAttr rolePermissionsAttr : selectByExample) {
				Long attrId = rolePermissionsAttr.getAttrId();
				arrayList.add(attrId);
			}
		}
		return arrayList;
	}
	
	/**
	 * @Description:获取角色在菜单的所有属性值的id
	 * @param id 菜单主键
	 * @param roles 角色主键集合
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	@Override
	public List<Long> findAttrIds(Long id, List<Long> roles) {
		List<Long> arrayList = new ArrayList<Long>();
		Example example = new Example(GemRolePermissionsAttr.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("permisId", id);
		createCriteria.andIn("roleId", roles);
		List<GemRolePermissionsAttr> selectByExample = rolePermissionsAttrMapper.selectByExample(example);
		if(selectByExample!=null && selectByExample.size()>0) {
			for (GemRolePermissionsAttr rolePermissionsAttr : selectByExample) {
				Long attrId = rolePermissionsAttr.getAttrId();
				arrayList.add(attrId);
			}
		}
		return arrayList;
	}
	
	/**
	 * @Description: 关联属性值
	 * @param permissid 权限主键
	 * @param roleId 角色主键
	 * @author: Ryan
	 * @date 2018年11月12日
	 */
	@Override
	public Integer deleteAssociatedAttr(Long permissid, Long roleId) {
		Example example = new Example(GemRolePermissionsAttr.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("permisId", permissid);
		createCriteria.andEqualTo("roleId", roleId);
		return rolePermissionsAttrMapper.deleteByExample(example);
	}

	/**
	 * @Description: 添加属性关联
	 * @param permissid 权限主键
	 * @param roleId 角色主键
	 * @param attrId 属性主键
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	@Override
	public Integer saveAssociatedAttr(Long permissid, Long roleId, Long attrId) {
		GemRolePermissionsAttr rolePermissionsAttr = new GemRolePermissionsAttr();
		rolePermissionsAttr.setId(GemFrameIdUtlis.Id());
		rolePermissionsAttr.setRoleId(roleId);
		rolePermissionsAttr.setPermisId(permissid);
		rolePermissionsAttr.setAttrId(attrId);
		return rolePermissionsAttrMapper.insert(rolePermissionsAttr);
	}

	/**
	 * @Description:添加角色和属性的关联关系
	 * @param roleId 角色主键集合
	 * @param attrs 属性主键集合
	 * @param permiss 权限主键
	 * @author: Ryan  
	 * @date 2018年11月13日
	 */
	@Override
	public Integer saveRoleAndAttrs(Long[] roleId, Long permiss, Long[] attrs) {
		if(roleId!=null && roleId.length>0 && permiss!=null) {
			for (Long rid : roleId) {
				Example example = new Example(GemRolePermissionsAttr.class);
				Criteria createCriteria = example.createCriteria();
				createCriteria.andEqualTo("roleId", rid);
				createCriteria.andEqualTo("permisId", permiss);
				rolePermissionsAttrMapper.deleteByExample(example);
			}
		}else {
			throw new GemFrameException(GemFrameErrorStatus.PARAMETER_ERROR);
		}
		if(attrs!=null && attrs.length>0) {
			for (Long rid : roleId) {
				for (Long aid : attrs) {
					GemRolePermissionsAttr rolePermissionsAttr = new GemRolePermissionsAttr();
					rolePermissionsAttr.setId(GemFrameIdUtlis.Id());
					rolePermissionsAttr.setRoleId(rid);
					rolePermissionsAttr.setPermisId(permiss);
					rolePermissionsAttr.setAttrId(aid);
					rolePermissionsAttrMapper.insert(rolePermissionsAttr);
				}
			}
		}
		return null;
	}

}
