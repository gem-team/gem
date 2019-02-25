/**
 * @Title:业务接口
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
package cn.gemframe.business.service;

import java.util.List;

import cn.gemframe.business.domain.GemUser;
import cn.gemframe.business.vo.GemUserVo;

/**
 * @Title:业务接口
 * @Description:用户信息管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemUserService {

	/**
	 * @Description: 添加用户
	 * @param userVo 用户参数实体
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	Integer saveUser(GemUserVo userVo);

	List<GemUser> findUserList(GemUserVo userVo, Integer pageNum, Integer pageSize);

	/**
	 * @Description:删除用户
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	Integer deleteUserById(Long id);

	/**
	 * @Description: 修改用户
	 * @param userVo 用户参数实体
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	Integer updateUser(GemUserVo userVo);

	/**
	 * @Description:根据角色查询用户
	 * @param roleId 角色主键
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的数据
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	List<GemUser> findUserListByRoleId(Long roleId, Integer pageNum, Integer pageSize);

	/**
	 * @Description:用户详情
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月10日
	 */
	GemUser findUserById(Long id);

}
