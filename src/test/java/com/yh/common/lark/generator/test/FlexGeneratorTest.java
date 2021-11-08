package com.yh.common.lark.generator.test;

import java.io.File;

import com.yh.common.lark.generator.provider.db.model.Table;
import com.yh.common.lark.generator.provider.db.DbTableFactory;

public class FlexGeneratorTest extends GeneratorTestCase {

	public void testGenerate() throws Exception {

		Table table = DbTableFactory.getInstance().getTable("USER_INFO");

		g.addTemplateRootDir(new File("template").getAbsoluteFile());
		g.addTemplateRootDir(new File("plugins/flex/template"));
		g.addTemplateRootDir(new File("plugins/flex_cairngorm/template"));

		generateByTable(table);

		//Runtime.getRuntime().exec("cmd.exe /c start "+new File(g.outRootDir).getAbsolutePath());
	}

}
