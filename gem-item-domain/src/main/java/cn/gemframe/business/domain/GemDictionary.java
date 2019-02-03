/**
 * @Title:业务对象
 * @Description:词典信息
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
 * @Description:词典信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
@Entity
@Table(name="gem_tab_dictionary")
@EqualsAndHashCode(callSuper = true)
public class GemDictionary extends BaseBean {

	private static final long serialVersionUID = 1L;

	private @Column(name="dic_parentId",cause="父级",	columnDefinition = "bigint(20) COMMENT '父级'") Long parentId;
	private @Column(name="dic_name",cause="名字",	columnDefinition = "varchar(255) COMMENT '名字'") String name;
	private @Column(name="dic_code",cause="编码",	columnDefinition = "varchar(255) COMMENT '编码'") String code;
	private @Column(name="dic_level",cause="级别",	columnDefinition = "int(11) COMMENT '级别'") Integer levels;
	private @Column(name="dic_sort",cause="排序号",	columnDefinition = "int(11) COMMENT '排序号'") Integer sortNumber;
	private @Column(name="dic_value",cause="值",	columnDefinition = "varchar(255) COMMENT '值'") String dicValue;
	private @Column(name="dic_type",cause="类型:1组，2字典，3字典项",	columnDefinition = "varchar(255) COMMENT '类型:1组，2字典，3字典项'") String dicType;
	private @Column(name="dic_cause",cause="备注",	columnDefinition = "varchar(255) COMMENT '备注'") String cause;

	private @Transient(cause="字典子集") List<GemDictionary> children;

}
