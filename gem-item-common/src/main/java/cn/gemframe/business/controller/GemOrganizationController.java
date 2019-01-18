/**
 * @Title:控制器
 * @Description:组织管理
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gemframe.business.domain.GemOrganization;
import cn.gemframe.business.domain.GemOrganizationChange;
import cn.gemframe.business.service.GemOrganizationService;
import cn.gemframe.business.vo.GemOrganizationChangeVo;
import cn.gemframe.business.vo.GemOrganizationVo;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Title:控制器
 * @Description:组织管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemOrganizationController {
	
	@Autowired
	private GemOrganizationService organizationService;
	
	/**
	 * @Description:添加组织机构
	 * @param organizationVo 组织机构对象
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	@PostMapping("organ/saveOrgan")
	public ResponseEntity<ResultData> saveOrgan(GemOrganizationVo organizationVo){
		Integer flag=organizationService.saveOrgan(organizationVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:删除组织机构
	 * @param ids 组织机构主键集合
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	@PostMapping("organ/deletOrganById")
	public ResponseEntity<ResultData> deletOrganById(Long[] ids){
		Integer flag=organizationService.deletOrganById(ids);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	
	/**
	 * @Description:修改组织机构
	 * @param organizationVo 组织机构对象
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	@PostMapping("organ/updateOrgan")
	public ResponseEntity<ResultData> updateOrgan(GemOrganizationVo organizationVo, GemOrganizationChangeVo organizationChangeVo, MultipartFile file){
		Integer flag=organizationService.updateOrgan(organizationVo,organizationChangeVo,file);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:查询组织机构详情
	 * @param id 组织机构主键
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	@PostMapping("organ/findOrganById")
	public ResponseEntity<ResultData> findOrganById(Long id){
		GemOrganization flag=organizationService.findOrganById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:查询组织机构树
	 * @param name 名称
	 * @param time 事件
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@PostMapping("organ/findOrganZtree")
	public ResponseEntity<ResultData> findOrganZtree(String name,String time){
		List<GemOrganization> flag=organizationService.findOrganZtree(name,time);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:部门概要
	 * @param id 部门主键
	 * @author: Ryan  
	 * @date 2018年11月21日
	 */
	@PostMapping("organ/findOrganProfile")
	public ResponseEntity<ResultData> findOrganProfile(Long id){
		Map<String, Object> hashMap = new HashMap<String,Object>();
		//查询部门历史变更
		List<GemOrganizationChange> organChange=organizationService.findOrganProfile(id);
		hashMap.put("organChange",organChange);
		//部门人数
		int statis=organizationService.memberStatistical(id);
		hashMap.put("memberNumber",statis);
		//简介
		String introduction= organizationService.findOrganIntroduction(id);
		hashMap.put("introduction",introduction);
		return ResponseEntity.ok(ResultData.SUCCESS(hashMap));
	}
	
}
