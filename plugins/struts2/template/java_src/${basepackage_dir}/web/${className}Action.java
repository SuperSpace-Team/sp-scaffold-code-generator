<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package ${basepackage}.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import cn.org.rapid_framework.beanutils.BeanUtils;

import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;
import com.woyoframework.modules.web.CrudActionSupport;

<#include "/java_imports.include">
@SuppressWarnings("serial")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "${table.jspClassName}.action", type = "redirect") })
public class ${className}Action extends WoyoCrudActionSupport<${className}Manager,${className}Dao,${className}>{
	@Override
	@Autowired
	protected void setManager(${className}Manager ${classNameLower}Manager){
		this.manager=${classNameLower}Manager;
	}
	
}
