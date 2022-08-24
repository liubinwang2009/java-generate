package com.generate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.generate.utils.Constant;
import com.generate.utils.CreateBean;
import com.generate.utils.StringUtil;

public class GenerateXYMysqlOdsSQLColumn {

	public static void main(String[] args) {
		CreateBean bean = new CreateBean();
		String tableName = "EXT_YK_WIFI_COLLISION_OFFLINE";
		String database = "prairie_fire_ods";
		String[] tableNames = tableName.split(",");
		boolean isGet=true;
		try {

			bean.setConnectionInfo(
					"jdbc:mysql://192.168.1.25:3306/prairie_fire_ods?useUnicode=true&characterEncoding=UTF-8", "root",
					"Govnet1@", database);
			Connection con = bean.getConnection();
			for (String tbName : tableNames) {
				String SQLColumns = "SELECT column_name,data_type,CHARACTER_MAXIMUM_LENGTH from information_schema.COLUMNS t  where t.TABLE_NAME='"
						+ tbName + "' and t.TABLE_SCHEMA='" + database + "'";
				PreparedStatement ps = con.prepareStatement(SQLColumns);
				ResultSet rs = ps.executeQuery();
				StringBuffer insert = new StringBuffer();
				StringBuffer cols = new StringBuffer();
				StringBuffer megreInsertSql=new StringBuffer();
				StringBuffer megreUpdateSql=new StringBuffer();
				while (rs.next()) {
					String clumnName = rs.getString(1);	
					if ("INSERT_TIME".equalsIgnoreCase(clumnName)) {
						continue;
					}
					if (cols.length() > 0) {
						cols.append(",");
					}
					if (megreInsertSql.length() > 0) {
						megreInsertSql.append(".append(\",\");\r");
					}
					if (megreUpdateSql.length() > 0) {
						megreUpdateSql.append(".append(\",\");\r");
					}
					cols.append(clumnName);
					String name = StringUtil.column2java(clumnName, Constant.EXP);
					String maxChar = name.substring(0, 1).toUpperCase();
					String method = maxChar + name.substring(1, name.length());
					megreInsertSql.append("sql.append(SqlCommon.appendSql(");
					if(isGet) {
						megreInsertSql.append("obj.get").append(method).append("()");
					}else {
						megreInsertSql.append(name);
					}
					megreInsertSql.append("))");
					if ("RECORD_ID".equalsIgnoreCase(clumnName)) {
						continue;
					}
					megreUpdateSql.append("sql.append(\"").append(clumnName).append("=\").append(SqlCommon.appendSql(");
					if(isGet) {
						megreUpdateSql.append("obj.get").append(method).append("()");
					}else {
						megreUpdateSql.append(name);
					}
					megreUpdateSql.append("))");
				}
				insert.append("StringBuilder sql=new StringBuilder();\rsql.append(\"INSERT INTO ").append(tbName.toUpperCase()).append("(").append(cols).append(") VALUES (\");\r");
				insert.append(megreInsertSql).append(";\r");
				insert.append("sql.append(\") ON DUPLICATE KEY UPDATE \"); \r");
				insert.append(megreUpdateSql).append(";");
				System.out.println(insert.toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
