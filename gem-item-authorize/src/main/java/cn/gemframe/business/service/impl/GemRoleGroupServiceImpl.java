/**
 * @Title:业务实现
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
package cn.gemframe.business.service.impl;

import java.util.Date;
import java.util.List;

import cn.gemframe.business.domain.GemRole;
import cn.gemframe.business.domain.GemRoleGroup;
import cn.gemframe.business.vo.GemRoleGroupVo;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import cn.gemframe.config.utils.GemFrameStringUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gemframe.business.dao.GemRoleGroupMapper;
import cn.gemframe.business.dao.GemRoleMapper;
import cn.gemframe.business.service.GemRoleGroupService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @Title:业务实现
 * @Description:角色组管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class GemRoleGroupServiceImpl implements GemRoleGroupService {

	@Autowired
	private GemRoleMapper roleMapper;
	@Autowired
	private GemRoleGroupMapper roleGroupMapper;

	/**
	 * @Description:添加角色组
	 * @param roleGroupVo 接收参数的实体对象
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	@Override
	public Integer saveRoleGroup(GemRoleGroupVo roleGroupVo) {
		roleGroupVo.setCreateDate(new Date());
		roleGroupVo.setUpdateDate(new Date());
		GemRoleGroup roleGroup = GemFrameJsonUtils.classToClass(roleGroupVo, GemRoleGroup.class);
		roleGroup.setId(GemFrameIdUtlis.Id());
		return roleGroupMapper.insert(roleGroup);
	}

	/**
	 * @Description:删除角色组
	 * @param id 主键
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	@Override
	public Integer deleteRoleGroup(Long id) {
		return roleGroupMapper.deleteByPrimaryKey(id);
	}


	/**
	 * @Description:修改角色组
	 * @param roleGroupVo 接收参数的实体对象
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	@Override
	public Integer updateRoleGroup(GemRoleGroupVo roleGroupVo) {
		roleGroupVo.setUpdateDate(new Date());
		GemRoleGroup roleGroup = GemFrameJsonUtils.classToClass(roleGroupVo, GemRoleGroup.class);
		return roleGroupMapper.updateByPrimaryKeySelective(roleGroup);
	}

	/**
	 * @Description:查询角色组详情
	 * @param id 主键
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	@Override
	public GemRoleGroup findRoleGroupById(Long id) {
		return roleGroupMapper.selectByPrimaryKey(id);
	}

	/**
	 * @Description:查询角色组以及所有角色（组成tree结构）
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	@Override
	public List<GemRoleGroup> findGroupRoleList() {
		Example example = new Example(GemRoleGroup.class);
		example.createCriteria().andEqualTo("parentId",-1);
		example.setOrderByClause("group_sort asc");
		List<GemRoleGroup> listGroup = roleGroupMapper.selectByExample(example);
		return findGroupChilds(listGroup);
	}

	/**
	 * @Description:递归遍历角色组下面的角色和角色组
	 * @param listGroup
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	private List<GemRoleGroup> findGroupChilds(List<GemRoleGroup> listGroup){
		if(listGroup!=null && listGroup.size()>0) {
			for (GemRoleGroup roleGroup : listGroup) {
				Example example = new Example(GemRole.class);
				Criteria createCriteria = example.createCriteria();
				createCriteria.andEqualTo("groupId", roleGroup.getId());
				example.setOrderByClause("role_sort asc");
				List<GemRole> selectByExample = roleMapper.selectByExample(example);
				roleGroup.setRoles(selectByExample);

				Example example2 = new Example(GemRoleGroup.class);
				example2.createCriteria().andEqualTo("parentId", roleGroup.getId());
				example2.setOrderByClause("group_sort asc");
				List<GemRoleGroup> selectByExample2 = roleGroupMapper.selectByExample(example2);
				roleGroup.setChildren(selectByExample2);
				findGroupChilds(selectByExample2);
			}
		}
		return listGroup;
	}

}
