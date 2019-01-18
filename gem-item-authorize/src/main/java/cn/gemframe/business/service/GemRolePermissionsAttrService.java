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

import java.util.List;

/**
 * @Title:业务接口
 * @Description:角色和权限的关联管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemRolePermissionsAttrService {

	/**
	 * @Description:获取角色在菜单的所有属性值的id
	 * @param id 菜单主键
	 * @param roleId 角色主键
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	List<Long> findAttrIds(Long id, Long roleId);
	
	/**
	 * @Description:获取角色在菜单的所有属性值的id
	 * @param id 菜单主键
	 * @param roleId 角色主键集合
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	List<Long> findAttrIds(Long id, List<Long> roleId);

	/**
	 * @Description: 删除关联属性值
	 * @param permissid 权限主键
	 * @param roleId 角色主键
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	Integer deleteAssociatedAttr(Long permissid, Long roleId);

	/**
	 * @Description: 添加属性关联
	 * @param permissid 权限主键
	 * @param roleId 角色主键
	 * @param attrId 属性主键
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	Integer saveAssociatedAttr(Long permissid, Long roleId, Long attrId);

	/**
	 * @Description:添加角色和属性的关联关系
	 * @param roleId 角色主键集合
	 * @param attrs 属性主键集合
	 * @param permiss 权限主键
	 * @author: Ryan  
	 * @date 2018年11月13日
	 */
	Integer saveRoleAndAttrs(Long[] roleId, Long permiss, Long[] attrs);

}
