<#include "/java_copyright.include">

package ${basepackage}.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

import com.sp.framework.orm.lark.orm.dao.supports.BaseDao;
import com.sp.framework.common.bo.BaseBO;
import com.sp.framework.common.enums.SystemErrorCodeEnum;
import com.sp.framework.common.exception.BusinessException;
import com.sp.infra.comp.core.validator.BizValidationManager;

/**
 * @description: 【参考使用(非必需)】基础业务实现类
 * 用于放置可复用代码,适合大部分的ServiceImpl,但也有少数ServiceImpl需要进行定制,KEY不一定是表的ID,
 * 一般是业务编码或具有联合主键的属性/字段,所以需要定制化getModelByCode.
 *
 * @author: xxx
 * @date: Created in xxxx
 */
@Slf4j
@Service
public class BaseBizServiceImpl<KEY, BO extends BaseBO, DAO extends BaseDao> {
    @Autowired
    BizValidationManager validationManager;

    /**
     * 业务DAO操作实例
     */
    @Autowired
    DAO bizDao;

    /**
     * 获取DAO对象
     *
     * @return
     */
    protected BaseDao<BO, Long> getDao() {
        throw bizDao;
    }

    /**
     * 根据编码从DB中查询到PO实体
     * 适用于DB表/实体带有业务编码属性的场景
     * @param code
     * @return
     */
    protected BO getModelByCode(Integer code) {
        throw new RuntimeException("NOT IMPLEMENTED.");
    };

    /**
     * 校验数据
     *
     * @param bo
     * @return
     */
    protected void validate(BO bo) {
        List<Error> errors = validationManager.validate(bo);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new BusinessException(SystemErrorCodeEnum.DATA_ENROLL_DB_ERROR.getCode(), errors.get(0).getMessage());
        }
    }

    /**
     * 通用更新操作
     *
     * @param bo
     */
    public Boolean update(BO bo) {
        //经各校验器检查
        validate(bo);

        try {
            if (bo.getId() == null) {
                bo.setVersion(1L);

                if(bizDao.insert(bo) == 0L) {
                    throw new BusinessException(SystemErrorCodeEnum.DATA_ENROLL_DB_ERROR.getCode(),
                            "更新/插入数据失败，请检查." + bo.getId());
                }
            } else {
                if (bizDao.update(bo) == 0) {
                    throw new BusinessException(SystemErrorCodeEnum.DATA_ENROLL_DB_ERROR.getCode(),
                            "无法更新数据，可能是数据过期." + bo.getId());
                }
            }
        } catch (Exception e) {
            log.error("BaseManagerImpl update error, exception:" + e);

            if (e instanceof DuplicateKeyException) {
                throw new BusinessException(SystemErrorCodeEnum.DATA_ENROLL_DB_ERROR.getCode(),
                        "数据库中存在重复数据，请确保记录唯一存在:" + bo.getId());
            } else {
                throw new BusinessException(SystemErrorCodeEnum.DATA_ENROLL_DB_ERROR.getCode(),
                        SystemErrorCodeEnum.DATA_ENROLL_DB_ERROR.getMsg() + "请重试:" + bo.getId());
            }
        }

        return true;
    }

    /**
     * 启用/生效该条记录
     * @param key
     * @param operator
     * @return
     */
    public Boolean enable(KEY key, String operator) {
        return true;
//      return changeStatus(key, operator, true);
    }

    /**
     * 禁用/失效该条记录
     * @param key
     * @param operator
     * @return
     */
    public Boolean disable(KEY key, String operator) {
        return true;
//      return changeStatus(key, operator, false);
    }

//    /** 参考使用
//     * 更新记录的业务状态值
//     * 适用于DB表/实体带有status字段的场景
//     * @param key
//     * @param operator
//     * @param newStatus
//     * @return
//     */
//    protected Boolean changeStatus(KEY key, String operator, Boolean newStatus) {
//        Integer code = (Integer) key;
//        BO bo = getModelByCode(code);
//
//        // 已经是目标状态，不需要更改
//        if (newStatus.equals(bo.getStatus())) {
//            return true;
//        }
//
//        //自行实现changeStatus() SQL查询
//        int update = bizDao.changeStatus(getTableName(), code, operator, newStatus, bo.getVersion());
//        // 更新成功
//        if (update != 1) {
//            bo = getModelByCode(code);
//            // 若被更新过,则不需要更新版本号
//            if (newStatus.equals(bo.getStatus())) {
//                return true;
//            }
//
//            // 更新失败
//            if (update == 0) {
//                throw new BusinessException(SystemErrorCodeEnum.DATA_ENROLL_DB_ERROR.getCode(), "无法更新状态为" + newStatus);
//            }
//        }
//
//        return true;
//    }
}
