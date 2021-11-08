<#include "/java_copyright.include">
<#assign className = table.className>

package ${basepackage}.biz.service;

/**
 * @description: 基础业务Service方法定义
 * @author: xxx
 * @date: Created in xxx
 */
public interface BaseBizService<KEY, BO> {
    /**
     * 保存记录
     * @param bizBO
     * @return
     */
    Boolean save(BO bizBO);

    /**
     * 启用
     * @param key 主键字段
     * @param operator 操作人
     */
    Boolean enable(KEY key, String operator);

    /**
     * 禁用
     * @param key 主键字段
     * @param operator 操作人
     */
    Boolean disable(KEY key, String operator);
}
