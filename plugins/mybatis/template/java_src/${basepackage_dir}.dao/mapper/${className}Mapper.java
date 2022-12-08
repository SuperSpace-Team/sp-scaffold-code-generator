<#include "/java_copyright.include">
<#assign className = table.className>

package ${basepackage}.dao.mapper.${subpackage};

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import ${basepackage}.domain.po.${subpackage}.${className}PO;

/**
 * @description: 业务Mapper类定义
 * 通过Mybatis-Plus框架基类方法操作,对应classpath:mapper/xxxMapper.xml
 * @author: xxx
 * @date: Created in xxx
 */
public interface ${className}Mapper extends BaseMapper<${className}PO> {

}
