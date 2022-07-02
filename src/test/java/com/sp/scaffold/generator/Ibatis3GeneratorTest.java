package com.sp.scaffold.generator;

import java.io.File;

import com.sp.scaffold.generator.provider.db.model.Column;
import com.sp.scaffold.generator.provider.db.model.Table;
import com.sp.scaffold.generator.test.GeneratorTestCase;
import com.sp.scaffold.generator.provider.db.DbTableFactory;

import org.junit.Test;

public class Ibatis3GeneratorTest extends GeneratorTestCase {

	private static String[] tableNameKeys = { "dyeing_rule_group", "dyeing_node_rules" };
	
	//如果为{}则表使用用默认的方式生成class name
	//如 sys_dictionary,默认class name为 SysDictionary
	private static String[] classNameKeys = { "DyeingRuleGroup", "DyeingNodeRules"};

	//是否有自增ID，如果没有，则需要在insert语句中将id包含进去
	private static boolean hasAutoIncrementId=true;
	
	/**
	 * version字段，用于update by version
	 */
	private static String[] versionNames={"updatedAt"};
	
	@Test
	public void testAll() throws Exception {
		//keys = new String[] { "USERINFO" };
		for (int i=0;i < tableNameKeys.length;i++) {
			String table = tableNameKeys[i];
			String className = null;
			String versinName = null;
			if(classNameKeys != null && classNameKeys.length == tableNameKeys.length)
				className = classNameKeys[i];
			
			if(versionNames != null && versionNames.length == tableNameKeys.length)
				versinName = versionNames[i];
			
			testGenerate(table, className, versinName);
		}
	}

	public void testGenerate(String tableName,String className,String versionName) throws Exception {
		Table table = DbTableFactory.getInstance().getTable(tableName);
		table.setClassName(className);
		g.addTemplateRootDir(new File("template").getAbsoluteFile());
		g.addTemplateRootDir(new File("plugins/mybatis/template"));
		table.setHasAutoIncrementId(hasAutoIncrementId);
		
		if(versionName!=null){
			for(Object obj:table.getNotPkColumns()){
				Column column=(Column)obj;
				if(versionName.equalsIgnoreCase(column.getColumnNameFirstLower())){
					table.setVersionColumn(column);
				}
			}
		}
		
		generateByTable(table);
		//Runtime.getRuntime().exec("cmd.exe /c start "+new File(g.outRootDir).getAbsolutePath());
	}

	@Test
	public void testPrint() throws Exception {
		String table1Name = tableNameKeys[0];
		Table table = DbTableFactory.getInstance().getTable(table1Name);
		System.out.println(table.getSqlName());
	}
}
