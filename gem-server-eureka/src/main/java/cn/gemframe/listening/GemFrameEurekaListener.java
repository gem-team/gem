/**
 * @Title:Eureka
 * @Description:Eureka
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
package cn.gemframe.listening;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Title:Eureka
 * @Description:Eureka
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Component
public class GemFrameEurekaListener {

    @EventListener //服务下线事件
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        //服务断线事件
        String appName = eurekaInstanceCanceledEvent.getAppName();
        String serverId = eurekaInstanceCanceledEvent.getServerId();
        System.out.println("服务断线事件:"+appName+":"+serverId);
    }

    @EventListener //服务注册事件
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.out.println("服务注册事件:"+instanceInfo.getAppName()+":"+instanceInfo.getIPAddr()+":"+instanceInfo.getPort());
    }

	@EventListener // 服务续约事件
	public void listen(EurekaInstanceRenewedEvent event) {
		String appName = event.getAppName();
		String serverId = event.getServerId();
		System.out.println("服务续约事件:" + appName + ":" + serverId);
	}

    @EventListener //注册中心启动事件
    public void listen(EurekaRegistryAvailableEvent event) {
    	System.out.println("注册中心启动事件:"+event);
    }

    @EventListener //Server启动事件
    public void listen(EurekaServerStartedEvent event) {
    	System.out.println("Server启动事件:"+event);
    }
}
