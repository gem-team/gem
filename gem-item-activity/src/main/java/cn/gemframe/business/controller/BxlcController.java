/**
 * @Title:控制器
 * @Description:流程管理
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

import cn.gemframe.business.domain.ProcessVersion;
import cn.gemframe.business.domain.TaskDetails;
import cn.gemframe.business.service.BxlcTaskListeningImpl;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Title:控制器
 * @Description:流程管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class BxlcController {

	@Autowired
	private BxlcTaskListeningImpl bxlcTaskListeningImpl;

	/**
	 * @Description:发布流程
	 * @param type 流程的key
	 * @author: Ryan
	 * @date 2018年11月18日
	 */
	@GetMapping("bxlc/deployment")
	public ResponseEntity<ResultData> deployment(String type){
		String flag=bxlcTaskListeningImpl.deployment(type);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}

	/**
	 * @Description:流程列表
	 * @author: Ryan
	 * @date 2018年11月18日
	 */
	@GetMapping("bxlc/findProcessList")
	public ResponseEntity<ResultData> findProcessList(){
		List<ProcessVersion> flag=bxlcTaskListeningImpl.findProcessList();
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}

	/**
	 * @Description:删除流程
	 * @param id 流程主键
	 * @author: Ryan
	 * @date 2018年11月18日
	 */
	@GetMapping("bxlc/deletProcess")
	public ResponseEntity<ResultData> deletProcess(String id){
		String flag=bxlcTaskListeningImpl.deletProcess(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}

	/**
	 * @Description:填写申请的表单
	 * @param rmb 报销金额
	 * @param key 流程的key
	 * @author: Ryan
	 * @date 2018年11月18日
	 */
	@GetMapping("bxlc/saveBxlcForm")
	public ResponseEntity<ResultData> saveBxlcForm(String rmb,String key){
		String flag=bxlcTaskListeningImpl.saveBxlcForm(rmb,key);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}

	/**
	 * @Description:流程列表
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月18日
	 */
	@GetMapping("bxlc/findTaskList")
	public ResponseEntity<ResultData> findTaskList(String id){
		List<TaskDetails> flag=bxlcTaskListeningImpl.findTaskList(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}

	/**
	 * @Description:执行任务
	 * @param id 任务主键
	 * @param cause 备注
	 * @author: Ryan
	 * @date 2018年11月18日
	 */
	@GetMapping("bxlc/performTask")
	public ResponseEntity<ResultData> performTask(String id,String cause){
		String flag=bxlcTaskListeningImpl.performTask(id,cause);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
}
