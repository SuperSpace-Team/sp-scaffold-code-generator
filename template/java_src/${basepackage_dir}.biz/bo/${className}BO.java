<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.biz.bo.${subpackage};

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.yh.infra.common.bo.BaseBO;

<#include "/java_imports.include">

/**
 * @description: ${className}业务操作POJO定义
 * @author: xxx
 * @date: Created in xxx
 */
@Data
@NoArgsConstructor
public class ${className}BO extends BaseBO {
	
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



