package com.sp.scaffold.generator.provider.db.model;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.sp.scaffold.generator.provider.db.DbTableFactory;
import com.sp.scaffold.generator.util.StringHelper;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author badqiu
 * @email badqiu(a)gmail.com
 */
public class Table {
	/**
	 * 表名
	 */
	String tableType;
	String sqlName;
	String remarks;
	/**
	 * 自定义类名，如果没有自定义，则使用规则生成
	 */
	String customClassName;
	
	/**
	 * version字段(启用乐观锁)
	 */
	Column versionColumn;
	
	//是否有自增ID，如果没有，则需要在insert语句中将id包含进去
	boolean hasAutoIncrementId;
	/** the name of the owner of the synonym if this table is a synonym */
	private String ownerSynonymName = null;
	Set<Column> columns = new LinkedHashSet();
	List<Column> primaryKeyColumns = new ArrayList();

	public Set<Column> getColumns() {
		return columns;
	}

	public void setColumns(Set columns) {
		this.columns = columns;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	
	public Column getVersionColumn() {
		return versionColumn;
	}

	public void setVersionColumn(Column versionColumn) {
		this.versionColumn = versionColumn;
	}

	public String getOwnerSynonymName() {
		return ownerSynonymName;
	}

	public void setOwnerSynonymName(String ownerSynonymName) {
		this.ownerSynonymName = ownerSynonymName;
	}

	public List<Column> getPrimaryKeyColumns() {
		return primaryKeyColumns;
	}

	public void setPrimaryKeyColumns(List<Column> primaryKeyColumns) {
		this.primaryKeyColumns = primaryKeyColumns;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getRemarks() {
		String remark = remarks;
		if (remark != null) {
			if ((remark.indexOf("\r\n")) > -1) {
				return remark.substring(0, remark.indexOf("\r\n"));
			} else if ((remark.indexOf("\n")) > -1) {
				return remark.substring(0, remark.indexOf("\n"));
			}
		}
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void addColumn(Column column) {
		columns.add(column);
	}

	public void setClassName(String customClassName) {
		this.customClassName = customClassName;
	}

	public String getClassName() {
		String defaultValue = StringHelper.makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(getSqlName()));
		return StringHelper.emptyIf(customClassName, defaultValue);
	}

	public String getJspClassName() {
		String defaultValue = StringHelper.makeJspPath(StringHelper.toUnderscoreName(getSqlName()));
		return defaultValue;
	}

	public String getJspRootName() {
		String defaultValue = StringHelper.makeJspRootPath(StringHelper.toUnderscoreName(getSqlName()));
		return defaultValue;
	}

	public boolean isHasAutoIncrementId() {
		return hasAutoIncrementId;
	}

	public void setHasAutoIncrementId(boolean hasAutoIncrementId) {
		this.hasAutoIncrementId = hasAutoIncrementId;
	}

	public String getTableAlias() {
		return StringHelper.emptyIf(getRemarks(), getClassName());
	}

	public String getClassNameLowerCase() {
		return getClassName().toLowerCase();
	}

	public String getUnderscoreName() {
		return getSqlName().toLowerCase();
	}

	public String getClassNameFirstLower() {
		return StringHelper.uncapitalize(getClassName());
	}

	public String getConstantName() {
		return StringHelper.toUnderscoreName(getClassName()).toUpperCase();
	}

	public boolean isSingleId() {
		return getPkCount() == 1 ? true : false;
	}

	public boolean isCompositeId() {
		return getPkCount() > 1 ? true : false;
	}

	public boolean isView() {
		return StringUtils.equalsIgnoreCase("VIEW", this.getTableType());
	}

	public boolean getIsTable() {
		return !isView();
	}

	public boolean isNotCompositeId() {
		return !isCompositeId();
	}

	public int getPkCount() {
		int pkCount = 0;
		for (Column c : columns) {
			if (c.isPk()) {
				pkCount++;
			}
		}
		return pkCount;
	}

	/**
	 * use getPkColumns()
	 * 
	 * @deprecated
	 */
	@Deprecated
	public List getCompositeIdColumns() {
		return getPkColumns();
	}

	public List getPkColumns() {
		List results = new ArrayList();
		for (Column c : getColumns()) {
			if (c.isPk())
				results.add(c);
		}
		return results;
	}

	/**
	 * 获取非主键的字段
	 * @return
	 */
	public List getNotPkColumns() {
		List results = new ArrayList();
		for (Column c : getColumns()) {
			if (!c.isPk())
				results.add(c);
		}
		return results;
	}

	/**
	 * 获取插入时候的字段，不包含修改人以及修改日期
	 * @return
	 */
	public List getInsertColumns() {
		List results = new ArrayList();
		for (Column c : getColumns()) {
			if (!c.isPk()) {
				if (false && StringUtils.equalsIgnoreCase(DoConstant.UPDATED_AT, c.getColumnName())) {
				} else {
					results.add(c);
				}
			}
		}
		return results;
	}

	/**
	 * 获取修改字段，不包括产生日期和产生人
	 * @return
	 */
	public List getUpdateColumns() {
		List results = new ArrayList();
		for (Column c : getColumns()) {
			if (!c.isPk()) {
				if (StringUtils.equalsIgnoreCase(DoConstant.CREATED_BY, c.getColumnName())
						|| StringUtils.equalsIgnoreCase(DoConstant.CREATED_AT, c.getColumnName())) {
				} else {
					results.add(c);
				}
			}
		}
		return results;
	}

	/**
	 * 获取非标准字段
	 * @return
	 */
	public List getNotDefaultColumns() {
		List results = new ArrayList();
		for (Column c : getColumns()) {
			if (!c.isPk()) {
				if (StringUtils.equalsIgnoreCase(DoConstant.CREATED_BY, c.getColumnName())
						|| StringUtils.equalsIgnoreCase(DoConstant.IS_DELETE, c.getColumnName())
						|| StringUtils.equalsIgnoreCase(DoConstant.UPDATED_BY, c.getColumnName())
						|| StringUtils.equalsIgnoreCase(DoConstant.CREATED_AT, c.getColumnName())
						|| StringUtils.equalsIgnoreCase(DoConstant.UPDATED_AT, c.getColumnName())
						|| StringUtils.equalsIgnoreCase(DoConstant.VERSION, c.getColumnName())) {
				} else {
					results.add(c);
				}
			}
		}
		return results;
	}

	public List getCommonColumns() {
		List results = new ArrayList();
		for (Column c : getColumns()) {
			if (!c.isPk()) {
				boolean isDefaultValue = false;
				for (String str : Column.defaultColumnNames) {
					if (StringUtils.equalsIgnoreCase(str, c.getColumnName())) {
						isDefaultValue = true;
						break;
					}
				}
				if (!isDefaultValue) {
					results.add(c);
				}

			}
		}
		return results;
	}

	public Column getIdColumn() {
		if (isView()) {
			//如果是视图，则返回
			for (Column c : getColumns()) {
				if (StringUtils.equalsIgnoreCase(c.getColumnName(), "id"))
					return c;
			}
		}
		for (Column c : getColumns()) {
			if (c.isPk())
				return c;
		}
		return null;
	}

	/**
	 * This method was created in VisualAge.
	 */
	public void initImportedKeys(DatabaseMetaData dbmd) throws java.sql.SQLException {

		// get imported keys a

		ResultSet fkeys = dbmd.getImportedKeys(catalog, schema, this.sqlName);

		while (fkeys.next()) {
			String pktable = fkeys.getString(PKTABLE_NAME);
			String pkcol = fkeys.getString(PKCOLUMN_NAME);
			String fktable = fkeys.getString(FKTABLE_NAME);
			String fkcol = fkeys.getString(FKCOLUMN_NAME);
			String seq = fkeys.getString(KEY_SEQ);
			Integer iseq = new Integer(seq);
			getImportedKeys().addForeignKey(pktable, pkcol, fkcol, iseq);
		}
		fkeys.close();
	}

	/**
	 * This method was created in VisualAge.
	 */
	public void initExportedKeys(DatabaseMetaData dbmd) throws java.sql.SQLException {
		// get Exported keys

		ResultSet fkeys = dbmd.getExportedKeys(catalog, schema, this.sqlName);

		while (fkeys.next()) {
			String pktable = fkeys.getString(PKTABLE_NAME);
			String pkcol = fkeys.getString(PKCOLUMN_NAME);
			String fktable = fkeys.getString(FKTABLE_NAME);
			String fkcol = fkeys.getString(FKCOLUMN_NAME);
			String seq = fkeys.getString(KEY_SEQ);
			Integer iseq = new Integer(seq);
			getExportedKeys().addForeignKey(fktable, fkcol, pkcol, iseq);
		}
		fkeys.close();
	}

	/**
	 * @return Returns the exportedKeys.
	 */
	public ForeignKeys getExportedKeys() {
		if (exportedKeys == null) {
			exportedKeys = new ForeignKeys(this);
		}
		return exportedKeys;
	}

	/**
	 * @return Returns the importedKeys.
	 */
	public ForeignKeys getImportedKeys() {
		if (importedKeys == null) {
			importedKeys = new ForeignKeys(this);
		}
		return importedKeys;
	}

	@Override
	public String toString() {
		return "Database Table:" + getSqlName() + " to ClassName:" + getClassName();
	}

	String catalog = DbTableFactory.getInstance().getCatalog();
	String schema = DbTableFactory.getInstance().getSchema();

	private ForeignKeys exportedKeys;
	private ForeignKeys importedKeys;

	public static final String PKTABLE_NAME = "PKTABLE_NAME";
	public static final String PKCOLUMN_NAME = "PKCOLUMN_NAME";
	public static final String FKTABLE_NAME = "FKTABLE_NAME";
	public static final String FKCOLUMN_NAME = "FKCOLUMN_NAME";
	public static final String KEY_SEQ = "KEY_SEQ";
}
