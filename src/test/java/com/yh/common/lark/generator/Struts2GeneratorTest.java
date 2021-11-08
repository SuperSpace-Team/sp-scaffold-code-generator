package com.yh.common.lark.generator;

import java.io.File;

import com.yh.common.lark.generator.provider.db.DbTableFactory;
import com.yh.common.lark.generator.provider.db.model.Table;
import com.yh.common.lark.generator.test.GeneratorTestCase;
import org.junit.Test;

public class Struts2GeneratorTest extends GeneratorTestCase {
	private static String[] keys = { "user" };

	@Test
	public void testAll() throws Exception {
		//keys = new String[] { "USERINFO" };
		for (String str : keys) {
			testGenerate(str);
		}
	}

	public void testGenerate(String tableName) throws Exception {

		Table table = DbTableFactory.getInstance().getTable(tableName);

		g.addTemplateRootDir(new File("template").getAbsoluteFile());
		g.addTemplateRootDir(new File("plugins/struts2/template"));

		generateByTable(table);

		//		Runtime.getRuntime().exec("cmd.exe /c start "+new File(g.outRootDir).getAbsolutePath());
	}

}
