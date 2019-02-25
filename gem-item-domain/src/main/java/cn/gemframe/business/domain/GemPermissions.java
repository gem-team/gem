/**
 * @Title:业务对象
 * @Description:权限信息
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
import java.util.List;

/**
 * @Title:业务对象
 * @Description:权限信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
@Entity
@Table(name="gem_tab_permissions")
@EqualsAndHashCode(callSuper = true)
public class GemPermissions extends BaseBean {

	private static final long serialVersionUID = 1L;

	private @Column(name="per_parentId",cause="权限的父级",	columnDefinition = "bigint(20) COMMENT '权限的父级'") 	Long parentId;
	private @Column(name="per_name",	cause="权限名字",	columnDefinition = "varchar(255) COMMENT '权限名字'") 	String name;
	private @Column(name="per_path",	cause="资源路径",	columnDefinition = "varchar(255) COMMENT '资源路径'") 	String path;
	private @Column(name="per_icon",	cause="图标",		columnDefinition = "varchar(255) COMMENT '图标'")		String icon;
	private @Column(name="per_code",	cause="权限编码",	columnDefinition = "varchar(255) COMMENT '权限编码'")	String code;
	private @Column(name="per_level",	cause="权限的级别",	columnDefinition = "int(11) COMMENT '权限的级别'") 		Integer levels;
	private @Column(name="per_sort",	cause="排序号",		columnDefinition = "int(11) COMMENT '排序号'") 			Integer sortNumber;
	private @Column(name="per_menus_type",cause="是否是菜单0为菜单1按钮",columnDefinition = "int(11) COMMENT '是否是菜单0为菜单1按钮'") Integer menusType;
	private @Column(name="per_cause",	cause="权限描述",	columnDefinition = "varchar(255) COMMENT '权限描述'") 	String cause;

	private @Transient(cause="权限子集") List<GemPermissions> children;
	private @Transient(cause="是否选中") boolean selected;
}
