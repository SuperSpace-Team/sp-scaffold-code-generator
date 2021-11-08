/**
 * Copyright (c) 2013 Yonghui All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Yonghui.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Yonghui.
 *
 * YONGHUI MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. YONGHUI SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
 
package com.yh.infra.test.dao.repository.user.unit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import static junit.framework.Assert.*;

import java.util.List;
import java.util.Map;


public class InstanceFilterParamCfgDaoTest extends BaseDaoTestCase {
	@Autowired
	private InstanceFilterParamCfgDao instanceFilterParamCfgDao;
	
	@Override
	protected String[] getDbUnitDataFiles() {
		return new String[]{"classpath:common_testdata.xml","classpath:InstanceFilterParamCfg_testdata.xml"};
	}
	
	@Test
	public void findByPageRequest() {
		int pageNumber = 1;
		int pageSize = 10;
		InstanceFilterParamCfg obj = new InstanceFilterParamCfg();
		
	  	obj.setVersion(new java.lang.Long("1"));
	  	obj.setInstanceId(new java.lang.Long("1"));
	  	obj.setFilterParamId(new java.lang.Long("1"));
	  	obj.setFilterParamCfgValue(new java.lang.String("1"));
	  	obj.setFilterId(new java.lang.Long("1"));
	  	obj.setDescription(new java.lang.String("1"));
	  	obj.setIsDeleted(new java.lang.Boolean("1"));
	  	obj.setCreateBy(new java.lang.String("1"));
	  	obj.setCreateTime(new java.util.Date(System.currentTimeMillis()));
	  	obj.setUpdateBy(new java.lang.String("1"));
	  	obj.setUpdateTime(new java.util.Date(System.currentTimeMillis()));
		
		PageRequest<InstanceFilterParamCfg> pageRequest = new PageRequest<InstanceFilterParamCfg>(obj);
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		pageRequest.setSortColumns(null);

		Page page = instanceFilterParamCfgDao.getAllByPage(pageRequest);
		assertEquals(pageNumber,page.getPageNo());
		assertEquals(pageSize,page.getPageSize());
		List resultList = (List)page.getResult();
		assertNotNull(resultList);		
	}
	
}
