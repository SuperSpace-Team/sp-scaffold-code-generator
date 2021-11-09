<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.dao.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.common.lark.orm.dao.supports.BaseDao;

/**
 * @description: 抽象公共仓储类
 * @author: xxx
 * @date: Created in xxx
 */
public abstract class BaseRepository<MP extends BaseMapper<PO>, PO> extends ServiceImpl<MP, PO> {

}
