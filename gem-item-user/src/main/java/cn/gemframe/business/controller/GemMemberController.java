/**
 * @Title:控制器
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
package cn.gemframe.business.controller;

import java.util.List;

import cn.gemframe.business.domain.GemMember;
import cn.gemframe.business.service.GemMemberService;
import cn.gemframe.business.vo.GemMemberVo;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

/**
 * @Title:控制器
 * @Description:员工信息管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemMemberController {
	
	@Autowired
	private GemMemberService gemMemberService;
	
	/**
	 * @Description: 添加员工
	 * @param memberVo 员工参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@PostMapping("member/saveMember")
	public ResponseEntity<ResultData> saveMember(GemMemberVo memberVo){
		Integer flag= gemMemberService.saveMember(memberVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:删除员工
	 * @param ids 员工主键集合
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@PostMapping("member/deleteMemberById")
	public ResponseEntity<ResultData> deleteMemberById(Long[] ids){
		Integer flag= gemMemberService.deleteMemberById(ids);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description: 修改员工
	 * @param memberVo 员工参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@PostMapping("member/updateMember")
	public ResponseEntity<ResultData> updateMember(GemMemberVo memberVo){
		Integer flag= gemMemberService.updateMember(memberVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description: 员工详情
	 * @param memberVo 员工参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@PostMapping("member/findMemberById")
	public ResponseEntity<ResultData> findMemberById(Long id){
		GemMember flag= gemMemberService.findMemberById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description: 员工列表
	 * @param memberVo 员工参数接受对象
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的数据
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	@PostMapping("member/findMemberList")
	public ResponseEntity<ResultData> findMemberList(GemMemberVo memberVo, @RequestParam(defaultValue = "1") Integer pageNum,
													 @RequestParam(defaultValue = "10") Integer pageSize){
		List<GemMember> flag= gemMemberService.findMemberList(memberVo,pageNum,pageSize);
		PageInfo<GemMember> pageInfo = new PageInfo<>(flag);
		return ResponseEntity.ok(ResultData.SUCCESS(pageInfo));
	}
}
