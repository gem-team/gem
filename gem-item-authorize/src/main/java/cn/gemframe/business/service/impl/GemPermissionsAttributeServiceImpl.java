/**
 * @Title:业务实现
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
package cn.gemframe.business.service.impl;

import java.util.Date;
import java.util.List;

import cn.gemframe.business.dao.GemPermissionsAttributeMapper;
import cn.gemframe.business.domain.GemPermissionsAttribute;
import cn.gemframe.business.service.GemPermissionsAttributeService;
import cn.gemframe.business.vo.GemPermissionsAttributeVo;
import cn.gemframe.config.exception.GemFrameException;
import cn.gemframe.config.exception.status.GemFrameErrorStatus;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gemframe.business.service.GemRolePermissionsAttrService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @Title:业务实现
 * @Description:权限管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class GemPermissionsAttributeServiceImpl implements GemPermissionsAttributeService {
	
	@Autowired
	private GemPermissionsAttributeMapper permissionsAttributeMapper;
	@Autowired
	private GemRolePermissionsAttrService rolePermissionsAttrService;

	/**
	 * @Description:添加权限的属性值
	 * @param permissionsAttributeVo 权限的属性对象
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public Integer savePermissAttr(GemPermissionsAttributeVo permissionsAttributeVo) {
		permissionsAttributeVo.setCreateDate(new Date());
		permissionsAttributeVo.setUpdateDate(new Date());
		GemPermissionsAttribute permissionsAttribute = GemFrameJsonUtils.classToClass(permissionsAttributeVo, GemPermissionsAttribute.class);
		permissionsAttribute.setId(GemFrameIdUtlis.Id());
		return permissionsAttributeMapper.insert(permissionsAttribute);
	}

	/**
	 * @Description:删除权限的属性值
	 * @param id 权限的属性值主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public Integer deletePermissAttrById(Long[] id) {
		if(id!=null && id.length>0) {
			for (Long lid : id) {
				 permissionsAttributeMapper.deleteByPrimaryKey(lid);
			}
		}else {
			throw new GemFrameException(GemFrameErrorStatus.PARAMETER_ERROR);
		}
		return null;
	}

	/**
	 * @Description:修改权限的属性值
	 * @param permissionsAttributeVo 权限的属性对象
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public Integer updatePermissAttr(GemPermissionsAttributeVo permissionsAttributeVo) {
		permissionsAttributeVo.setUpdateDate(new Date());
		GemPermissionsAttribute permissionsAttribute = GemFrameJsonUtils.classToClass(permissionsAttributeVo, GemPermissionsAttribute.class);
		return permissionsAttributeMapper.updateByPrimaryKeySelective(permissionsAttribute);
	}

	/**
	 * @Description:根据主键查询权限的属性值
	 * @param id 权限的属性值主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public GemPermissionsAttribute findPermissAttrById(Long id) {
		return permissionsAttributeMapper.selectByPrimaryKey(id);
	}

	/**
	 * @Description:查询属性值
	 * @param id 权限主键
	 * @param roleId 角色主键
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	@Override
	public List<GemPermissionsAttribute> findAttrByPermissId(Long id, Long roleId) {
		if(id==null || roleId==null) {
			return null;
		}
		List<Long> ids=rolePermissionsAttrService.findAttrIds(id,roleId);
		Example example = new Example(GemPermissionsAttribute.class);
		example.createCriteria().andEqualTo("permissId", id);
		List<GemPermissionsAttribute> selectByExample = permissionsAttributeMapper.selectByExample(example);
		if(selectByExample!=null && selectByExample.size()>0 && ids!=null && ids.size()>0) {
			for (GemPermissionsAttribute permissionsAttribute : selectByExample) {
				Long id2 = permissionsAttribute.getId();
				if(ids.contains(id2)) {
					permissionsAttribute.setSelected(true);
				}
			}
		}
		return selectByExample;
	}

	/**
	 * @Description:查询属性值
	 * @param id 权限主键
	 * @param roles 角色主键
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	@Override
	public List<GemPermissionsAttribute> findAttrByPermissId(Long id, List<Long> roles) {
		if(id==null || roles.size()==0) {
			return null;
		}
		List<Long> ids=rolePermissionsAttrService.findAttrIds(id,roles);
		Example example = new Example(GemPermissionsAttribute.class);
		example.createCriteria().andEqualTo("permissId", id);
		List<GemPermissionsAttribute> selectByExample = permissionsAttributeMapper.selectByExample(example);
		if(selectByExample!=null && selectByExample.size()>0 && ids!=null && ids.size()>0) {
			for (GemPermissionsAttribute permissionsAttribute : selectByExample) {
				Long id2 = permissionsAttribute.getId();
				if(ids.contains(id2)) {
					permissionsAttribute.setSelected(true);
				}
			}
		}
		return selectByExample;
	}

	/**
	 * @Description: 关联属性值
	 * @param permissid 权限主键
	 * @param roleId 角色主键
	 * @param attrs 属性主键集合
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	@Override
	public Integer updateAssociatedAttr(Long permissid, Long roleId, Long[] attrs) {
		//删除关联
		if(permissid!=null && roleId!=null) {
			rolePermissionsAttrService.deleteAssociatedAttr(permissid,roleId);
		}
		//添加关联
		if(attrs!=null && attrs.length>0) {
			for (Long attrId : attrs) {
				  rolePermissionsAttrService.saveAssociatedAttr(permissid,roleId,attrId);
			}
		}
		return null;
	}

	/**
	 * @Description:属性列表
	 * @param id 权限主键
	 * @author: Ryan  
	 * @date 2018年11月14日
	 */
	@Override
	public List<GemPermissionsAttribute> findPermissAttrList(Long id) {
		if(id==null) {
			return null;
		}
		Example example = new Example(GemPermissionsAttribute.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("permissId", id);
		return permissionsAttributeMapper.selectByExample(example);
	}

}
