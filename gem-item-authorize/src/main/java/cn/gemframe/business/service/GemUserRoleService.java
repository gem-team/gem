/**
 * @Title:业务接口
 * @Description:用户角色管理
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

import cn.gemframe.business.domain.GemUserRole;

/**
 * @Title:业务接口
 * @Description:用户角色管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemUserRoleService {

	/**
	 * @Description:根据角色删除关联关系
	 * @param id 角色主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	Integer deleteByRoleId(Long id);

	/**
	 * @Description:根据用户删除关联关系
	 * @param id 用户主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	Integer deleteRoleByUserId(Long id);

	/**
	 * @Description: 添加用户和角色的关联关系
	 * @param userRole 角色和用户中间表的实体
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	Integer saveUserAndRole(GemUserRole userRole);

}
