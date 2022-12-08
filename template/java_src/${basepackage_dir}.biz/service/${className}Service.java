<#include "/java_copyright.include">
<#assign className = table.className>

package ${basepackage}.biz.service.${subpackage};

import java.util.List;
import java.util.Map;

import com.sp.framework.orm.lark.common.dao.Page;
import com.sp.framework.orm.lark.common.dao.Pagination;
import com.sp.framework.common.vo.BasePageQueryReqVO;
import ${basepackage}.biz.bo.${subpackage}.${className}BO;
import ${basepackage}.biz.service.BaseBizService;

/**
 * @description: ${className}业务服务定义
 * @author: xxx
 * @date: Created in xxx
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
