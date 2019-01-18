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
package cn.gemframe.business.service.impl;

import cn.gemframe.business.service.GemTokenCrudService;
import cn.gemframe.config.constant.GemFrameConstant;
import cn.gemframe.config.utils.GemFrameStringUtlis;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;


/**
 * @Title:
 * @Description:
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class TokenCrudServiceImpl implements GemTokenCrudService {

	@Autowired
	private TokenStore tokenStore;

	@Override
	public String deleteToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (GemFrameStringUtlis.isNoneBlank(header) && header.length() > 0) {
			String tokenValue = header.split(" ")[1];
			if (GemFrameStringUtlis.isNoneBlank(tokenValue) && tokenValue.length() > 0) {
				OAuth2AccessToken readAccessToken = tokenStore.readAccessToken(tokenValue);
				if (readAccessToken != null) {
					tokenStore.removeAccessToken(readAccessToken);
					return GemFrameConstant.SUCCESS_SATE;
				}
			}
		}
		return GemFrameConstant.FAILURE_SATE;
	}

}
