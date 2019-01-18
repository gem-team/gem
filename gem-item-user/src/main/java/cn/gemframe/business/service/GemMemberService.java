/**
 * @Title:业务接口
 * @Description:员工信息管理
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

import cn.gemframe.business.domain.GemMember;
import cn.gemframe.business.vo.GemMemberVo;

/**
 * @Title:业务接口
 * @Description:员工信息管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
public interface GemMemberService {

	/**
	 * @Description: 添加员工
	 * @param memberVo 员工参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	Integer saveMember(GemMemberVo memberVo);

	/**
	 * @Description:删除员工
	 * @param ids 员工主键集合
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	Integer deleteMemberById(Long[] ids);

	/**
	 * @Description: 修改员工
	 * @param memberVo 员工参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	Integer updateMember(GemMemberVo memberVo);

	/**
	 * @Description: 员工详情
	 * @param memberVo 员工参数接受对象
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	GemMember findMemberById(Long id);

	/**
	 * @Description: 员工列表
	 * @param memberVo 员工参数接受对象
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的数据
	 * @author: Ryan  
	 * @date 2018年11月22日
	 */
	List<GemMember> findMemberList(GemMemberVo memberVo, Integer pageNum, Integer pageSize);

}
