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
package cn.gemframe.hystrix;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Title:
 * @Description:
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Component
public class GemFrameHystrixResponse implements ClientHttpResponse {

	@Autowired
	private ObjectMapper objectMapper;
	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	@Override
	public InputStream getBody() throws IOException {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("state", httpStatus.value());
		hashMap.put("message", "服务异常");
		String writeValueAsString = objectMapper.writeValueAsString(hashMap);
		return new ByteArrayInputStream(writeValueAsString.getBytes("UTF-8"));
	}

	@Override
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return headers;
	}

	@Override
	public HttpStatus getStatusCode() throws IOException {
		return httpStatus;
	}

	@Override
	public int getRawStatusCode() throws IOException {
		return httpStatus.value();
	}

	@Override
	public String getStatusText() throws IOException {
		return httpStatus.name();
	}

	@Override
	public void close() {

	}

}
