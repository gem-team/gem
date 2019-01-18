/**
 * @Title:业务实现
 * @Description:验证信息
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
import java.util.concurrent.TimeUnit;

import cn.gemframe.business.dao.GemUserMapper;
import cn.gemframe.business.domain.GemUser;
import cn.gemframe.business.service.GemRetrieveService;
import cn.gemframe.config.constant.GemFrameConstant;
import cn.gemframe.config.exception.GemFrameException;
import cn.gemframe.config.exception.status.GemFrameErrorStatus;
import cn.gemframe.config.mail.GemFrameMailConfigurationService;
import cn.gemframe.config.mail.GemFrameSendMailService;
import cn.gemframe.config.utils.GemFramePasswordUtil;
import cn.gemframe.config.utils.GemFrameStringUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @Title:业务实现
 * @Description:验证信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class RetrieveServiceImpl implements GemRetrieveService, GemFrameMailConfigurationService {
	
	@Autowired
	private GemUserMapper userMapper;
	@Autowired
	private GemFrameSendMailService sendMailService;
	@Autowired
	private ValueOperations<String, Object> valueOperations;

	@Override
	public String mailHost() {
		return "smtp.126.com";
	}

	@Override
	public String mailUserName() {
		return "dianshi_test@126.com";
	}

	@Override
	public String mailUserPassword() {
		return "123456dianshi1";
	}

	/**
	 * @Description:发送验证码
	 * @param name 手机号或者邮箱
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	@Override
	public String sendVrification(String name) {
		if(GemFrameStringUtlis.isBlank(name) || name.equalsIgnoreCase(GemFrameConstant.NULL) && name.length()==0) {
			throw new GemFrameException(GemFrameErrorStatus.PHONE_EMAIL_ISNULL);
		}
		Example example = new Example(GemUser.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("userName",name);
		Criteria createCriteria2=example.createCriteria();
		createCriteria2.andEqualTo("email", name);
		example.or(createCriteria);
		example.or(createCriteria2);
		String vrificationCode = GemFrameStringUtlis.vrificationCode();
		List<GemUser> selectByExample = userMapper.selectByExample(example);
		if(selectByExample!=null && selectByExample.size()>0) {
			GemUser user = selectByExample.get(0);
			String phone = user.getPhone();
			String email = user.getEmail();
			if(GemFrameStringUtlis.isNotBlank(phone) && phone.equalsIgnoreCase(name)) {
				System.out.println("调用手机的验证码");
			}
			if(GemFrameStringUtlis.isNotBlank(email) && email.equalsIgnoreCase(name)) {
				sendMailService.sendMailService("验证码", name, vrificationCode);
			}
		}else {
			throw new GemFrameException(GemFrameErrorStatus.NOT_USER);
		}
		valueOperations.set(name, vrificationCode,60L, TimeUnit.SECONDS);
		return vrificationCode;
	}

	/**
	 * @Description: 校验验证码
	 * @param name 获取验证的手机号或邮箱
	 * @param value 验证码
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	@Override
	public String checkVrification(String name, String value) {
		String code = String.valueOf(valueOperations.get(name));
		if(GemFrameStringUtlis.isBlank(code) || code.equalsIgnoreCase(GemFrameConstant.NULL) || code.length()==0) {
			throw new GemFrameException(GemFrameErrorStatus.VRIFICATION_ERROR);
		}
		if(!value.equalsIgnoreCase(code)) {
			throw new GemFrameException(GemFrameErrorStatus.VRIFICATION_ERROR);
		}
		return name;
	}

	/**
	 * @Description:修改密码
	 * @param name 手机号或密码
	 * @param password 新密码
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	@Override
	public Integer updatePassword(String name, String password) {
		Example example = new Example(GemUser.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("userName",name);
		Criteria createCriteria2=example.createCriteria();
		createCriteria2.andEqualTo("email", name);
		example.or(createCriteria);
		example.or(createCriteria2);
		List<GemUser> selectByExample = userMapper.selectByExample(example);
		if(selectByExample!=null && selectByExample.size()>0) {
			GemUser user = selectByExample.get(0);
			String encode = GemFramePasswordUtil.encode(password);
			user.setPassWord(encode);
			return userMapper.updateByPrimaryKeySelective(user);
		}
		return null;
	}

}
