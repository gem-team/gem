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
package cn.gemframe.business.service.impl;

import cn.gemframe.business.dao.GemBrowseLogsDao;
import cn.gemframe.business.domain.GemBrowseLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import cn.gemframe.business.service.GemBrowseLogsService;

/**
 * @Title:
 * @Description:
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class GemBrowseLogsServiceImpl implements GemBrowseLogsService {

	@Autowired
	private GemBrowseLogsDao browseLogsDao;

	@Override
	public void addStoreBrowseLogs(GemBrowseLogs browseLogs) {
		browseLogsDao.save(browseLogs);
	}

	@Override
	public Page<GemBrowseLogs> findStoreBrowseLogs(GemBrowseLogs browseLogs, int page, int pageSize) {
		page=page>0?page-1:0;
		PageRequest pageable = new PageRequest(page,pageSize,new Sort(Direction.DESC, "createDate"));
		Page<GemBrowseLogs> storeBrowseLogsPage = null;
		storeBrowseLogsPage=browseLogsDao.findByUserName(browseLogs.getUserName(), pageable);
		return storeBrowseLogsPage;
	}
}
