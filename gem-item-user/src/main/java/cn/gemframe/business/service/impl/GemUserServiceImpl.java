/**
 * @Title:业务实现
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
package cn.gemframe.business.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.gemframe.business.dao.GemUserMapper;
import cn.gemframe.business.domain.GemUser;
import cn.gemframe.business.vo.GemUserRoleVo;
import cn.gemframe.business.vo.GemUserVo;
import cn.gemframe.config.constant.GemFrameConstant;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import cn.gemframe.config.utils.GemFramePasswordUtil;
import cn.gemframe.config.utils.GemFrameStringUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gemframe.business.feign.GemRoleFeginClient;
import cn.gemframe.business.service.GemUserService;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @Title:业务实现
 * @Description:用户信息管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
@Transactional
public class GemUserServiceImpl implements GemUserService {

	@Autowired
	private GemUserMapper userMapper;
	@Autowired
	private GemRoleFeginClient roleFeginClient;
	
	
	/**
	 * @Description: 添加用户
//	 * @param userVo 用户参数实体
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@Override
	public Integer saveUser(GemUserVo uservo) {
		String passWord = getPassWord(String.valueOf(uservo.getPasswordType()),uservo.getCard(),uservo.getUserName());
		uservo.setCreateDate(new Date());
		uservo.setUpdateDate(new Date());
		GemUser user = GemFrameJsonUtils.classToClass(uservo, GemUser.class);
		Long userId = GemFrameIdUtlis.Id();
		user.setId(userId);
		user.setPassWord(passWord);
		String[] roles = uservo.getRoles();
		if(roles!=null && roles.length>0) {
			for (String roleId : roles) {
				GemUserRoleVo gemUserRoleVo = new GemUserRoleVo();
				gemUserRoleVo.setId(GemFrameIdUtlis.Id());
				gemUserRoleVo.setUserId(userId);
				gemUserRoleVo.setRoleId(new Long(roleId));
				roleFeginClient.saveUserAndRole(gemUserRoleVo);
			}
		}
		return userMapper.insert(user);
	}
	
	/**
	 * @Description:条件查询用户
	 * @param userVo 用户参数实体
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的数据
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@Override
	public List<GemUser> findUserList(GemUserVo userVo, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Example example = new Example(GemUser.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIsNotNull("id");
		String userName = userVo.getUserName();
		if(GemFrameStringUtlis.isNotBlank(userName) && !userName.equalsIgnoreCase("null") && userName.length()>0) {
			createCriteria.andLike("userName","%"+userName+"%");
		}
		String memberName = userVo.getMemberName();
		if(GemFrameStringUtlis.isNotBlank(memberName) && !memberName.equalsIgnoreCase("null") && memberName.length()>0) {
			createCriteria.andLike("memberName","%"+memberName+"%");
		}
		String phone = userVo.getPhone();
		if(GemFrameStringUtlis.isNotBlank(phone) && !phone.equalsIgnoreCase("null") && phone.length()>0) {
			createCriteria.andEqualTo("phone", phone);
		}
		String email = userVo.getEmail();
		if(GemFrameStringUtlis.isNotBlank(email) && !email.equalsIgnoreCase("null") && email.length()>0) {
			createCriteria.andEqualTo("email", email);
		}
		return userMapper.selectByExample(example);
	}
	
	public String getPassWord(String passWordType,String card,String userName) {
		if(GemFrameStringUtlis.isNotBlank(passWordType) && passWordType.length()>0) {
			if(passWordType.equalsIgnoreCase("0")) {
				return GemFramePasswordUtil.encode(GemFrameConstant.PASS_WORD);
			}
			if(passWordType.equalsIgnoreCase("1")) {
				return GemFramePasswordUtil.encode(userName);
			}
			if(passWordType.equalsIgnoreCase("2") && card.length()>7) {
				card.substring(card.length()-8, card.length());
			}
		}else {
			return GemFramePasswordUtil.encode(GemFrameConstant.PASS_WORD);
		}
		return null;
	}

	/**
	 * @Description:删除用户
	 * @param id 用户主键
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@Override
	public Integer deleteUserByid(Long id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @Description: 修改用户
	 * @param userVo 用户参数实体
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@Override
	public Integer updateUser(GemUserVo userVo) {
		userVo.setUpdateDate(new Date());
		GemUser user = GemFrameJsonUtils.classToClass(userVo, GemUser.class);
		return userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * @Description:根据角色查询用户
	 * @param roleId 角色主键
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的数据
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@Override
	public List<GemUser> findUserListByRoleId(Long roleId, Integer pageNum, Integer pageSize) {
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("roleId", roleId);
		PageHelper.startPage(pageNum, pageSize);
		List<GemUser> listUser=userMapper.findUserListByRoleId(hashMap);
		return listUser;
	}

	/**
	 * @Description:用户详情
	 * @param id 用户主键
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	@Override
	public GemUser findUserById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

}
