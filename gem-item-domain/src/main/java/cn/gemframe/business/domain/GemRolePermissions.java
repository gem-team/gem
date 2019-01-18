/**
 * @Title:业务对象
 * @Description:角色权限关联信息
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
 * @Description:角色权限关联信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
@Entity
@Table(name="gem_tab_role_permis")
@EqualsAndHashCode(callSuper = true)
public class GemRolePermissions extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	
	private @Column(name="role_id",cause="角色主键",columnDefinition = "bigint(20) COMMENT '角色主键'") Long roleId;
	private @Column(name="per_id",cause="权限主键",	columnDefinition = "bigint(20) COMMENT '权限主键'") Long permisId;
}
