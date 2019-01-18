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
package com.gemframe.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gemframe.business.domain.GemBrowseLogs;
import com.gemframe.business.service.GemBrowseLogsService;

/**
 * @Title:
 * @Description:
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemBrowseLogsController {
	
	@Autowired
	private GemBrowseLogsService storeBrowseLogsService;

	@GetMapping("findStoreBrowseLogs")
	public ResponseEntity<Page<GemBrowseLogs>> findStoreBrowseLogs(GemBrowseLogs browseLogs, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="pageSize", defaultValue="10") int pageSize) {
		Page<GemBrowseLogs> list=storeBrowseLogsService.findStoreBrowseLogs(browseLogs,page,pageSize);
		return ResponseEntity.ok(list);
	}
}
