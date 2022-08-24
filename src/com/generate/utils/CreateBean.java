package com.generate.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class CreateBean {
	private String url;
	private String username;
	private String password;
	private String database;
	private static final String SQLTables = "select table_name from user_tables";
	private String method;
	private String argv;
	private static final String ORACLE = "oracle";
	private static final String MYSQL = "mysql";
	private static final String PRI = "PRI";
	private String databaseName;

	public void setConnectionInfo(String url, String username, String password, String database) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.database = database;
	}

	public Connection getConnection()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (url.indexOf(ORACLE) != -1) {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			databaseName = ORACLE;
		} else if (url.indexOf(MYSQL) != -1) {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			databaseName = MYSQL;
		}
		return DriverManager.getConnection(url, username, password);
	}

	public List<String> getTables()
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(SQLTables);
		ResultSet rs = ps.executeQuery();
		List<String> list = new ArrayList<String>();
		while (rs.next()) {
			String tableName = rs.getString(1);
			list.add(tableName);
		}
		rs.close();
		ps.close();
		con.close();
		return list;
	}

	/**
	 * 表字段 注释
	 *
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public List<ColumnData> getColumnDatas(String tableName) throws Exception {
		Connection con = getConnection();
		String SQLColumns = "";
		if (databaseName.equals(ORACLE)) {
			SQLColumns = "select t.column_name,t.data_type,c.comments,DECODE(a.constraint_type,'P','PRI') from user_tab_columns t "
					+ "INNER JOIN user_col_comments c  on t.table_name=c.table_name and t.column_name=c.column_name "
					+ "left  join (select cu.column_name,CU.table_name,au.constraint_type from user_cons_columns cu, user_constraints au "
					+ "where cu.constraint_name = au.constraint_name and au.constraint_type = 'P' ) a  on t.table_name=a.table_name and t.column_name=a.column_name ";
			SQLColumns += "where t.table_name='" + tableName.toUpperCase() + "' order by LAST_ANALYZED";
		} else if (databaseName.equals(MYSQL)) {
			// mysql 表名相同的两个库需要按库查询
			SQLColumns = "SELECT column_name,data_type,column_comment,column_key from information_schema.COLUMNS t  where t.TABLE_NAME='"
					+ tableName + "' and t.TABLE_SCHEMA='" + this.database + "'";
		}
		PreparedStatement ps = con.prepareStatement(SQLColumns);
		List<ColumnData> columnList = new ArrayList<ColumnData>();
		ResultSet rs = ps.executeQuery();
		StringBuilder str = new StringBuilder();
		StringBuilder getset = new StringBuilder();
		while (rs.next()) {
			String clumnName = rs.getString(1);
			String dataType = rs.getString(2);
			String columnComment = rs.getString(3);
			String columnKey = rs.getString(4);
			ColumnData cd = new ColumnData();
			cd.setColumnName(clumnName);
			cd.setPropName(StringUtil.column2java(clumnName, Constant.EXP));
			cd.setDataType(getType(dataType));
			cd.setJdbcType(getJdbcType(dataType));
			cd.setColumnComment(columnComment == null ? "" : columnComment);
			cd.setPri(PRI.equals(columnKey) ? true : false);
			columnList.add(cd);
		}
		this.argv = str.toString();
		this.method = getset.toString();
		rs.close();
		ps.close();
		con.close();
		return columnList;
	}

	/**
	 * 属性 get set 方法
	 *
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public String getBeanFeilds(String tableName) throws Exception {
		List<ColumnData> dataList = getColumnDatas(tableName);
		StringBuilder str = new StringBuilder();
		StringBuilder getset = new StringBuilder();
		for (ColumnData d : dataList) {
			String columnName = d.getColumnName();
			String name = StringUtil.column2java(columnName, Constant.EXP);
			String type = d.getDataType();
			String comment = d.getColumnComment();
			if (StringUtils.isNotEmpty(comment)) {
				str.append("\r\t/** ").append(comment).append(" */");
			}
			String maxChar = name.substring(0, 1).toUpperCase();
			// str.append("\r\t").append("@ExcelProperty(\"").append(comment).append("\")");
			// str.append("\r\t").append("@JSONField(name=\"").append(columnName.toLowerCase()).append("\")");
			str.append("\r\t").append("@Column(name =\"").append(columnName).append("\")");
			// str.append("\r\t").append("@ApiModelProperty(\"").append(comment).append("\")");
			str.append("\r\t").append("private ").append(type + " ").append(name).append(";");
