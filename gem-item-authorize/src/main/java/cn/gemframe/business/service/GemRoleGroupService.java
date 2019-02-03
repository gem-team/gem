/**
 * @Title:业务接口
 * @Description:角色组管理
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

import java.util.List;

import cn.gemframe.business.domain.GemRoleGroup;
import cn.gemframe.business.vo.GemRoleGroupVo;

/**
 * @Title:业务接口
 * @Description:角色组管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemRoleGroupService {

	/**
	 * @Description:添加角色组
	 * @param roleGroupVo 接收参数的实体对象
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	Integer saveRoleGroup(GemRoleGroupVo roleGroupVo);

	/**
	 * @Description:删除角色组
	 * @param id 主键
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	Integer deleteRoleGroup(Long id);

	/**
	 * @Description:修改角色组
	 * @param roleGroupVo 接收参数的实体对象
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	Integer updateRoleGroup(GemRoleGroupVo roleGroupVo);

	/**
	 * @Description:查询角色组详情
	 * @param id 主键
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	GemRoleGroup findRoleGroupById(Long id);

	/**
	 * @Description:查询角色组以及所有角色（组成tree结构）
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	List<GemRoleGroup> findGroupRoleList();

}
