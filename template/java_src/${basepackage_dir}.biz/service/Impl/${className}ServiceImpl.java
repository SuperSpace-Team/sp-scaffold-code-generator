<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

package ${basepackage}.biz.service.${subpackage}.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.sp.framework.orm.lark.common.dao.Page;
import com.sp.framework.orm.lark.common.dao.Pagination;
import com.sp.framework.orm.lark.common.dao.Sort;

import com.sp.infra.common.utils.bean.DozerUtil;
import com.sp.framework.common.base;

import ${basepackage}.biz.bo.${subpackage}.${className}BO;
import ${basepackage}.domain.po.${subpackage}.${className}PO;
import ${basepackage}.biz.service.${subpackage}.${className}Service;
import ${basepackage}.dao.repository.${subpackage}.${className}Repository;

/**
 * @description: 业务Service实现类
 * @author: xxx
 * @date: Created in xxx
 */
@Service
public class ${className}ServiceImpl extends BaseBizServiceImpl<Long, ${className}BO, ${className}Dao> implements ${className}Service {
    @Autowired
    ${className}Repository ${classNameLower}Repository;

    @Autowired
    ${className}Dao ${classNameLower}Dao;

    /**
     * 保存单条记录
     * @param ${classNameLower}BO
     * @return
     */
    @Override
    public Boolean save(${className}BO ${classNameLower}BO) {
        validate(${classNameLower}BO);

        ${className}PO ${classNameLower}PO = DozerUtil.map(${classNameLower}BO, ${className}PO.class);
        if (${classNameLower}PO.getId() == null) {
        ${classNameLower}PO.setVersion(1L);
            return ${classNameLower}Repository.save(${classNameLower}PO) ;
        }

        return ${classNameLower}Repository.updateById(${classNameLower}PO);
    }

    /**
     * 根据指定的分页/排序/属性集合条件查询数据列表
     * @param page 分页对象
     * @param param 筛选属性条件集合
     * @return
     */
    @Override
    public Pagination<${className}BO> query(Page page, Map<String, Object> param) {
        //默认按更新时间排序
        Sort[] sorts = new Sort[1];
        sorts[0] = new Sort("updated_at", "DESC");
        return DozerUtil.map(${classNameLower}Dao.findListByQueryMapWithPage(page, sorts, param), Pagination.class);
    }

    /**
     * 统一分页综合查询
     * 普通场景:Pagination<${className}PO>转成Pagination<${className}BO>
     * 多表Join分页查询场景: 自定义BO对象接收mapper查询出的结果集,返回Pagination<${className}BO>
     * @param basePageQueryVO 分页排序请求VO
     * @return
     */
    @Override
    public Pagination<${className}BO> getPageDataList(BasePageQueryReqVO basePageQueryVO) {
        return DozerUtil.map(${classNameLower}Dao.findListByQueryMapWithPage(basePageQueryVO.getPage(),
                basePageQueryVO.getSorts(), basePageQueryVO.getParams()), Pagination.class);
    }
}
