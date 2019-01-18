/**
 * @Title:控制器
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
package cn.gemframe.business.controller;

import java.util.List;

import cn.gemframe.business.domain.GemLearningExperience;
import cn.gemframe.business.vo.GemLearningExperienceVo;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.gemframe.business.service.GemLearningExperienceService;

/**
 * @Title:控制器
 * @Description:员工学历信息管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemLearningExperienceController {
	
	@Autowired
	private GemLearningExperienceService learningExperienceService;

	/**
	 * @Description: 添加学习经历
	 * @param learningExperienceVo 学习经历参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@PostMapping("learn/saveLearning")
	public ResponseEntity<ResultData> saveLearning(GemLearningExperienceVo learningExperienceVo){
		Integer flag=learningExperienceService.saveLearning(learningExperienceVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description: 删除学习经历
	 * @param ids 学习经历主键集合
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@PostMapping("learn/deleteLearningById")
	public ResponseEntity<ResultData> deleteLearningById(Long[] ids){
		Integer flag=learningExperienceService.deleteLearningById(ids);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description: 修改学习经历
	 * @param learningExperienceVo 学习经历参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@PostMapping("learn/updateLearning")
	public ResponseEntity<ResultData> updateLearning(GemLearningExperienceVo learningExperienceVo){
		Integer flag=learningExperienceService.updateLearning(learningExperienceVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description: 学习经历详情
	 * @param id 学习经历主键
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@PostMapping("learn/findLearningById")
	public ResponseEntity<ResultData> findLearningById(Long id){
		GemLearningExperience flag=learningExperienceService.findLearningById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description: 查询员工的学习经历
	 * @param memberId 员工主键
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@PostMapping("learn/findLearningByMemberId")
	public ResponseEntity<ResultData> findLearningByMemberId(Long memberId){
		List<GemLearningExperience> flag=learningExperienceService.findLearningByMemberId(memberId);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
}
