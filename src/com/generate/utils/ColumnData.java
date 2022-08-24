package com.generate.utils;

/**
 * 字段类型  
 * @author liubinwang
 *
 */
public class ColumnData {
	private String columnName; //列名
	private String propName; //属性名
	private String dataType;//数据类型
	private String jdbcType;// mybatis 数据jdbc类型
	private String columnComment;//注释
	private boolean pri;//是否主键

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public boolean isPri() {
		return pri;
	}

	public void setPri(boolean pri) {
		this.pri = pri;
	}

	
	
	

}