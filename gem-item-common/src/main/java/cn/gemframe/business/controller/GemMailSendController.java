/**
 * @Title:控制器
 * @Description:邮件管理
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

import cn.gemframe.config.mail.GemFrameMailConfigurationService;
import cn.gemframe.config.mail.GemFrameSendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title:控制器
 * @Description:邮件管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemMailSendController implements GemFrameMailConfigurationService {

	@Autowired
	private GemFrameSendMailService sendMailService;

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
	 * @Description:发送邮件
	 * @param title    邮件标题
	 * @param toUser	邮件目标
	 * @param context	邮件内容
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@GetMapping("sendMail")
	public ResponseEntity<String> sendMail(String title,String toUser,String context){
		sendMailService.sendMailService(title,toUser,context);
		return ResponseEntity.ok(null);
	}


}
