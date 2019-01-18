/**
 * @Title:业务对象
 * @Description:用户角色关联信息
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
import java.util.Date;

/**
 * @Title:业务对象
 * @Description:用户角色关联信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
@Entity
@Table(name="gem_tab_user_role")
@EqualsAndHashCode(callSuper = true)
public class GemUserRole extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	
	private @Column(name="user_id",cause="用户主键",	 columnDefinition = "bigint(20) COMMENT '用户主键'") Long userId;
	private @Column(name="role_id",cause="角色主键",	 columnDefinition = "bigint(20) COMMENT '角色主键'") Long roleId;
	private @Column(name="valid_time",cause="生效时间",	 columnDefinition = "datetime COMMENT '生效时间'") Date validTime;
	private @Column(name="failure_time",cause="失效时间",columnDefinition = "datetime COMMENT '失效时间'") Date failureTime;
}
