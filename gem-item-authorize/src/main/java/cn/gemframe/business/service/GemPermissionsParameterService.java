/**
 * @Title:业务接口
 * @Description:权限管理
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

import cn.gemframe.business.domain.GemPermissionsParameter;
import cn.gemframe.business.domain.GemRolePermissionsParameter;
import cn.gemframe.business.vo.GemPermissionsParameterVo;

/**
 * @Title:业务接口
 * @Description:权限管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemPermissionsParameterService {

	/**
	 * @Description:添加权限的参数值
	 * @param permissionsParameterVo 权限的参数对象
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	Integer savePermissParam(GemPermissionsParameterVo permissionsParameterVo);

	/**
	 * @Description:删除权限的参数
	 * @param id 权限的参数值主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	Integer deletePermissParamById(Long[] id);

	/**
	 * @Description:修改权限的参数值
	 * @param permissionsParameterVo 权限的参数对象
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	Integer updatePermissParam(GemPermissionsParameterVo permissionsParameterVo);

	/**
	 * @Description:根据主键查询权限的属性值
	 * @param id 权限的属性值主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	GemPermissionsParameter findPermissParamById(Long id);

	/**
	 * @Description:根据权限主键查询属性值
	 * @param id 菜单主键
	 * @param roleId 角色主键
	 * @author: Ryan  
	 * @date 2018年11月12日
	 */
	List<GemRolePermissionsParameter> findParamByPermissId(Long id, Long roleId);
	
	List<GemRolePermissionsParameter> findParamByPermissIds(Long id, List<Long> roleId);

	/**
	 * @Description:查询权限的参数值列表
	 * @param id 权限主键
	 * @author: Ryan  
	 * @date 2018年11月5日
	 */
	List<GemPermissionsParameter> findPermissParamList(Long id);

}
