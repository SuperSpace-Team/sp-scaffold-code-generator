<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package ${basepackage}.dao.po.${subpackage};

import com.yh.infra.common.base.BasePO;
import com.yh.infra.common.utils.DateUtil;
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
			public String get${column.columnName}String(){
				return DateUtil.formatDate(get${column.columnName}(),DateUtil.YMD_HMS);
			}

			public void set${column.columnName}String(String value){
				set${column.columnName}(DateUtil.parse(value,DateUtil.YMD_HMS));
			}
		</#if>
		</#list>
	</#macro>
}