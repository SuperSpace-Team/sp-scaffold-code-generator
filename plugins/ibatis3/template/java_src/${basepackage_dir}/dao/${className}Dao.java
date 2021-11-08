<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

<#include "/java_imports.include">

import org.springframework.stereotype.Component;
import com.woyo.ims.entity.${className};
import com.woyoframework.orm.dal.ibatis3.BaseIbatis3Dao;


@Component
public class ${className}Dao extends BaseIbatis3Dao<${className}>{

	<#list table.columns as column>
	<#if column.unique && !column.pk>
	public ${className} getBy${column.columnName}(${column.javaType} v) {
		return (${className})sqlSessionTemplate.selectOne("${className}.getBy${column.columnName}",v);
	}	
	</#if>
	</#list>

}
