/**
 * @Title:业务对象
 * @Description:员工学历信息
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

/**
 * @Title:业务对象
 * @Description:员工学历信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
@Entity
@Table(name="gem_tab_learning")
@EqualsAndHashCode(callSuper = true)
public class GemLearningExperience extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	
	private @Column(name="degree_level",cause="学历等级",			columnDefinition = "varchar(255) COMMENT '学历等级'") String degreeLevel;
	private @Column(name="admission_time",cause="入学时间",			columnDefinition = "varchar(255) COMMENT '入学时间'") String admissionTime;
	private @Column(name="graduation_time",cause="毕业时间",		columnDefinition = "varchar(255) COMMENT '毕业时间'") String graduationTime;
	private @Column(name="school_name",cause="学校名字",			columnDefinition = "varchar(255) COMMENT '学校名字'") String schoolName;
	private @Column(name="school_profess",cause="专业",				columnDefinition = "varchar(255) COMMENT '专业'") String professional;
	private @Column(name="school_degree",cause="学位",				columnDefinition = "varchar(255) COMMENT '学位'") String degreeIn;
	private @Column(name="graduation_file",cause="毕业证书文件路径",columnDefinition = "varchar(255) COMMENT '毕业证书文件路径'") String graduationFile;
	private @Column(name="graduation_name",cause="毕业证书文件名称",columnDefinition = "varchar(255) COMMENT '毕业证书文件名称'") String graduationNmae;
	private @Column(name="degree_file",cause="学位证书文件路径",	columnDefinition = "varchar(255) COMMENT '学位证书文件路径'") String degreeFile;
	private @Column(name="degree_name",cause="学位证书文件名称",	columnDefinition = "varchar(255) COMMENT '学位证书文件名称'") String degreeName;
	private @Column(name="member_id",cause="员工主键",				columnDefinition = "bigint(20) COMMENT '员工主键'") Long memberId;
	
}
