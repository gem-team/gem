/**
 * @Title:控制器
 * @Description:流程图管理
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
package cn.gemframe.business.controller;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import cn.gemframe.business.service.BxlcTaskListeningImpl;
import cn.gemframe.config.exception.GemFrameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @Title:控制器
 * @Description:流程图管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Controller
public class ImageProcessController {

	@Autowired
	private BxlcTaskListeningImpl bxlcTaskListeningImpl;

	/**
	 * @Description:查看流程图
	 * @param id 流程主键
	 * @author: Ryan
	 * @date 2018年11月18日
	 */
	@GetMapping("image")
	public void image(HttpServletResponse response, String id) {
		InputStream pngLn =null;
		ServletOutputStream outputStream =null;
		try {
			pngLn = bxlcTaskListeningImpl.image(id);
			response.setContentType("multipart/form-data");
			outputStream = response.getOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024 * 10];
			while ((len = pngLn.read(buffer)) != -1){
				outputStream.write(buffer,0,len);
			}
			outputStream.flush();
		} catch (Exception e) {
			throw new GemFrameException("图片读取异常");
		}finally {
			try {
				if(pngLn!=null) {
					pngLn.close();
				}
				if(outputStream!=null) {
					outputStream.close();
				}
			} catch (Exception e) {
				throw new GemFrameException("IO关闭异常");
			}
		}
	}
}
