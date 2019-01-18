/**
 * @Title:业务接口
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
package cn.gemframe.business.service;

/**
 * @Title:业务接口
 * @Description:验证信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemRetrieveService {

	/**
	 * @Description:发送验证码
	 * @param name 手机号或者邮箱
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	String sendVrification(String name);

	/**
	 * @Description: 校验验证码
	 * @param name 获取验证的手机号或邮箱
	 * @param value 验证码
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	String checkVrification(String name, String value);

	/**
	 * @Description:修改密码
	 * @param name 手机号或密码
	 * @param password 新密码
	 * @author: Ryan  
	 * @date 2018年11月6日
	 */
	Integer updatePassword(String name, String password);

}
