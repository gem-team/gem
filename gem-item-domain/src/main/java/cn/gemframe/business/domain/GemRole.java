/**
 * @Title:业务对象
 * @Description:角色信息
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
package cn.gemframe.business.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Title:业务对象
 * @Description:角色信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
@Entity
@Table(name="gem_tab_role")
@EqualsAndHashCode(callSuper = true)
public class GemRole extends BaseBean {

	private static final long serialVersionUID = 1L;

	private @Column(name="role_groupId",cause="所属角色组",	columnDefinition = "bigint(20)   COMMENT '所属角色组'") Long groupId;
	private @Column(name="role_name",   cause="角色名字",	columnDefinition = "varchar(255) COMMENT '角色名字'") 	String name;
	private @Column(name="role_code",   cause="角色编码",	columnDefinition = "varchar(255) COMMENT '角色编码'") 	String code;
	private @Column(name="role_sort",   cause="排序号",		columnDefinition = "int(11) 	  COMMENT '排序号'") 	Integer sortNumber;
	private @Column(name="role_cause",  cause="角色描述",	columnDefinition = "varchar(255) COMMENT '角色描述'") 	String cause;

	private @Transient(cause="是否选中") boolean selected;
}
