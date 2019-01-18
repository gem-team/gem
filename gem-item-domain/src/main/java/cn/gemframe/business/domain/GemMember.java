/**
 * @Title:业务对象
 * @Description:员工信息
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
 * @Description:员工信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
@Entity
@Table(name="gem_tab_member")
@EqualsAndHashCode(callSuper = true)
public class GemMember extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	
	private @Column(name="member_name",cause="姓名",				columnDefinition = "varchar(255) COMMENT '姓名'") String memberName;
	private @Column(name="sex",cause="性别",						columnDefinition = "int(11) COMMENT '性别'") Integer sex;
	private @Column(name="birthday",cause="生日",					columnDefinition = "varchar(255) COMMENT '生日'") String birthday;
	private @Column(name="national",cause="民族",					columnDefinition = "varchar(255) COMMENT '民族'") String national;
	private @Column(name="address",cause="地址",					columnDefinition = "varchar(255) COMMENT '地址'") String address;
	private @Column(name="states",cause="状态 0 离职，1在职",		columnDefinition = "int(11) COMMENT '状态 0 离职，1在职'") Integer states;
	private @Column(name="descri",cause="备注",						columnDefinition = "varchar(255) COMMENT '备注'") String descri;
	private @Column(name="start_time",cause="生效时间",				columnDefinition = "datetime COMMENT '生效时间'") Date starTime;
	private @Column(name="end_time",cause="失效时间",				columnDefinition = "datetime COMMENT '失效时间'") Date endTime;
	private @Column(name="phone",cause="手机号",					columnDefinition = "varchar(255) COMMENT '手机号'") String phone;
	private @Column(name="card",cause="证件号",						columnDefinition = "varchar(255) COMMENT '证件号'") String card;
	private @Column(name="card_type",cause="证件类型 1 身份证 2护照",columnDefinition = "int(11) COMMENT '证件类型 1 身份证 2护照'") Integer cardType;
	private @Column(name="work_number",cause="工号",				columnDefinition = "varchar(255) COMMENT '工号'") String workNumber;
	private @Column(name="position",cause="职位",					columnDefinition = "varchar(255) COMMENT '职位'") String position;
	private @Column(name="company_name",cause="公司名称",			columnDefinition = "varchar(255) COMMENT '公司名称'") String companyName;
	private @Column(name="organ_id",cause="部门主键",				columnDefinition = "varchar(255) COMMENT '部门主键'") String organId;
	
	private @Transient(cause="部门名称") String organName;
	private @Transient(cause="学习经历集合") List<GemLearningExperience> learns;
}
