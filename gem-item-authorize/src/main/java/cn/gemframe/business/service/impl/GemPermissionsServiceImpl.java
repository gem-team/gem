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
import java.util.Map;

import cn.gemframe.business.constant.AuthsConstant;
import cn.gemframe.business.domain.GemPermissions;
import cn.gemframe.business.domain.GemPermissionsAttribute;
import cn.gemframe.business.domain.GemRole;
import cn.gemframe.business.domain.GemRolePermissionsParameter;
import cn.gemframe.business.vo.GemPermissionsVo;
import cn.gemframe.config.exception.GemFrameException;
import cn.gemframe.config.exception.status.GemFrameErrorStatus;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gemframe.business.dao.GemPermissionsMapper;
import cn.gemframe.business.service.GemPermissionsAttributeService;
import cn.gemframe.business.service.GemPermissionsParameterService;
import cn.gemframe.business.service.GemPermissionsService;
import cn.gemframe.business.service.GemRolePermissionsService;
import cn.gemframe.business.service.GemRoleService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
	@Override
	public List<GemPermissions> findPermissByRoleId(Long id) {
		List<Long> ids=null;
		if(id!=null) {
			Map<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("id", id);
			ids=permissionsMapper.findPermissByRole(hashMap);
		}else {
			return null;
		}
		return findAllMenu(ids, AuthsConstant.MENU_ONESELF);
	}

	/**
	 * @Description: 根据角色获取权限按钮
	 * @param id 角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public List<Long> findPermissMunByRoleId(Long id) {
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("id", id);
		return permissionsMapper.findPermissMunByRole(hashMap);
	}

	/**
	 * @Description: 根据用户获取权限菜单
	 * @param id 用户主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissions> findPermissByUserId(Long id) {
		List<Long> ids=null;
		if(id!=null) {
			Map<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("id", id);
			ids=permissionsMapper.findPermissByUser(hashMap);
		}else {
			return null;
		}
		return findAllMenu(ids,AuthsConstant.MENU_ONESELF);
	}

	/**
	 * @Description:查看权限详情
	 * @param id 权限主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public GemPermissions findPermissById(Long id) {
		return permissionsMapper.selectByPrimaryKey(id);
	}

	/**
	 * @Description:修改权限
	 * @param permissionsVo 权限接受参数实体
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public Integer updatePermiss(GemPermissionsVo permissionsVo) {
		permissionsVo.setUpdateDate(new Date());
		GemPermissions permissions = GemFrameJsonUtils.classToClass(permissionsVo, GemPermissions.class);
		return permissionsMapper.updateByPrimaryKeySelective(permissions);
	}

	/**
	 * @Description:添加权限
	 * @param permissionsVo 权限接受参数实体
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public Integer savePermiss(GemPermissionsVo permissionsVo) {
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
	public Integer deletePermissById(Long[] id) {
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
	 * @Description:查看菜单下的子权限
	 * @param id 菜单主键
	 * @param roleId 角色主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissions> findPermissByParentId(Long id, Long roleId) {
		List<Long> ids=null;
		if(roleId!=null) {
			Map<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("id", roleId);
			ids=permissionsMapper.findPermissByRole(hashMap);
		}
		Example example = new Example(GemPermissions.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("parentId",id);
		createCriteria.andEqualTo("menusType","1");
		List<GemPermissions> selectByExample = permissionsMapper.selectByExample(example);
		if(selectByExample!=null && selectByExample.size()>0 && ids!=null && ids.size()>0) {
			for (GemPermissions permissions : selectByExample) {
				Long id2 = permissions.getId();
				if(ids.contains(id2)) {
					permissions.setSelected(true);
				}
			}
		}
		return selectByExample;
	}

	/**
	 * @Description: 查询所有权限菜单
	 * @param ids 权限主键集合
	 * @param type 0查询拥有的权限，不拥有的不显示 ，1查询所有，字段标识是否拥有
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissions> findAllMenu(List<Long> ids, String type) {
		Example example = new Example(GemPermissions.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("parentId",-1);
		createCriteria.andEqualTo("menusType",0);
		List<GemPermissions> selectByExample = permissionsMapper.selectByExample(example);
		if(selectByExample!=null && selectByExample.size()>0) {
			for (GemPermissions permissions : selectByExample) {
				Long id = permissions.getId();
				if(ids!=null && ids.size()>0) {
					if(ids.contains(id)) {
						permissions.setSelected(true);
					}
				}
			}
		}
		return getChildsPermiss(selectByExample,ids,type);
	}
	
	/**
	 * @Description: 递归查询所有的权限列表
	 * @param listPermiss 所有的权限集合
	 * @param ids 用户拥有的权限主键集合
	 * @param type 0查询拥有的权限，不拥有的不显示 ，1查询所有，字段标识是否拥有
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	public List<GemPermissions> getChildsPermiss(List<GemPermissions> listPermiss, List<Long> ids, String type) {
		int levelsNUmber=3;
		if(listPermiss!=null && listPermiss.size()>0) {
			for (GemPermissions permissions : listPermiss) {
				Example example = new Example(GemPermissions.class);
				Criteria createCriteria = example.createCriteria();
				createCriteria.andEqualTo("parentId",permissions.getId());
				createCriteria.andEqualTo("menusType",0);
				Integer levels = permissions.getLevels();
				if(levels!=null && levels<levelsNUmber) {
					List<GemPermissions> selectByExample = permissionsMapper.selectByExample(example);
					if(selectByExample!=null && selectByExample.size()>0) {
						for (GemPermissions pers : selectByExample) {
							Long id = pers.getId();
							if(ids!=null && ids.size()>0) {
								if(ids.contains(id)){
									pers.setSelected(true);
								}
							}
						}
					}
					if(selectByExample!=null && selectByExample.size()>0) {
						permissions.setChilds(selectByExample);
					}
					getChildsPermiss(selectByExample,ids,type);
				}
			}
		}
		return listPermiss;
	}

	/**
	 * @Description: 根据用户获取所有权限（包含参数，属性和子权限）
	 * @param id 用户主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemPermissions> findUserPermiss(Long id) {
		if(id==null) {
			return null;
		}
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("id", id);
		List<Long> permissIds = permissionsMapper.findPermissByUser(hashMap);
		List<Long> roleIds = roleService.findRoleIdsByUser(id);
		Example example = new Example(GemPermissions.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("parentId",-1);
		List<GemPermissions> listPermissions = permissionsMapper.selectByExample(example);
		return findUserAllPermiss(listPermissions,roleIds,permissIds);
	}
	
	public  List<GemPermissions> findUserAllPermiss(List<GemPermissions> listPermissions, List<Long> roles, List<Long> permissIds){
		if(listPermissions!=null && listPermissions.size()>0) {
			for (GemPermissions permissions : listPermissions) {
				Long id = permissions.getId();
				if(permissIds!=null && permissIds.size()>0) {
					if(permissIds.contains(id)) {
						permissions.setSelected(true);
					}
				}
				Example example = new Example(GemPermissions.class);
				Criteria createCriteria = example.createCriteria();
				createCriteria.andEqualTo("parentId",permissions.getId());
				List<GemPermissions> selectByExample = permissionsMapper.selectByExample(example);
				if(selectByExample!=null && selectByExample.size()>0) {
					for (GemPermissions permissionsChild : selectByExample) {
						Long idChild = permissionsChild.getId();
						if(permissIds!=null && permissIds.size()>0) {
							if(permissIds.contains(idChild)) {
								permissionsChild.setSelected(true);
							}
						}
					}
				}
				if(selectByExample!=null && selectByExample.size()>0) {
					permissions.setChilds(selectByExample);
				}
				List<GemPermissionsAttribute> findAttrByPermissId = permissionsAttributeService.findAttrByPermissId(permissions.getId(),roles);
				permissions.setAttrs(findAttrByPermissId);
				List<GemRolePermissionsParameter> findParamByPermissIds = permissionsParameterService.findParamByPermissIds(permissions.getId(), roles);
				permissions.setParams(findParamByPermissIds);
				findUserAllPermiss(permissions.getChilds(),roles,permissIds);
			}
		}
		return listPermissions;
	}

}
