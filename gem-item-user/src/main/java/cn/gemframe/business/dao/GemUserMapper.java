/**
 * @Title:mapper层
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
package cn.gemframe.business.dao;

import java.util.List;
import java.util.Map;

import cn.gemframe.business.domain.GemUser;
import cn.gemframe.config.mapper.GemFrameMapper;

/**
 * @Title:mapper层
 * @Description:用户信息管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemUserMapper extends GemFrameMapper<GemUser> {

	/**
	 * @Description:条件查询用户
	 * @param hashMap 返回数据数据
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	List<GemUser> findUserList(Map<String, Object> hashMap);

	/**
	 * @Description:根据角色查询用户
	 * @param hashMap 返回数据数据
	 * @author: Ryan  
	 * @date 2018年11月10日
	 */
	List<GemUser> findUserListByRoleId(Map<String, Object> hashMap);

}
