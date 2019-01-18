/**
 * @Title:业务对象
 * @Description:用户信息
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @Title:业务对象
 * @Description:用户信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
@Entity
@Table(name="gem_tab_user")
@EqualsAndHashCode(callSuper = true)
public class GemUser extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	
	private @Column(name="user_name",cause="用户名",			columnDefinition = "varchar(255) COMMENT '用户名'")				String userName;
	@JsonIgnore
	private @Column(name="pass_word",cause="密码",				columnDefinition = "varchar(255) COMMENT '密码'") 				String passWord;
	private @Column(name="member_name",cause="姓名",			columnDefinition = "varchar(255) COMMENT '姓名'") 				String memberName;
	private @Column(name="states",cause="状态 0 禁用，1启用",	columnDefinition = "varchar(255) COMMENT '状态 0 禁用，1启用'") Integer states;
	private @Column(name="descri",cause="备注",					columnDefinition = "varchar(255) COMMENT '备注'") 				String descri;
	private @Column(name="valid_time",cause="生效时间",			columnDefinition = "datetime COMMENT '生效时间'") 				Date validTime;
	private @Column(name="failure_time",cause="失效时间",		columnDefinition = "datetime COMMENT '失效时间'") 				Date failureTime;
	private @Column(name="phone",cause="手机号",				columnDefinition = "varchar(255) COMMENT '手机号'") 			String phone;
	private @Column(name="email",cause="邮箱",					columnDefinition = "varchar(255) COMMENT '邮箱'") 				String email;
	private @Column(name="member_id",cause="员工id",			columnDefinition = "varchar(20) COMMENT '员工id'") 				String memberId;
	private @Column(name="login_time",cause="最后登陆时间",		columnDefinition = "datetime COMMENT '最后登陆时间'") 			Date loginTime;
	private @Column(name="admin_type",cause="1代表是超级管理员",columnDefinition = "varchar(11) COMMENT '1代表是超级管理员'") 	Integer adminType;

	private @Transient(cause="人员信息") GemMember member;
}
