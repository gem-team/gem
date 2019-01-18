/**
 * @Title:业务对象
 * @Description:组织变更信息
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
 * @Description:组织变更信息
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
@Entity
@Table(name = "gem_tab_organ_change")
@EqualsAndHashCode(callSuper = true)
public class GemOrganizationChange extends BaseBean {

	private static final long serialVersionUID = 1L;

	private @Column(name="chang_time",cause="变更日期",	 columnDefinition = "datetime COMMENT '变更日期'") Date changeTime;
	private @Column(name="chang_number",cause="变更文号",columnDefinition = "varchar(255) COMMENT '变更文号'")String changeNumber;
	private @Column(name="file_path",cause="文件地址",   columnDefinition = "varchar(255) COMMENT '文件地址'")String filePath;
	private @Column(name="file_name",cause="文件名称",	 columnDefinition = "varchar(255) COMMENT '文件名称'")String fileName;
	private @Column(name="chang_cause",cause="原因",     columnDefinition = "varchar(255) COMMENT '原因'")String changeCause;
	private @Column(name="event_name",cause="事件名称",  columnDefinition = "varchar(255) COMMENT '事件名称'")String eventName;
	private @Column(name="organ_id",cause="部门主键",    columnDefinition = "bigint(20) COMMENT '部门主键'")Long organId;

}
