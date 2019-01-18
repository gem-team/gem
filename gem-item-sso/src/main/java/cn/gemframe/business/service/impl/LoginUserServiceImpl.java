/**
 * @Title:业务实现
 * @Description:用户登录
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

import cn.gemframe.business.dao.GemUserMapper;
import cn.gemframe.business.domain.GemUser;
import cn.gemframe.config.GemFrameUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * @Title:业务实现
 * @Description:用户登录
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Component
public class LoginUserServiceImpl implements UserDetailsService{

	@Autowired
	private GemUserMapper userMapper;

	/**
	 * @Description:获取用户登录信息
	 * @param username 用户名
	 * @author: Ryan
	 * @date 2018年11月6日
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		GemUser userByDataBase = getUserByDataBase(username);
		GemFrameUserDetails userData = new GemFrameUserDetails(username,userByDataBase.getPassWord());
		userData.setUserId(String.valueOf(userByDataBase.getId()));
		userData.setUsername(userByDataBase.getUserName());
		return userData;
	}

	/**
	 * @Description:查询用户
	 * @param username 用户名
	 * @author: Ryan
	 * @date 2018年11月6日
	 */
	public GemUser getUserByDataBase(String username) {
		Example example = new Example(GemUser.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("userName", username);
		List<GemUser> listUser = userMapper.selectByExample(example);
		if(listUser!=null && listUser.size()>0) {
			return listUser.get(0);
		}
		return null;
	}

}
