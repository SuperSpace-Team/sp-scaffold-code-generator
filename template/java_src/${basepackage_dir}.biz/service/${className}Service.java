<#include "/java_copyright.include">
<#assign className = table.className>

package ${basepackage}.biz.service;

import com.yh.common.lark.common.dao.Page;
import com.yh.common.lark.common.dao.Pagination;
import com.yh.infra.common.vo.BasePageQueryReqVO;
import com.yh.scaffold.api.user.domain.bo.DemoBiz1BO;

import java.util.List;
import java.util.Map;

/**
 * @description: ${className}服务定义
 * @author: xxx
 * @date: Created in ${now}
 */
public interface ${className}Service extends BaseBizService<Long, ${className}BO> {
    /**
     * 普通分页查询
     * @param page
     * @param param
     * @return
     */
    Pagination<${className}BO> query(Page page, Map<String, Object> param);

    /**
     * 分页+排序查询方法
     * @param basePageQueryVO
     * @return
     */
    Pagination<${className}BO> getPageDataList(BasePageQueryReqVO basePageQueryVO);
}