//			String method = maxChar + name.substring(1, name.length());
//			getset.append("\r\r\t").append("public ").append(type + " ").append("get" + method + "() {\r\t");
//			getset.append("    return this.").append(name).append(";\r\t}");
//			getset.append("\r\r\t").append("public void ").append("set" + method + "(" + type + " " + name + ") {\r\t");
//			getset.append("    this." + name + " = ").append(name).append(";\r\t}");
		}
		this.argv = str.toString();
//		this.method = getset.toString();
		return this.argv + this.method;
	}

	/**
	 * 属性 get set 方法
	 *
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public String getBeanVOFeilds(String tableName) throws Exception {
		List<ColumnData> dataList = getColumnDatas(tableName);
		StringBuilder str = new StringBuilder();
		StringBuilder getset = new StringBuilder();
		for (ColumnData d : dataList) {
			String columnName = d.getColumnName();
			String name = StringUtil.column2java(columnName, Constant.EXP);
			String type = d.getDataType();
			String comment = d.getColumnComment();
			if (StringUtils.isNotEmpty(comment)) {
				str.append("\r\t/** ").append(comment).append(" */");
			}
			String maxChar = name.substring(0, 1).toUpperCase();
			// str.append("\r\t").append("@ExcelProperty(\"").append(comment).append("\")");
			// str.append("\r\t").append("@JSONField(name=\"").append(columnName.toLowerCase()).append("\")");
			str.append("\r\t").append("@Column(name =\"").append(columnName).append("\")");
			if (StringUtils.isNotEmpty(comment)) {
				str.append("\r\t").append("@ApiModelProperty(\"").append(comment).append("\")");
			}
			str.append("\r\t").append("private ").append(type + " ").append(name).append(";");
//			String method = maxChar + name.substring(1, name.length());
//			getset.append("\r\r\t").append("public ").append(type + " ").append("get" + method + "() {\r\t");
//			getset.append("    return this.").append(name).append(";\r\t}");
//			getset.append("\r\r\t").append("public void ").append("set" + method + "(" + type + " " + name + ") {\r\t");
//			getset.append("    this." + name + " = ").append(name).append(";\r\t}");
		}
		this.argv = str.toString();
