/**
 * @Title:业务对象
 * @Description:组织信息
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
import java.util.Date;
import java.util.List;

/**
 * @Title:业务对象
 * @Description:组织信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
@Entity
@Table(name="gem_tab_organ")
@EqualsAndHashCode(callSuper = true)
public class GemOrganization extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	
	private @Column(name="organ_name",cause="部门名称",			columnDefinition = "varchar(255) COMMENT '部门名称'") String organName;
	private @Column(name="organ_unit",cause="单位简称",			columnDefinition = "varchar(255) COMMENT '单位简称'") String unitName;
	private @Column(name="organ_levels",cause="部门级别",		columnDefinition = "int(11) COMMENT '部门级别'") Integer levels;
	private @Column(name="valid_time",cause="生效日期",			columnDefinition = "datetime COMMENT '生效日期'") Date validTime;
	private @Column(name="organ_type",cause="部门类型",			columnDefinition = "varchar(255) COMMENT '部门类型'") String organType;
	private @Column(name="organ_code",cause="机构代码",		 	columnDefinition = "varchar(255) COMMENT '机构代码'") String organCode;
	private @Column(name="approval_unit",cause="批准成立单位",	columnDefinition = "varchar(255) COMMENT '批准成立单位'") String approvalUnit;
	private @Column(name="set_number",cause="成立文号",			columnDefinition = "varchar(255) COMMENT '成立文号'") String setNumber;
	private @Column(name="approval_time",cause="批准成立日期",	columnDefinition = "datetime COMMENT '批准成立日期'") Date approvalTime;
	private @Column(name="compile_number",cause="编织数",		columnDefinition = "varchar(255) COMMENT '编织数'") String compileNumber;
	private @Column(name="organ_intrduc",cause="简介",			columnDefinition = "varchar(255) COMMENT '简介'") String introductions;
	private @Column(name="organ_cause",cause="备注",			columnDefinition = "varchar(255) COMMENT '备注'") String cause;
	private @Column(name="parent_id",cause="父级",				columnDefinition = "bigint(20) COMMENT '父级'") Long parentId;
	
	private @Transient(cause="组织机构子集") List<GemOrganization> childs;


	public GemOrganization() {
	}
}
