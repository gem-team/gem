/**
 * @Title:业务接口
 * @Description:参数管理
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

import cn.gemframe.business.domain.GemPermissions;
import cn.gemframe.business.vo.GemPermissionsVo;

import java.util.List;

/**
 * @Title:业务接口
 * @Description:参数管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemPermissionsService {

	/**
	 * @Description: 根据角色获取权限菜单
	 * @param id 角色主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	List<GemPermissions> findPermissByRoleId(Long id);


	/**
	 * @Description: 根据角色获取权限按钮
	 * @param id 角色主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	List<Long> findPermissMunByRoleId(Long id);

	/**
	 * @Description: 根据用户获取权限菜单
	 * @param id 用户主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	List<GemPermissions> findPermissByUserId(Long id);

	/**
	 * @Description:查看权限详情
	 * @param id 权限主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	GemPermissions findPermissById(Long id);

	/**
	 * @Description:修改权限
	 * @param permissionsVo 权限接受参数实体
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	Integer updatePermiss(GemPermissionsVo permissionsVo);

	/**
	 * @Description:添加权限
	 * @param  permissionsVo 权限接受参数实体
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	Integer savePermiss(GemPermissionsVo permissionsVo);

	/**
	 * @Description:删除权限
	 * @param id 权限主键集合
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	Integer deletePermissById(Long[] id);

	/**
	 * @Description: 查询所有权限菜单
	 * @param ids 权限主键集合
	 * @param type 0查询拥有的权限，不拥有的不显示 ，1查询所有，字段标识是否拥有
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	List<GemPermissions> findAllMenu(List<Long> ids, String type);

	/**
	 * @Description:查看菜单下的子权限
	 * @param id 菜单主键
	 * @param roleId 角色主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	List<GemPermissions> findPermissByParentId(Long id, Long roleId);

	List<GemPermissions> findUserPermiss(Long id);

}
