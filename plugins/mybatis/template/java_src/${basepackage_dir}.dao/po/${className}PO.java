<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package ${basepackage}.dao.po.${subpackage};

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

<#include "/java_imports.include">
import com.yh.infra.common.base.BasePO;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: DB实体定义类
 *
 * @author: xxx
 * @date: Created in xxx
 */
@Data
@NoArgsConstructor
@TableName("${table.sqlName}")
public class ${className}PO extends BasePO {

	<#list table.notDefaultColumns as column>
	private ${column.javaType} ${column.columnNameLower};
	</#list>

<@generateJavaColumns/>

<#macro generateJavaColumns>
	<#list table.notDefaultColumns as column>
		<#if column.isDateTimeColumn>
	public String get${column.columnName}String() {
		return date2String(get${column.columnName}(), DATE_TIME_FORMAT);
	}
	public void set${column.columnName}String(String value) {
		set${column.columnName}(string2Date(value, DATE_TIME_FORMAT,${column.javaType}.class));
	}
	
		</#if>
	</#list>
</#macro>