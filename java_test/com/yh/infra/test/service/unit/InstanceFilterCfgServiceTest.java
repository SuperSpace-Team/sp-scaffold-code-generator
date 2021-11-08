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
 package com.yh.infra.test.biz.service.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Test;
import static junit.framework.Assert.*;


import java.util.List;
import java.util.Map;


public class InstanceFilterCfgManagerTest extends BaseManagerTestCase{
	@Autowired
	private InstanceFilterCfgService service;
	

	@Override
	protected String[] getDbUnitDataFiles() {
		return new String[]{"classpath:common_testdata.xml","classpath:InstanceFilterCfg_testdata.xml"};
	}

	@Test
	public void crud() {
		InstanceFilterCfg obj = new InstanceFilterCfg();
		
	  	obj.setVersion(new java.lang.Long("1"));
	  	obj.setInstanceId(new java.lang.Long("1"));
	  	obj.setFilterId(new java.lang.Long("1"));
	  	obj.setFilterOrder(new java.lang.Long("1"));
	  	obj.setStatus(new java.lang.Integer("1"));
	  	obj.setIsDeleted(new java.lang.Boolean("1"));
	  	obj.setCreateBy(new java.lang.String("1"));
	  	obj.setCreateTime(new java.util.Date(System.currentTimeMillis()));
	  	obj.setUpdateBy(new java.lang.String("1"));
	  	obj.setUpdateTime(new java.util.Date(System.currentTimeMillis()));
		
		manager.save(obj);
		manager.getEntityDao().flush();
		
		manager.update(obj);
		manager.getEntityDao().flush();
		
		assertNotNull(obj.getId());
		
		manager.removeById(obj.getId());
		manager.getEntityDao().flush();
	
	}
}
