/**
 * @Title:业务对象
 * @Description:参数信息
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
 * @Description:参数信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
@Entity
@Table(name="gem_tab_permiss_param")
@EqualsAndHashCode(callSuper = true)
public class GemPermissionsParameter extends BaseBean {
	
	private static final long serialVersionUID = 1L;
	
	private @Column(name="param_name",cause="参数名字",							columnDefinition = "varchar(255) COMMENT '参数名字'") String name;
	private @Column(name="param_key",cause="参数的key",							columnDefinition = "varchar(255) COMMENT '参数的key'") String paramKey;
	private @Column(name="param_type",cause="参数的类型",						columnDefinition = "varchar(255) COMMENT '参数的类型'") String paramType;
	private @Column(name="param_value",cause="参数的值",						columnDefinition = "varchar(255) COMMENT '参数的值'") String paramValue;
	private @Column(name="param_where",cause="条件值0 等于 1大于  2 小于  3包含",columnDefinition = "varchar(255) COMMENT '条件值0 等于 1大于  2 小于  3包含'") String paramWhere;
	private @Column(name="param_code",cause="参数的编码",						columnDefinition = "varchar(255) COMMENT '参数的编码'") String code;
	private @Column(name="per_id",cause="权限主键",								columnDefinition = "bigint(20) COMMENT '权限主键'") Long permissId;
	
}
