/**
 * @Title:
 * @Description:
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
package com.gemframe.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Description:
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class GemTaskServiceImpl implements SchedulingConfigurer {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private static final String DEFAULT_CRON = "0/5 * * * * ?";
	private String cron = DEFAULT_CRON;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(() -> {
			// 定时任务的业务逻辑
			System.out.println("动态修改定时任务cron参数，当前时间：" + dateFormat.format(new Date()));
		}, (triggerContext) -> {
			// 定时任务触发，可修改定时任务的执行周期
			CronTrigger trigger = new CronTrigger(cron);
			Date nextExecDate = trigger.nextExecutionTime(triggerContext);
			return nextExecDate;
		});
	}

	public void setCron(String cron) {
		System.out.println("当前cron=" + this.cron + "->将被改变为：" + cron);
		this.cron = cron;
	}

}