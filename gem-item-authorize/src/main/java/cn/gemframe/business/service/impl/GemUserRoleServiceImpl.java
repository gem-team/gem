/**
 * @Title:业务实现
 * @Description:用户角色管理
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

import cn.gemframe.business.domain.GemUserRole;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gemframe.business.dao.GemUserRoleMapper;
import cn.gemframe.business.service.GemUserRoleService;

import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @Title:业务实现
 * @Description:用户角色管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class GemUserRoleServiceImpl implements GemUserRoleService {

	@Autowired
	private GemUserRoleMapper userRoleMapper;

	/**
	 * @Description:根据角色删除关联关系
	 * @param id 角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer deleteByRoleId(Long id) {
		Example example = new Example(GemUserRole.class);
		example.createCriteria().andEqualTo("roleId", id);
		return userRoleMapper.deleteByExample(example);
	}

	/**
	 * @Description:根据用户删除关联关系
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer deleteRoleByUserId(Long id) {
		Example example = new Example(GemUserRole.class);
		example.createCriteria().andEqualTo("userId", id);
		return userRoleMapper.deleteByExample(example);
	}

	/**
	 * @Description: 添加用户和角色的关联关系
	 * @param userRole 角色和用户中间表的实体
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer saveUserAndRole(GemUserRole userRole) {
		userRole.setId(GemFrameIdUtlis.Id());
		userRole.setCreateDate(new Date());
		userRole.setUpdateDate(new Date());
		return userRoleMapper.insert(userRole);
	}

	/**
	 * 跟新用户对应的角色关系
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@Override
	public Integer updateUserToRole(Long userId,Long[] roleIds){
		//1.删除用户对应的角色关系
		Integer returnCount = deleteRoleByUserId(userId);
		//2.添加用户对应的角色关系
		if(roleIds!=null && roleIds.length>0) {
			for (Long roleId : roleIds) {
				GemUserRole userRole = new GemUserRole();
				userRole.setUserId(userId);
				userRole.setRoleId(roleId);
				saveUserAndRole(userRole);
			}
		}
		return returnCount;
	}

}
