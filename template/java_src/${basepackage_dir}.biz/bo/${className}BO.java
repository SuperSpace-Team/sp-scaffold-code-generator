<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.biz.bo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

<#include "/java_imports.include">

@Data
@NoArgsConstructor
public class ${className}BO implements Serializable {
	
	<@generateFields/>
	<@generateProperties/>

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}

<#macro generateFields>
	<#list table.columns as column>
	/**
	 * ${column.columnAlias}
	 */
	private ${column.javaType} ${column.columnNameLower};
	</#list>

</#macro>



