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

	private @Column(name="organ_parentId",cause="父级",			columnDefinition = "bigint(20) COMMENT '父级'") 	  Long parentId;
	private @Column(name="organ_name",cause="部门名称",			columnDefinition = "varchar(255) COMMENT '部门名称'") String organName;
	private @Column(name="organ_short_name",cause="部门简称",			columnDefinition = "varchar(255) COMMENT '部门简称'") String organShortName;
	private @Column(name="organ_sort",cause="排序号",			columnDefinition = "int(11) COMMENT '排序号'") 		  Integer sortNumber;
	private @Column(name="organ_cause",cause="备注",			columnDefinition = "varchar(255) COMMENT '备注'") 	  String cause;

	private @Transient(cause="组织机构子集") List<GemOrganization> childs;

	public GemOrganization() {
	}
}
