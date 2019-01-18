/**
 * @Title:业务接口
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
package cn.gemframe.business.service;

import cn.gemframe.business.domain.GemLearningExperience;
import cn.gemframe.business.vo.GemLearningExperienceVo;

import java.util.List;

/**
 * @Title:业务接口
 * @Description:员工学历信息管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemLearningExperienceService {

	/**
	 * @Description: 添加学习经历
	 * @param learningExperienceVo 学习经历参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	Integer saveLearning(GemLearningExperienceVo learningExperienceVo);

	/**
	 * @Description: 删除学习经历
	 * @param ids 学习经历主键集合
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	Integer deleteLearningById(Long[] ids);
	
	/**
	 * @Description: 删除学习经历
	 * @param memberId 员工主键
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	Integer deleteLearningByMemberId(Long memberId);
	
	/**
	 * @Description: 删除学习经历
	 * @param memberIds 员工主键集合
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	Integer deleteLearningByMemberIds(Long[] memberIds);

	/**
	 * @Description: 修改学习经历
	 * @param learningExperienceVo 学习经历参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	Integer updateLearning(GemLearningExperienceVo learningExperienceVo);

	/**
	 * @Description: 学习经历详情
	 * @param id 学习经历主键
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	GemLearningExperience findLearningById(Long id);

	/**
	 * @Description: 查询员工的学习经历
	 * @param memberId 员工主键
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	List<GemLearningExperience> findLearningByMemberId(Long memberId);
	
}
