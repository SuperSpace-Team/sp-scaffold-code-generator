package com.sp.scaffold.generator.test;

import java.io.File;

import com.sp.scaffold.generator.provider.db.DbTableFactory;
import com.sp.scaffold.generator.provider.db.model.Table;

public class IbatisGeneratorTest extends GeneratorTestCase {

	public void testGenerate() throws Exception {

		Table table = DbTableFactory.getInstance().getTable("USER_INFO");

		g.addTemplateRootDir(new File("template").getAbsoluteFile());
		g.addTemplateRootDir(new File("plugins/ibatis/template"));

		generateByTable(table);

		//Runtime.getRuntime().exec("cmd.exe /c start "+new File(g.outRootDir).getAbsolutePath());
	}

}
