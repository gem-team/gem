/**
 * @Title:服务启动类
 * @Description:服务启动
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
package cn.gemframe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Title:服务启动类
 * @Description:服务启动
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@EnableEurekaClient
@SpringBootApplication
@MapperScan("cn.gemframe.business.dao")
public class AuthorizeServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthorizeServerApplication.class, args);
		System.out.println("---AuthorizeServerApplication：启动成功---");
	}
}
