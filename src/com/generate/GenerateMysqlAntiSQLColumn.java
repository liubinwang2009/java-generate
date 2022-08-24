package com.generate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.generate.utils.Constant;
import com.generate.utils.CreateBean;
import com.generate.utils.StringUtil;

public class GenerateMysqlAntiSQLColumn {

	public static void main(String[] args) {
		CreateBean bean = new CreateBean();
		String tableName = "ext_tx_intercept_app";
		String database = "anti";
		String[] tableNames = tableName.split(",");
		try {

			bean.setConnectionInfo(
					"jdbc:mysql://192.168.1.200:9910/anti?useUnicode=true&characterEncoding=UTF-8", "root",
					"123456", database);
			Connection con = bean.getConnection();
			for (String tbName : tableNames) {
				String SQLColumns = "SELECT column_name,data_type,CHARACTER_MAXIMUM_LENGTH from information_schema.COLUMNS t  where t.TABLE_NAME='"
						+ tbName + "' and t.TABLE_SCHEMA='" + database + "'";
				PreparedStatement ps = con.prepareStatement(SQLColumns);
				ResultSet rs = ps.executeQuery();
				System.out.println(
						"delete from anti_ga_push_field_conf where table_name='prairie_fire_ods." + tbName.toLowerCase() + "';");
				StringBuffer insert = new StringBuffer();
				StringBuffer cols = new StringBuffer();
				StringBuffer vals = new StringBuffer();
				StringBuffer update = new StringBuffer();
				StringBuffer megreInsertSql=new StringBuffer();
				StringBuffer megreUpdateSql=new StringBuffer();
				while (rs.next()) {
					String clumnName = rs.getString(1);
					String columnLength = rs.getString(3);
					if ("INSERT_TIME".equalsIgnoreCase(clumnName)) {
						continue;
					}
					if (cols.length() > 0) {
						cols.append(",");
					}
					if (vals.length() > 0) {
						vals.append(",");
					}
					cols.append(clumnName);
					String name = StringUtil.column2java(clumnName, Constant.EXP);
					String maxChar = name.substring(0, 1).toUpperCase();
					String method = maxChar + name.substring(1, name.length());
					vals.append("\"+SqlCommon.appendSql(obj.get").append(method).append("())+\"");
					megreInsertSql.append("sb.append(SqlCommon.appendSql(obj.get").append(method).append("())).append(\",\");\r");
					megreUpdateSql.append("sb.append(\"").append(clumnName).append("=\").append(SqlCommon.appendSql(obj.get").append(method).append("())).append(\",\");\r");
					if (!clumnName.equalsIgnoreCase("RECORD_ID")) {
		                if (update.length() > 0) {
		                    update.append(",");
		                }
		                update.append(clumnName).append("=").append("\"+SqlCommon.appendSql(obj.get").append(method).append("())+\"");
		            }
				  
					System.out.println("INSERT INTO `anti_ga_push_field_conf`(`table_name`, `field_name`, `field_type`, `field_length`, `defalut_value`, `is_encry`) VALUES ('" + database
							+ "." + tbName.toLowerCase() + "', '" + clumnName + "', 'VARCHAR', '" + columnLength + "', null, '0');");
				}
				System.out.println("\r");
				insert.append("INSERT INTO ").append(database).append(".").append(tbName.toUpperCase()).append("(").append(cols).append(") VALUES (").append(vals).append(")");
				insert.append(" ON DUPLICATE KEY UPDATE ").append(update);
				System.out.println(insert.toString());
				System.out.println("\r-------------------------------------------------------------");
				System.out.println(megreInsertSql.toString());
				System.out.println(megreUpdateSql.toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
