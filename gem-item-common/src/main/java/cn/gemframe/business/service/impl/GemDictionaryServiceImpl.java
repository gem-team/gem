/**
 * @Title:业务实现
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
package cn.gemframe.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.gemframe.business.domain.GemDictionary;
import cn.gemframe.business.vo.GemDictionaryVo;
import cn.gemframe.config.exception.GemFrameException;
import cn.gemframe.config.exception.status.GemFrameErrorStatus;
import cn.gemframe.config.utils.GemFrameIdUtlis;
import cn.gemframe.config.utils.GemFrameJsonUtils;
import cn.gemframe.config.utils.GemFrameStringUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gemframe.business.dao.GemDictionaryMapper;
import cn.gemframe.business.service.GemDictionaryService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @Title:业务实现
 * @Description:数据字典管理
 * @author Ryan
 * @date 2018-11-1 16:06:06
 * @version V1.0
 */
@Service
public class GemDictionaryServiceImpl implements GemDictionaryService {

	@Autowired
	private GemDictionaryMapper dictionaryMapper;

	/**
	 * @Description:添加数据字典
	 * @param dictionaryVo 数据字典对象
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer saveDiction(GemDictionaryVo dictionaryVo) {
		dictionaryVo.setCreateDate(new Date());
		dictionaryVo.setUpdateDate(new Date());
        GemDictionary dictionary = GemFrameJsonUtils.classToClass(dictionaryVo, GemDictionary.class);
		dictionary.setId(GemFrameIdUtlis.Id());
		return dictionaryMapper.insert(dictionary);
	}

	/**
	 * @Description:根据主键删除字典
	 * @param ids 主键集合
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer deleteDictionById(Long[] ids) {
		if(ids!=null && ids.length>0) {
			for (Long id : ids) {
				dictionaryMapper.deleteByPrimaryKey(id);
			}
		}
		return null;
	}

	/**
	 * @Description:修改数据字典
	 * @param dictionaryVo 数据字典对象
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public Integer updateDiction(GemDictionaryVo dictionaryVo) {
		dictionaryVo.setUpdateDate(new Date());
        GemDictionary dictionary = GemFrameJsonUtils.classToClass(dictionaryVo, GemDictionary.class);
		return dictionaryMapper.updateByPrimaryKeySelective(dictionary);
	}

	/**
	 * @Description: 根据主键查询字典详情
	 * @param id 主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public GemDictionary findDictionById(Long id) {
		return dictionaryMapper.selectByPrimaryKey(id);
	}

	/**
	 * @Description: 根据主键查询子集
	 * @param id 主键
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	@Override
	public List<GemDictionary> findDictionChildsById(Long id) {
		if(id==null) {
			throw new GemFrameException(GemFrameErrorStatus.PARAMETER_ERROR);
		}
		Example example = new Example(GemDictionary.class);
		example.createCriteria().andEqualTo("parentId", id).andEqualTo("dicType","3");
		return dictionaryMapper.selectByExample(example);
	}

	/**
	 * @Description:查询字典列表
	 * @param name 名称
	 * @param code 编码
	 * @author: Ryan
	 * @date 2018年11月13日
	 */
	@Override
	public List<GemDictionary> findDictionMenu(String name, String code) {
		Example example = new Example(GemDictionary.class);
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEqualTo("parentId",-1);
		List<String> dicTypeList = new ArrayList<>();
		dicTypeList.add("1");
		dicTypeList.add("2");
		createCriteria.andIn("dicType",dicTypeList);
		if(GemFrameStringUtlis.isNotBlank(name) && !name.equalsIgnoreCase("null") && name.length()>0) {
			createCriteria.andLike("name","%"+name+"%");
		}
		if(GemFrameStringUtlis.isNotBlank(code) && !code.equalsIgnoreCase("null") && code.length()>0) {
			createCriteria.andLike("code","%"+code+"%");
		}
		example.setOrderByClause("dic_sort asc");
		List<GemDictionary> selectByExample = dictionaryMapper.selectByExample(example);
		return getDicChildsList(selectByExample,name,code);
	}

	/**
	 * @Description:递归遍历所有的字典组
	 * @param list 字典数据集合
	 * @author: Ryan
	 * @date 2018年11月5日
	 */
	public List<GemDictionary> getDicChildsList(List<GemDictionary> list,String name, String code) {
		if(list!=null && list.size()>0) {
			for (GemDictionary dictionary : list) {
				Example example = new Example(GemDictionary.class);
				Criteria createCriteria = example.createCriteria();
				createCriteria.andEqualTo("parentId",dictionary.getId());
				List<String> dicTypeList = new ArrayList<>();
				dicTypeList.add("1");
				dicTypeList.add("2");
				createCriteria.andIn("dicType",dicTypeList);
				if(GemFrameStringUtlis.isNotBlank(name) && !name.equalsIgnoreCase("null") && name.length()>0) {
					createCriteria.andLike("name","%"+name+"%");
				}
				if(GemFrameStringUtlis.isNotBlank(code) && !code.equalsIgnoreCase("null") && code.length()>0) {
					createCriteria.andLike("code","%"+code+"%");
				}
				example.setOrderByClause("dic_sort asc");
				List<GemDictionary> selectByExample = dictionaryMapper.selectByExample(example);
				dictionary.setChildren(selectByExample);
				getDicChildsList(selectByExample,name,code);
			}
		}
		return list;
	}

}
