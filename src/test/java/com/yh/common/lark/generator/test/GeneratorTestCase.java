package com.yh.common.lark.generator.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.yh.common.lark.generator.Generator;
import com.yh.common.lark.generator.provider.db.model.Table;
import org.junit.Assert;
import org.junit.Before;

import com.yh.common.lark.generator.GeneratorProperties;
import com.yh.common.lark.generator.GeneratorFacade.GeneratorModel;
import com.yh.common.lark.generator.provider.db.DbTableFactory;
import com.yh.common.lark.generator.util.IOHelper;

public class GeneratorTestCase extends Assert {
	protected Generator g;

	@Before
	public void setUp() throws Exception {
		//不使用
		//runSqlScripts();

		g = new Generator();
		if (isRuningByAnt()) {
			String tempDir = getTempDir();
			System.out.println("running by ant, set outRootDir to tempDir=" + tempDir);
			g.setOutRootDir(tempDir);
		} else {
			g.setOutRootDir(".");
		}
	}

	private boolean isRuningByAnt() {
		return System.getProperty("java.class.path").indexOf("ant.jar") >= 0;
	}

	public static void runSqlScripts() throws SQLException, IOException {
		GeneratorProperties.setProperty("jdbc.url", "jdbc:hsqldb:mem:generatorDB");
		GeneratorProperties.setProperty("jdbc.driver", "org.hsqldb.jdbcDriver");
		GeneratorProperties.setProperty("jdbc.username", "sa");
		GeneratorProperties.setProperty("jdbc.password", "");
		GeneratorProperties.setProperty("jdbc.schema", "");
		GeneratorProperties.setProperty("jdbc.catalog", "");

		Connection conn = DbTableFactory.getInstance().getConnection();
		Connection conn2 = DbTableFactory.getInstance().getConnection();
		assertEquals(conn, conn2);

		System.out.println(conn.getCatalog());

		Statement stat = conn.createStatement();
		String sqlTables = IOHelper.readClassFile("test/generator_test_table.sql");
		System.out.println(sqlTables);
		stat.execute(sqlTables);
		stat.close();

	}

	public void generateByTable(Table table) throws Exception {
		GeneratorModel m = GeneratorModel.newFromTable(table);
		g.generateBy(m.templateModel, m.filePathModel);
	}
	
	

	public void generateByTable(Generator g, Table table) throws Exception {
		GeneratorModel m = GeneratorModel.newFromTable(table);
		g.generateBy(m.templateModel, m.filePathModel);
	}

	public String getTempDir() {
		String tempDir = System.getProperty("java.io.tmpdir");
		return tempDir + "/test_generator_out";
	}

}
