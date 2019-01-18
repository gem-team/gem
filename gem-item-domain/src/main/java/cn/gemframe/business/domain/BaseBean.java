/**
 * @Title:业务对象
 * @Description:业务对象的基类
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

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;


/**
 * @Title:业务对象
 * @Description:业务对象的基类
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Setter
@Getter
@MappedSuperclass
public class BaseBean implements Serializable {
	private static final long serialVersionUID = 8107925410233983481L;
	@Id
	private @Column(name="id",cause="主键",				columnDefinition = "varchar(20) COMMENT '主键'") Long id;
	private @Column(name="create_time",cause="创建时间",columnDefinition = "datetime COMMENT '创建时间'") Date createDate;
	private @Column(name="update_time",cause="修改时间",columnDefinition = "datetime COMMENT '修改时间'") Date updateDate;
}