//		this.method = getset.toString();
		return this.argv + this.method;
	}

	/**
	 * 设置 属性默认值 用于设置测试
	 *
	 * @return
	 * @throws Exception
	 */
	public String getSetDefaultValue(String classLowerName, String tableName) throws Exception {
		List<ColumnData> dataList = getColumnDatas(tableName);
		StringBuilder str = new StringBuilder();
		for (ColumnData d : dataList) {
			if (d.isPri()) {
				continue;
			}
			String name = StringUtil.column2java(d.getColumnName(), Constant.EXP);
			String maxChar = name.substring(0, 1).toUpperCase();
			String method = maxChar + name.substring(1, name.length());
			str.append("\t").append(classLowerName).append(".set").append(method).append("(")
					.append(getDefaultValueByType(d.getDataType(), d.getColumnComment())).append(");\r\t");
		}
		return str.toString();
	}

	/**
	 * 根据类型返回默认值 用于设置测试
	 *
	 * @param type
	 * @return
	 */
	public String getDefaultValueByType(String type, String defVal) {
		String value = "\"\"";
		if ("String".equals(type)) {
			value = "\"" + defVal + "\"";
		} else if ("Integer".equals(type) || "Long".equals(type)) {
			value = "10";
		} else if ("Double".equals(type)) {
			value = "10.1";
		} else if ("Date".equals(type)) {
			value = "new Date()";
		}
		return value;
	}

	/**
	 * 属性对应注释
	 *
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public String getPropComment(String tableName) throws Exception {
		List<ColumnData> dataList = getColumnDatas(tableName);
		StringBuilder str = new StringBuilder();
		for (ColumnData d : dataList) {
			str.append(d.getPropName()).append("：").append(d.getColumnComment()).append("\r");
		}
		str.append("\r\r");
		for (ColumnData d : dataList) {
			str.append(d.getPropName()).append("\r");
		}
		str.append("\r\r");
		for (ColumnData d : dataList) {
			str.append(d.getColumnComment()).append("\r");
		}
		str.append("\r\r");
		str.append("{\r");
		for (ColumnData d : dataList) {
			str.append("\"").append(d.getPropName()).append("\":").append("\"").append(d.getColumnComment())
					.append("\",\r");
		}
		str.append("}");
		return str.toString();
	}

	/**
	 * 根据 数据库类型 返回 java 数据类型
	 *
	 * @param type
	 * @return
	 */
	public String getType(String type) {
		type = type.toLowerCase();
		if (("char".equals(type)) || ("varchar".equals(type)) || ("varchar2".equals(type)) || ("text".equals(type))
				|| ("clob".equals(type)))
			return "String";
		if (("int".equals(type)) || ("number".equals(type)))
			return "Integer";
		if ("bigint".equals(type))
			return "Long";
		if ("float".equals(type) || ("decimal").equals(type) || ("double").equals(type))
			return "Double";
		if ("tinyint".equals(type))
			return "Byte";
		if ((type.startsWith("timestamp")) || ("date".equals(type)) || ("datetime".equals(type))) {
			return "Date";
		}
		return "String";
	}

	/**
	 * 根据 数据库类型 返回 mapper jdbcType
	 *
	 * @param type
	 * @return
	 */
	public String getJdbcType(String type) {
		type = type.toLowerCase();
		if ("char".equals(type) || "varchar".equals(type) || "varchar2".equals(type) || "text".equals(type)
				|| "clob".equals(type))
			return "VARCHAR";
		if (("int".equals(type)) || ("number".equals(type)))
			return "INTEGER";
		if ("bigint".equals(type))
			return "BIGINT";
		if ("float".equals(type))
			return "FLOAT";
		if ("double".equals(type))
			return "DOUBLE";
		if ("decimal".equals(type))
			return "DECIMAL";
		if ("tinyint".equals(type))
			return "TINYINT";
		if (type.startsWith("timestamp") || "date".equals(type) || "datetime".equals(type)) {
			return "TIMESTAMP";
		}
		return "VARCHAR";
	}

	public void getPackage(int type, String createPath, String content, String packageName, String className,
			String extendsClassName, String[] importName) throws Exception {
		if (packageName == null) {
			packageName = "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(packageName).append(";\r");
		sb.append("\r");
		for (int i = 0; i < importName.length; i++) {
			sb.append("import ").append(importName[i]).append(";\r");
		}
		sb.append("\r");
		sb.append("/**\r *  entity. @author liubinwang Date:"
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\r */");
		sb.append("\r");
		sb.append("\rpublic class ").append(className);
		if (extendsClassName != null) {
			sb.append(" extends ").append(extendsClassName);
		}
		if (type == 1)
			sb.append(" ").append("implements java.io.Serializable {\r");
		else {
			sb.append(" {\r");
		}
		sb.append("\r\t");
		sb.append("private static final long serialVersionUID = 1L;\r\t");
		String temp = className.substring(0, 1).toLowerCase();
		temp = temp + className.substring(1, className.length());
		if (type == 1) {
			sb.append("private " + className + " " + temp + "; // entity ");
		}
		sb.append(content);
		sb.append("\r}");
		System.out.println(sb.toString());
		createFile(createPath, "", sb.toString());
	}

	public String getTablesNameToClassName(String tableName) {
		String[] split = tableName.toLowerCase().split("_");
		if (split.length > 1) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < split.length; i++) {
				String tempTableName = split[i].substring(0, 1).toUpperCase()
						+ split[i].substring(1, split[i].length());
				sb.append(tempTableName);
			}
			System.out.println(sb.toString());
			return sb.toString();
		}
		String tempTables = split[0].substring(0, 1).toUpperCase() + split[0].substring(1, split[0].length());
		return tempTables;
	}

	public void createFile(String path, String fileName, String str) throws IOException {
		FileWriter writer = new FileWriter(new File(path + fileName));
		writer.write(new String(str.getBytes("utf-8")));
		writer.flush();
		writer.close();
	}

	public Map<String, Object> getAutoCreateSql(String tableName) throws Exception {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		List<ColumnData> columnDatas = getColumnDatas(tableName);
		if (columnDatas.size() > 0) {
			String columns = getColumnSplit(columnDatas);
			// String columnsJava = getColumnSplitForJava(columnDatas);
			String[] columnList = getColumnList(columns);
			String columnFields = getColumnFields(columns);
			String insertColumnFields = columnFields.replace("A.", "");
			// String insert = "insert into " + tableName + "(" +
			// columns.replaceAll("\\|",
			// ",") + ")\n values(#{"
			// + columnsJava.replaceAll("\\|", "},#{") + "})";
			String insertSelectiveSql = getInsertSelectiveSql(columnDatas, tableName);
			String updateSelective = getUpdateSelectiveSql(tableName, columnDatas);
			String selectById = getSelectByIdSql(tableName, columnList);
			String delete = getDeleteSql(tableName, columnList);
			sqlMap.put("columnList", columnList);
			sqlMap.put("columnFields", columnFields);
			// sqlMap.put("insert", insert);
			sqlMap.put("insertSelectiveSql", insertSelectiveSql);
			sqlMap.put("insertColumnFields", insertColumnFields);
			sqlMap.put("insertBatchSql", getBatchSql(columnDatas));
			sqlMap.put("delete", delete);
			sqlMap.put("updateSelective", updateSelective);
			sqlMap.put("selectById", selectById);
		} else {
			System.out.println("没有表字段信息！！");
		}
		return sqlMap;
	}

	/**
	 * 新增 sql
	 *
	 * @param columnDatas
	 * @return
	 */
	public String getInsertSelectiveSql(List<ColumnData> columnDatas, String tableName) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ").append(tableName);
		sb.append("\r	<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
		for (ColumnData columnData : columnDatas) {
			String columnName = columnData.getColumnName();
			String propName = columnData.getPropName();
			if ("create_date".equalsIgnoreCase(columnName) || "create_datetime".equalsIgnoreCase(columnName)
					|| "create_time".equalsIgnoreCase(columnName)) {
				sb.append("\r			").append(columnName).append(",");
			} else {
				sb.append("\r		<if test=\"").append(propName).append(" != null");
				if ("String" == columnData.getDataType()) {
					sb.append(" and ").append(propName).append(" != ''");
				}
				sb.append("\">");
				sb.append("\r			").append(columnName).append(",");
				sb.append("\r		</if>");
			}

		}
		sb.append("\r	</trim>");
		sb.append("\r	<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
		for (ColumnData columnData : columnDatas) {
			String columnName = columnData.getColumnName();
			String propName = columnData.getPropName();
			if ("create_date".equalsIgnoreCase(columnName) || "create_datetime".equalsIgnoreCase(columnName)
					|| "create_time".equalsIgnoreCase(columnName)) {
				if (databaseName.equals(ORACLE)) {
					sb.append("\r\t	   SYSDATE,	");
				} else if (databaseName.equals(MYSQL)) {
					sb.append("\r\t	   NOW(),	");
				}
			} else {
				sb.append("\r		<if test=\"").append(propName).append(" != null");
				if ("String" == columnData.getDataType()) {
					sb.append(" and ").append(propName).append(" != ''");
				}
				sb.append("\">");
				sb.append("\r			#{").append(propName).append(", jdbcType = ").append(columnData.getJdbcType())
						.append("},");
				sb.append("\r		</if>");
			}
		}
		sb.append("\r	</trim>");
		return sb.toString();
	}

	public String getDeleteSql(String tableName, String[] columnsList) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append("\t FROM ").append(tableName).append(" A WHERE ");
		sb.append(columnsList[0]).append(" = #{").append(StringUtil.column2java(columnsList[0], Constant.EXP))
				.append("}");
		return sb.toString();
	}

	public String getSelectByIdSql(String tableName, String[] columnsList) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT <include refid=\"Base_Column_List\" /> \n");
		sb.append("\t FROM ").append(tableName).append(" A WHERE ");
		sb.append(columnsList[0]).append(" = #{")
				.append(StringUtil.column2java(columnsList[0].replace("A.", ""), Constant.EXP)).append("}");
		return sb.toString();
	}

	public String getColumnFields(String columns) throws SQLException {
		String fields = columns;
		if ((fields != null) && (!"".equals(fields))) {
			fields = fields.replaceAll("[|]", ",");
		}
		return fields;
	}

	public String[] getColumnList(String columns) throws SQLException {
		String[] columnList = columns.split("[|]");
		return columnList;
	}

	public String getUpdateSelectiveSql(String tableName, List<ColumnData> columnList) throws Exception {
		// 乐观锁增加版本号字段控制
		boolean isVersion = false;
		StringBuilder sb = new StringBuilder();
		ColumnData priCd = null;
		sb.append("\t<trim  suffixOverrides=\",\" >\n");
		for (int i = 0; i < columnList.size(); i++) {
			ColumnData data = columnList.get(i);
			if (data.isPri()) {
				priCd = data;
				continue;
			}
			String prop = data.getPropName();

			String columnName = data.getColumnName();
			if ("VERSION".equalsIgnoreCase(columnName)) {
				isVersion = true;
				sb.append("\t<if test=\"").append(prop).append(" != null\">\n\t\t ");
				sb.append(columnName + " = " + columnName + "+1, ");
				sb.append("\n\t</if>\n");
			} else if ("update_date".equalsIgnoreCase(columnName) || "update_datetime".equalsIgnoreCase(columnName)
					|| "update_time".equalsIgnoreCase(columnName) || "modify_date".equalsIgnoreCase(columnName)
					|| "modify_datetime".equalsIgnoreCase(columnName) || "modify_time".equalsIgnoreCase(columnName)) {
				if (databaseName.equals(ORACLE)) {
					sb.append("\t\t" + columnName + " = SYSDATE,\n");
				} else if (databaseName.equals(MYSQL)) {
					sb.append("\t\t" + columnName + " = NOW(),\n");
				}
			} else {
				sb.append("\t<if test=\"").append(prop).append(" != null\">\n\t\t ");
				sb.append(columnName + " = #{" + prop + ", jdbcType = " + data.getJdbcType() + "},");
				sb.append("\n\t</if>\n");
			}
		}
		sb.append("\t</trim>");
		if (priCd == null) {
			priCd = columnList.get(0);
		}
		String update = "UPDATE " + tableName + " SET \n" + sb.toString() + " \n \t\tWHERE " + priCd.getColumnName()
				+ " = #{" + priCd.getPropName() + ",jdbcType = " + priCd.getJdbcType() + "}  ";
		if (isVersion) {
			update += " AND VERSION = #{version}";
		}
		return update;
	}

	public String getColumnSplit(List<ColumnData> columnList) throws Exception {
		StringBuilder commonColumns = new StringBuilder();
		for (ColumnData data : columnList) {
			commonColumns.append("A." + data.getColumnName() + "|");
		}
		return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
	}

	public String getColumnSplitForJava(List<ColumnData> columnList) throws Exception {
		StringBuilder commonColumns = new StringBuilder();
		for (ColumnData data : columnList) {
			commonColumns.append(StringUtil.column2java(data.getColumnName(), Constant.EXP) + "|");
		}
		return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
	}

	/**
	 * 批量保存sql
	 * 
	 * @return
	 */
	private String getBatchSql(List<ColumnData> columnList) {
		StringBuilder sql = new StringBuilder();
		if (databaseName.equals(MYSQL)) {
			sql.append(" VALUES \r");
		}
		sql.append("\t\t<foreach collection=\"list\" item=\"item\" index=\"index\" ");
		if (databaseName.equals(MYSQL)) {
			sql.append("separator=\",\">\r\t\t(");
		} else if (databaseName.equals(ORACLE)) {
			sql.append("separator=\"UNION ALL\">\r \t\tSELECT");
		}
		int i = 0;
		int size = columnList.size();
		for (ColumnData columnData : columnList) {
			i++;
			String propName = columnData.getPropName();
			sql.append("#{item.").append(propName).append("}");
			if (i < size) {
				sql.append(",\r\t\t");
			}
		}
		if (databaseName.equals(MYSQL)) {
			sql.append(")\r");
		} else if (databaseName.equals(ORACLE)) {
			sql.append(" \t\tFROM DUAL");
		}
		sql.append("\t\t</foreach>");
		return sql.toString();

	}
}