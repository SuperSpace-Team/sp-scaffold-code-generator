package com.sp.scaffold.generator.provider.db.model;

public interface DoConstant {
	String YES = "y";
	String NO = "n";

	/**
	 * 公共属性/字段定义
	 * 锁标识和创建/更新人及其时间
	 */
	String VERSION = "version";
	String CREATED_BY = "createdBy";
	String UPDATED_BY = "updatedBy";
	String IS_DELETE = "isDelete";
	String CREATED_AT = "createdAt";
	String UPDATED_AT = "updatedAt";

	String DB_CREATOR = "createdBy";
	String DB_MODIFIER = "updatedBy";
	String DB_IS_DELETED = "is_delete";
	String DB_GMT_CREATE = "created_at";
	String DB_GMT_MODIFIED = "updated_at";

	/**
	 * 系统用户
	 */
	Integer SYSTEM_USER = 0;
}
