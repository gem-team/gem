/**
 * @Title:业务接口
 * @Description:部门管理
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

import cn.gemframe.business.domain.GemOrganization;
import cn.gemframe.business.vo.GemOrganizationVo;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Title:业务接口
 * @Description:组织管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemOrganizationService {

	/**
	 * @Description:添加组织机构
	 * @param organizationVo 组织机构对象
	 * @author: Ryan
	 * @date 2018年11月6日
	 */
	Integer saveOrgan(GemOrganizationVo organizationVo);

	/**
	 * @Description:删除组织机构
	 * @param ids 组织机构主键集合
	 * @author: Ryan
	 * @date 2018年11月6日
	 */
	Integer deletOrganById(Long[] ids);

	/**
	 * @Description:修改组织机构
	 * @param organizationVo 组织机构对象
	 * @author: Ryan
	 * @date 2018年11月6日
	 */
	Integer updateOrgan(GemOrganizationVo organizationVo, MultipartFile file);

	/**
	 * @Description:查询组织机构详情
	 * @param id 组织机构主键
	 * @author: Ryan
	 * @date 2018年11月6日
	 */
	GemOrganization findOrganById(Long id);

	/**
	 * @Description:查询组织机构树
	 * @param name 名称
	 * @param time 事件
	 * @author: Ryan
	 * @date 2018年11月22日
	 */
	List<GemOrganization> findOrganZtree(String name, String time);


}
