/**
 * @Title:控制器
 * @Description:数据字典管理
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

import java.util.List;

import cn.gemframe.business.domain.GemDictionary;
import cn.gemframe.business.service.GemDictionaryService;
import cn.gemframe.business.vo.GemDictionaryVo;
import cn.gemframe.config.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Title:控制器
 * @Description:数据字典管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@RestController
public class GemDictionaryController {

	@Autowired
	private GemDictionaryService dictionaryService;

	/**
	 * @Description:添加数据字典
	 * @param dictionaryVo 数据字典对象
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("dic/saveDiction")
	public ResponseEntity<ResultData> saveDiction(GemDictionaryVo dictionaryVo) {
		Long dicId=dictionaryService.saveDiction(dictionaryVo);
		return ResponseEntity.ok(ResultData.SUCCESS(dicId));
	}

	/**
	 * @Description:根据主键删除字典
	 * @param ids 主键集合
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("dic/deleteDictionById")
	public ResponseEntity<ResultData> deleteDictionById(Long[] ids) {
		Integer flag=dictionaryService.deleteDictionById(ids);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}

	/**
	 * @Description:修改数据字典
	 * @param dictionaryVo 数据字典对象
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("dic/updateDiction")
	public ResponseEntity<ResultData> updateDiction(GemDictionaryVo dictionaryVo) {
		Integer flag=dictionaryService.updateDiction(dictionaryVo);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}

	/**
	 * @Description: 根据主键查询字典详情
	 * @param id 主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("dic/findDictionById")
	public ResponseEntity<ResultData> findDictionById(Long id) {
		GemDictionary flag=dictionaryService.findDictionById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}

	/**
	 * @Description: 根据菜单主键查询子集
	 * @param id 主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@PostMapping("dic/findDictionChildrenById")
	public ResponseEntity<ResultData> findDictionChildrenById(Long id) {
		List<GemDictionary> flag=dictionaryService.findDictionChildrenById(id);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}

	/**
	 * @Description:查询字典列表
	 * @param name 名称
	 * @param code 编码
	 * @author: Ryan
	 * @date 2018年11月13日
	 */
	@PostMapping("dic/findDictionMenu")
	public ResponseEntity<ResultData> findDictionMenu(String name,String code) {
		List<GemDictionary> flag=dictionaryService.findDictionMenu(name,code);
		return ResponseEntity.ok(ResultData.SUCCESS(flag));
	}
}
