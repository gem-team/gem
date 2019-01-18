/**
 * @Title:业务实现
 * @Description:员工信息管理
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

import java.util.Arrays;
import java.util.List;

import cn.gemframe.business.domain.GemLearningExperience;
import cn.gemframe.business.domain.GemMember;
import cn.gemframe.business.domain.GemOrganization;
import cn.gemframe.business.vo.GemMemberVo;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import cn.gemframe.config.utils.GemFrameStringUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gemframe.business.dao.GemLearningExperienceMapper;
import cn.gemframe.business.dao.GemMemberMapper;
import cn.gemframe.business.dao.GemOrganizationMapper;
import cn.gemframe.business.service.GemLearningExperienceService;
import cn.gemframe.business.service.GemMemberService;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @Title:业务实现
 * @Description:员工信息管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
@Transactional
public class GemMemberServiceImpl implements GemMemberService {

	@Autowired
	private GemMemberMapper memberMapper;
	@Autowired
	private GemOrganizationMapper organizationMapper;
	@Autowired
	private GemLearningExperienceMapper learningExperienceMapper;
	@Autowired
	private GemLearningExperienceService learningExperienceService;
	
	/**
	 * @Description: 添加员工
	 * @param memberVo 员工参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public Integer saveMember(GemMemberVo memberVo) {
		GemMember member = GemFrameJsonUtils.classToClass(memberVo, GemMember.class);
		Long id = GemFrameIdUtlis.Id();
		member.setId(id);
		int insert = memberMapper.insert(member);
		Long[] learnIds = memberVo.getLearnIds();
		if(learnIds!=null && learnIds.length>0) {
			Example example = new Example(GemLearningExperience.class);
			Criteria createCriteria = example.createCriteria();
			List<Long> asList = Arrays.asList(learnIds);
			createCriteria.andIn("id", asList);
			GemLearningExperience learningExperience = new GemLearningExperience();
			learningExperience.setMemberId(id);
			learningExperienceMapper.updateByExampleSelective(learningExperience, example);
		}
		return insert;
	}

	/**
	 * @Description:删除员工
	 * @param ids 员工主键集合
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public Integer deleteMemberById(Long[] ids) {
		if(ids!=null && ids.length>0) {
			learningExperienceService.deleteLearningByMemberIds(ids);
			Example example = new Example(GemMember.class);
			Criteria createCriteria = example.createCriteria();
			List<Long> asList = Arrays.asList(ids);
			createCriteria.andIn("id", asList);
			return memberMapper.deleteByExample(example);
		}
		return null;
	}

	/**
	 * @Description: 修改员工
	 * @param memberVo 员工参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public Integer updateMember(GemMemberVo memberVo) {
		Long id = memberVo.getId();
		learningExperienceService.deleteLearningByMemberId(id);
		GemMember member = GemFrameJsonUtils.classToClass(memberVo, GemMember.class);
		int updateByPrimaryKeySelective = memberMapper.updateByPrimaryKeySelective(member);
		Long[] learnIds = memberVo.getLearnIds();
		if(learnIds!=null && learnIds.length>0) {
			Example example = new Example(GemLearningExperience.class);
			Criteria createCriteria = example.createCriteria();
			List<Long> asList = Arrays.asList(learnIds);
			createCriteria.andIn("id", asList);
			GemLearningExperience learningExperience = new GemLearningExperience();
			learningExperience.setMemberId(id);
			learningExperienceMapper.updateByExampleSelective(learningExperience, example);
		}
		return updateByPrimaryKeySelective;
	}

	/**
	 * @Description: 员工详情
	 * @param memberVo 员工参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public GemMember findMemberById(Long id) {
		GemMember selectByPrimaryKey = memberMapper.selectByPrimaryKey(id);
		Example example = new Example(GemLearningExperience.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("memberId", id);
		List<GemLearningExperience> selectByExample = learningExperienceMapper.selectByExample(example);
		selectByPrimaryKey.setLearns(selectByExample);
		String organId = selectByPrimaryKey.getOrganId();
		if(GemFrameStringUtlis.isNotBlank(organId) && !organId.equalsIgnoreCase("null") && organId.length()>0) {
			GemOrganization selectByPrimaryKey2 = organizationMapper.selectByPrimaryKey(new Long(organId));
			if(selectByPrimaryKey2!=null) {
				String organName = selectByPrimaryKey2.getOrganName();
				selectByPrimaryKey.setOrganName(organName);
			}
		}
		return selectByPrimaryKey;
	}

	/**
	 * @Description: 员工列表
	 * @param memberVo 员工参数接受对象
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的数据
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public List<GemMember> findMemberList(GemMemberVo memberVo, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Example example = new Example(GemMember.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIsNotNull("id");
		String memberName = memberVo.getMemberName();
		if(GemFrameStringUtlis.isNotBlank(memberName) && !memberName.equalsIgnoreCase("null") && memberName.length()>0) {
			createCriteria.andLike("memberName", "%"+memberName+"%");
		}
		String phone = memberVo.getPhone();
		if(GemFrameStringUtlis.isNotBlank(phone) && !phone.equalsIgnoreCase("null") && phone.length()>0) {
			createCriteria.andLike("phone", "%"+phone+"%");
		}
		String card = memberVo.getCard();
		if(GemFrameStringUtlis.isNotBlank(card) && !card.equalsIgnoreCase("null") && card.length()>0) {
			createCriteria.andLike("card", "%"+card+"%");
		}
		List<GemMember> selectByExample = memberMapper.selectByExample(example);
		if(selectByExample!=null && selectByExample.size()>0) {
			for (GemMember member : selectByExample) {
				String organId = member.getOrganId();
				if(GemFrameStringUtlis.isNotBlank(organId) && !organId.equalsIgnoreCase("null") && organId.length()>0) {
					GemOrganization selectByPrimaryKey2 = organizationMapper.selectByPrimaryKey(new Long(organId));
					if(selectByPrimaryKey2!=null) {
						String organName = selectByPrimaryKey2.getOrganName();
						member.setOrganName(organName);
					}
				}
			}
		}
		return selectByExample;
	}
}
