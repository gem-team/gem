/**
 * @Title:业务实现
 * @Description:员工学历信息管理
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

import java.util.List;

import cn.gemframe.business.domain.GemLearningExperience;
import cn.gemframe.business.service.GemLearningExperienceService;
import cn.gemframe.business.vo.GemLearningExperienceVo;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gemframe.business.dao.GemLearningExperienceMapper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @Title:业务实现
 * @Description:员工学历信息管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
@Transactional
public class GemLearningExperienceServiceImpl implements GemLearningExperienceService {

	@Autowired
	private GemLearningExperienceMapper learningExperienceMapper;
	/**
	 * @Description: 添加学习经历
	 * @param learningExperienceVo 学习经历参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public Integer saveLearning(GemLearningExperienceVo learningExperienceVo) {
		GemLearningExperience classToClass = GemFrameJsonUtils.classToClass(learningExperienceVo, GemLearningExperience.class);
		classToClass.setId(GemFrameIdUtlis.Id());
		return learningExperienceMapper.insert(classToClass);
	}
	
	/**
	 * @Description: 删除学习经历
	 * @param ids 学习经历主键集合
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public Integer deleteLearningById(Long[] ids) {
		if(ids!=null && ids.length>0) {
			for (Long id : ids) {
				learningExperienceMapper.deleteByPrimaryKey(id);
			}
		}
		return null;
	}

	/**
	 * @Description: 修改学习经历
	 * @param learningExperienceVo 学习经历参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public Integer updateLearning(GemLearningExperienceVo learningExperienceVo) {
		GemLearningExperience classToClass = GemFrameJsonUtils.classToClass(learningExperienceVo, GemLearningExperience.class);
		return learningExperienceMapper.updateByPrimaryKeySelective(classToClass);
	}

	/**
	 * @Description: 学习经历详情
	 * @param id 学习经历主键
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public GemLearningExperience findLearningById(Long id) {
		return learningExperienceMapper.selectByPrimaryKey(id);
	}

	/**
	 * @Description: 查询员工的学习经历
	 * @param memberId 员工主键
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public List<GemLearningExperience> findLearningByMemberId(Long memberId) {
		Example example = new Example(GemLearningExperience.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("memberId", memberId);
		return learningExperienceMapper.selectByExample(example);
	}

	/**
	 * @Description: 删除学习经历
	 * @param memberId 员工主键
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public Integer deleteLearningByMemberId(Long memberId) {
		Example example = new Example(GemLearningExperience.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("memberId", memberId);
		return learningExperienceMapper.deleteByExample(example);
	}

	/**
	 * @Description: 删除学习经历
	 * @param memberIds 员工主键集合
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@Override
	public Integer deleteLearningByMemberIds(Long[] memberIds) {
		if(memberIds!=null && memberIds.length>0) {
			for (Long memberId : memberIds) {
				Example example = new Example(GemLearningExperience.class);
				Criteria createCriteria = example.createCriteria();
				createCriteria.andEqualTo("memberId", memberId);
				learningExperienceMapper.deleteByExample(example);
			}
		}
		return null;
	}
	
}
