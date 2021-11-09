<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.dao.repository.${subpackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import ${basepackage}.dao.po.${subpackage}.${className}PO;
import ${basepackage}.dao.mapper.${subpackage}.${className}Mapper;
import ${basepackage}.dao.repository.BaseRepository;

/**
 * @description: 【非必需】业务操作仓储实现类
 * 通过Mybatis-Plus框架基类方法操作,对应classpath:mapper/xxxMapper.xml
 * @author: xxx
 * @date: Created in xxx
 */
@Repository
public class ${className}Repository extends BaseRepository<${className}Mapper, ${className}PO> {

    @Autowired
    private ${className}Dao ${classNameLower}Dao;

}
