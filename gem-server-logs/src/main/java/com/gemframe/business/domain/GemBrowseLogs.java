/**
 * @Title:
 * @Description:
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
package com.gemframe.business.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * @Title:
 * @Description:
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Data
public class GemBrowseLogs implements Serializable {
	private static final long serialVersionUID = -5453735788732292301L;
	
	/**
	 * 主键
	 */
	@Id
	private Long id;
	
	/**
	 * 用户主键
	 */
	private String userId;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 方法
	 */
	private String methodUrl;
	
	/**
	 * 方法名字
	 */
	private String methodName;
	
	/**
	 * 方法类型
	 */
	private String methodType;
	
	/**
	 * Ip地址
	 */
	private String ip;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
}
