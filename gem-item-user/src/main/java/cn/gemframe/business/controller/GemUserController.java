/**
 * @Title:控制器
 * @Description:用户信息管理
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

import cn.gemframe.business.domain.GemUser;
import cn.gemframe.business.vo.GemUserVo;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.gemframe.business.service.GemUserService;
import com.github.pagehelper.PageInfo;

/**
 * @Title:控制器
 * @Description:用户信息管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemUserController {
	
	@Autowired
	private GemUserService userService;
	
	/**
	 * @Description: 添加用户
	 * @param userVo 用户参数实体
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@PostMapping("user/saveUser")
	public ResponseEntity<ResultData> saveUser(GemUserVo userVo){
		Integer flag=userService.saveUser(userVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:删除用户
	 * @param id 用户主键
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@PostMapping("user/deleteUserByid")
	public ResponseEntity<ResultData> deleteUserByid(Long id){
		Integer flag=userService.deleteUserByid(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description: 修改用户
	 * @param userVo 用户参数实体
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@PostMapping("user/updateUser")
	public ResponseEntity<ResultData> updateUser(GemUserVo userVo){
		Integer flag=userService.updateUser(userVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:用户详情
	 * @param id 用户主键
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@PostMapping("user/findUserById")
	public ResponseEntity<ResultData> findUserById(Long id){
		GemUser flag=userService.findUserById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
	
	/**
	 * @Description:条件查询用户
	 * @param userVo 用户参数实体
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的数据
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@PostMapping("user/findUserList")
	public ResponseEntity<ResultData> findUserList(GemUserVo userVo, @RequestParam(defaultValue = "1") Integer pageNum,
                                                   @RequestParam(defaultValue = "10") Integer pageSize){
		List<GemUser> flag=userService.findUserList(userVo,pageNum,pageSize);
		PageInfo<GemUser> pageInfo = new PageInfo<>(flag);
		return ResponseEntity.ok(ResultData.SUCCESS(pageInfo));
	}
	
	/**
	 * @Description:根据角色查询用户
	 * @param roleId 角色主键
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的数据
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@PostMapping("user/findUserListByRoleId")
	public ResponseEntity<ResultData> findUserListByRoleId(Long roleId,@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize){
		List<GemUser> flag=userService.findUserListByRoleId(roleId,pageNum,pageSize);
		PageInfo<GemUser> pageInfo = new PageInfo<>(flag);
		return ResponseEntity.ok(ResultData.SUCCESS(pageInfo));
	}
}
