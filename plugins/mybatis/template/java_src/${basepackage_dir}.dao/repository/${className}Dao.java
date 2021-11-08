<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>

package ${basepackage}.dao.repository.${subpackage};

<#include "/java_imports.include">
<#include "/java_dao_imports.include">


import java.util.Map;

import ${basepackage}.dao.po.${subpackage}.${className}PO;

public interface ${className}Dao extends BaseDao<${className}PO,Long>{
	<#list table.columns as column>
	<#if column.unique && !column.pk>
	/**
	 * 根据主键属性查询配置信息状态
	 * @return
	 */
	public ${className}PO getBy${column.columnName}(${column.javaType} v) {
		return (${className})sqlSessionTemplate.selectOne("${className}PO.getBy${column.columnName}",v);
	}
	</#if>
	</#list>

	/**
	 * 按分页/排序查询数据列表
	 * @param page
	 * @param sorts
	 * @param params
	 * @return Pagination<T>
	 */
	@QueryPage("findListCountByQueryMap")
	Pagination<${className}PO> findListByQueryMapWithPage(Page page,Sort[] sorts,Map<String, Object> params);

	/**
	 * 新增或更新记录
	 * @param o
	 * @return int
	 */
	@CommonQuery
	int saveOrUpdate(${className}PO po);

	<#if table.versionColumn?? >
	/**
	 * 按version新增或更新记录
	 * @param po
	 * @return int
	 */
	@CommonQuery
	int saveOrUpdateByVersion(${className}PO po);
	</#if>
}
