package com.yh.common.lark.generator.test;

import java.io.File;

import com.yh.common.lark.generator.provider.db.DbTableFactory;
import com.yh.common.lark.generator.provider.db.model.Table;

public class ExtjsGeneratorTest extends GeneratorTestCase {

	public void testGenerate() throws Exception {

		Table table = DbTableFactory.getInstance().getTable("USER_INFO");

		g.addTemplateRootDir(new File("template").getAbsoluteFile());
		g.addTemplateRootDir(new File("plugins/extjs/template"));

		generateByTable(table);

		//		Runtime.getRuntime().exec("cmd.exe /c start "+new File(g.outRootDir).getAbsolutePath());
	}

}
