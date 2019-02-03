/**
 * @Title:业务接口
 * @Description:角色管理
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

import cn.gemframe.business.domain.GemRole;
import cn.gemframe.business.vo.GemRoleVo;
import cn.gemframe.business.vo.GemUserRoleVo;

/**
 * @Title:业务接口
 * @Description:角色管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemRoleService {
	/**
	 * @param roleVo 角色接收参数的实体
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	Integer saveRole(GemRoleVo roleVo);
	/**
	 * @Description: 根据主键删除角色
	 * @param id 角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	Integer deleteRoleById(Long id);
	/**
	 * @Description: 修改角色
	 * @param roleVo 角色接收参数的实体
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	Integer updateRole(GemRoleVo roleVo);
	/**
	 * @Description:查询角色详情
	 * @param id 和角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	GemRole findRoleById(Long id);
	/**
	 * @Description: 删除用户的角色
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	Integer deleteRoleByUserId(Long id);
	/**
	 * @Description: 添加角色和权限的关联关系
	 * @param roleId 角色主键集合
	 * @param permiss 权限主键集合
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	Integer saveRoleAndPermiss(Long[] roleId,Long[] permiss);
	/**
	 * @Description:添加角色和用户的关联关系
	 * @param userRole 角色和用户关联接收参数的实体对象
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	Integer saveUserAndRole(GemUserRoleVo userRole);
	/**
	 * @Description: 查询用户拥有的角色
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	List<GemRole> findRoleByUser(Long id);
	/**
	 * @Description: 获取角色主键集合
	 * @param id 用户主键
	 * @author: Ryan
	 * @date 2018年11月23日
	 */
	List<Long> findRoleIdsByUser(Long id);

}
