/**
 * @Title:业务实现
 * @Description:数据字典管理
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
import java.util.Date;
import java.util.List;

import cn.gemframe.business.domain.GemOrganization;
import cn.gemframe.business.domain.GemOrganizationChange;
import cn.gemframe.business.vo.GemOrganizationChangeVo;
import cn.gemframe.business.vo.GemOrganizationVo;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import cn.gemframe.config.utils.GemFrameStringUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.gemframe.business.dao.GemOrganizationChangeMapper;
import cn.gemframe.business.dao.GemOrganizationMapper;
import cn.gemframe.business.service.GemOrganizationService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @Title:业务实现
 * @Description:组织管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class GemOrganizationServiceImpl implements GemOrganizationService {

	@Autowired
	private GemOrganizationMapper organizationMapper;
	@Autowired
	private GemOrganizationChangeMapper organizationChangeMapper;
	
	private List<Long> arrayList = new ArrayList<Long>();
	
	
	/**
	 * @Description:添加组织机构
	 * @param organizationVo 组织机构对象
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	@Override
	public Integer saveOrgan(GemOrganizationVo organizationVo) {
		GemOrganization organization = GemFrameJsonUtils.classToClass(organizationVo, GemOrganization.class);
		organization.setId(GemFrameIdUtlis.Id());
		return organizationMapper.insert(organization);
	}


	/**
	 * @Description:删除组织机构
	 * @param ids 组织机构主键集合
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	@Override
	public Integer deletOrganById(Long[] ids) {
		if(ids!=null && ids.length>0) {
			for (Long id : ids) {
				organizationMapper.deleteByPrimaryKey(id);
			}
		}
		return null;
	}

	/**
	 * @Description:修改组织机构
	 * @param organizationVo 组织机构对象
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	@Override
	public Integer updateOrgan(GemOrganizationVo organizationVo, GemOrganizationChangeVo organizationChangeVo, MultipartFile file) {
		GemOrganization organization = GemFrameJsonUtils.classToClass(organizationVo, GemOrganization.class);
		GemOrganizationChange organizationChange = GemFrameJsonUtils.classToClass(organizationChangeVo, GemOrganizationChange.class);
		organizationChange.setId(GemFrameIdUtlis.Id());
		organizationChangeMapper.insert(organizationChange);
		return organizationMapper.updateByPrimaryKeySelective(organization);
	}

	/**
	 * @Description:查询组织机构详情
	 * @param id 组织机构主键
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	@Override
	public GemOrganization findOrganById(Long id) {
		return organizationMapper.selectByPrimaryKey(id);
	}


	/**
	 * @Description:查询组织机构树
	 * @param name 名称
	 * @param time 事件
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public List<GemOrganization> findOrganZtree(String name,String time) {
		arrayList.clear();
		List<Long> organIds = getOrganIds(name,time);
		Example example = new Example(GemOrganization.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIsNull("parentId");
		if(organIds!=null && organIds.size()>0) {
			createCriteria.andIn("id", organIds);
		}
		List<GemOrganization> selectByExample = organizationMapper.selectByExample(example);
		return getOrganZtree(selectByExample,organIds);
	}
	
	/**
	 * @Description:查询组织机构主键集合
	 * @param name 组织机构名称
	 * @param time 组织机构生效时间
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	public List<Long> getOrganIds(String name,String time){
		Example example = new Example(GemOrganization.class);
		example.selectProperties("id","parentId");
		Criteria createCriteria = example.createCriteria();
		if(GemFrameStringUtlis.isNotBlank(name) && !name.equalsIgnoreCase("null") && name.length()>0) {
			createCriteria.andLike("organName", "%"+name+"%");
		}
		if(GemFrameStringUtlis.isNotBlank(time) && !time.equalsIgnoreCase("null") && time.length()>0) {
			Date minStringDate = GemFrameStringUtlis.minStringDate(time);
			Date maxStringDate = GemFrameStringUtlis.maxStringDate(time);
			createCriteria.andLessThanOrEqualTo("validTime", minStringDate);
			createCriteria.andGreaterThanOrEqualTo("validTime", maxStringDate);
		}
		List<GemOrganization> selectByExample = organizationMapper.selectByExample(example);
		if(selectByExample!=null && selectByExample.size()>0) {
			for (GemOrganization organization : selectByExample) {
				Long id = organization.getId();
				Long parentId = organization.getParentId();
				if(parentId!=null) {
					arrayList.add(parentId);
					getParentId(parentId);
				}
				arrayList.add(id);
			}
		}
		return arrayList;
	}
	
	/**
	 * @Description:递归获取父级id
	 * @param id 主键
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	public void getParentId(Long id) {
		GemOrganization selectByPrimaryKey = organizationMapper.selectByPrimaryKey(id);
		Long parentId = selectByPrimaryKey.getParentId();
		if(parentId!=null) {
			arrayList.add(parentId);
			getParentId(parentId);
		}
	}
	
	/**
	 * @Description: 递归查询组织机构树
	 * @param listOrganization 组织机构集合
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	public List<GemOrganization> getOrganZtree(List<GemOrganization> listOrganization,List<Long> ids){
		if(listOrganization!=null && listOrganization.size()>0) {
			for (GemOrganization organization : listOrganization) {
				Long id = organization.getId();
				Example example = new Example(GemOrganization.class);
				Criteria createCriteria = example.createCriteria();
				createCriteria.andEqualTo("parentId", id);
				if(ids!=null && ids.size()>0) {
					createCriteria.andIn("id", ids);
				}
				List<GemOrganization> selectByExample = organizationMapper.selectByExample(example);
				organization.setChilds(selectByExample);
				getOrganZtree(selectByExample,ids);
			}
		}
		return listOrganization;
	}

	/**
	 * @Description:查询部门历史变更
	 * @param id 部门主键
	 * @author: Ryan  
	 * @date 2018年11月21日
	 */
	@Override
	public List<GemOrganizationChange> findOrganProfile(Long id) {
		if(id==null) {
			return null;
		}
		Example example = new Example(GemOrganizationChange.class);
		example.selectProperties("id","changeTime","eventName");
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("organId", id);
		return organizationChangeMapper.selectByExample(example);
	}

	/**
	 * @Description:查询部门人数
	 * @param id 部门主键
	 * @author: Ryan  
	 * @date 2018年11月21日
	 */
	@Override
	public int memberStatistical(Long id) {
		if(id==null) {
			return 0;
		}
		Example example = new Example(GemOrganizationChange.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("organId", id);
		return organizationChangeMapper.selectCountByExample(example);
	}

	/**
	 * @Description:部门简介
	 * @param id 部门主键
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public String findOrganIntroduction(Long id) {
		GemOrganization selectByPrimaryKey = organizationMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey.getIntroductions();
	}

}
