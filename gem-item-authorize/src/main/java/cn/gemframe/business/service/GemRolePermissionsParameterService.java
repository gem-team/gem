/**
 * @Title:业务接口
 * @Description:角色和权限的关联管理
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
 * @Description:角色和权限的关联管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemRolePermissionsParameterService {

	/**
	 * @Description:添加角色和参数的关联关系
	 * @param roleId 角色主键集合
	 * @param params 参数主键集合
	 * @param permiss 权限主键
	 * @author: Ryan  
	 * @date 2018年11月13日
	 */
	Integer saveRoleAndParams(Long[] roleId, Long permiss, String[] params);

}
