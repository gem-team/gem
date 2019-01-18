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

import cn.gemframe.business.domain.GemPermissionsParameter;
import cn.gemframe.business.domain.GemRolePermissionsParameter;
import cn.gemframe.business.vo.GemPermissionsParameterVo;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gemframe.business.dao.GemPermissionsParameterMapper;
import cn.gemframe.business.dao.GemRolePermissionsParameterMapper;
import cn.gemframe.business.service.GemPermissionsParameterService;

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
public class GemPermissionsParameterServiceImpl implements GemPermissionsParameterService {

	@Autowired
	private GemRolePermissionsParameterMapper rolePermissionsParameterMapper;
	@Autowired
	private GemPermissionsParameterMapper permissionsParameterMapper;
	
	/**
	 * @Description:添加权限的参数值
	 * @param permissionsParameterVo 权限的参数对象
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public Integer savePermissParam(GemPermissionsParameterVo permissionsParameterVo) {
		permissionsParameterVo.setCreateDate(new Date());
		permissionsParameterVo.setUpdateDate(new Date());
		GemPermissionsParameter permissionsParameter = GemFrameJsonUtils.classToClass(permissionsParameterVo, GemPermissionsParameter.class);
		permissionsParameter.setId(GemFrameIdUtlis.Id());
		return permissionsParameterMapper.insert(permissionsParameter);
	}

	/**
	 * @Description:删除权限的参数
	 * @param id 权限的参数值主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public Integer deletePermissParamById(Long[] id) {
		if(id!=null && id.length>0) {
			for (Long lid : id) {
				permissionsParameterMapper.deleteByPrimaryKey(lid);
			}
		}
		return null;
	}

	/**
	 * @Description:修改权限的参数值
	 * @param permissionsParameterVo 权限的参数对象
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public Integer updatePermissParam(GemPermissionsParameterVo permissionsParameterVo) {
		permissionsParameterVo.setUpdateDate(new Date());
		GemPermissionsParameter permissionsParameter = GemFrameJsonUtils.classToClass(permissionsParameterVo, GemPermissionsParameter.class);
		return permissionsParameterMapper.updateByPrimaryKeySelective(permissionsParameter);
	}

	/**
	 * @Description:根据主键查询权限的属性值
	 * @param id 权限的属性值主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public GemPermissionsParameter findPermissParamById(Long id) {
		return permissionsParameterMapper.selectByPrimaryKey(id);
	}

	/**
	 * @Description:根据权限主键查询属性值
	 * @param id 菜单主键
	 * @param roleId 角色主键
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	@Override
	public List<GemRolePermissionsParameter> findParamByPermissId(Long id, Long roleId) {
		if(id==null || roleId==null) {
			return null;
		}
		Example example = new Example(GemRolePermissionsParameter.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("permissId",id);
		createCriteria.andEqualTo("roleId",roleId);
		List<GemRolePermissionsParameter> selectByExample = rolePermissionsParameterMapper.selectByExample(example);
		if(selectByExample==null || selectByExample.size()==0) {
			Example example2 = new Example(GemPermissionsParameter.class);
			example2.createCriteria().andEqualTo("permissId",id);
			List<GemPermissionsParameter> selectByExample2 = permissionsParameterMapper.selectByExample(example2);
			String objectToJson = GemFrameJsonUtils.ObjectToJson(selectByExample2);
			selectByExample = GemFrameJsonUtils.jsonToList(objectToJson, GemRolePermissionsParameter.class);
		}
		return selectByExample;
	}
	
	@Override
	public List<GemRolePermissionsParameter> findParamByPermissIds(Long id, List<Long> roleId) {
		if(id==null || roleId.size()==0) {
			return null;
		}
		Example example = new Example(GemRolePermissionsParameter.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("permissId",id);
		createCriteria.andIn("roleId", roleId);
		List<GemRolePermissionsParameter> selectByExample = rolePermissionsParameterMapper.selectByExample(example);
		if(selectByExample==null || selectByExample.size()==0) {
			Example example2 = new Example(GemPermissionsParameter.class);
			example2.createCriteria().andEqualTo("permissId",id);
			List<GemPermissionsParameter> selectByExample2 = permissionsParameterMapper.selectByExample(example2);
			String objectToJson = GemFrameJsonUtils.ObjectToJson(selectByExample2);
			selectByExample = GemFrameJsonUtils.jsonToList(objectToJson, GemRolePermissionsParameter.class);
		}
		return selectByExample;
	}


	/**
	 * @Description:查询权限的参数值列表
	 * @param id 权限主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissionsParameter> findPermissParamList(Long id) {
		if(id!=null) {
			Example example = new Example(GemPermissionsParameter.class);
			Criteria createCriteria = example.createCriteria();
			createCriteria.andEqualTo("permissId", id);
			return permissionsParameterMapper.selectByExample(example);
		}
		return null;
	}

}
