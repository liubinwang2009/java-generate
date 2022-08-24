package com.generate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.generate.utils.Constant;
import com.generate.utils.CreateBean;
import com.generate.utils.StringUtil;

public class GenerateOracleSQLColumn {

	public static void main(String[] args) {
		CreateBean bean = new CreateBean();
		String tableName = "ANTI_APPANALY_SERVER";
		String database = "antifraud";
		String[] tableNames = tableName.split(",");
		try {
			bean.setConnectionInfo(
					"jdbc:oracle:thin:@192.168.1.250:1521:pt", "antifraud",
					"antifraud", database);
			Connection con = bean.getConnection();
			for (String tbName : tableNames) {
				String SQLColumns = "select t.column_name,t.data_type,c.comments,DECODE(a.constraint_type,'P','PRI') from user_tab_columns t "
						+ "INNER JOIN user_col_comments c  on t.table_name=c.table_name and t.column_name=c.column_name "
						+ "left  join (select cu.column_name,CU.table_name,au.constraint_type from user_cons_columns cu, user_constraints au "
						+ "where cu.constraint_name = au.constraint_name and au.constraint_type = 'P' ) a  on t.table_name=a.table_name and t.column_name=a.column_name ";
				SQLColumns += "where t.table_name='" + tbName.toUpperCase() + "' order by LAST_ANALYZED";
				PreparedStatement ps = con.prepareStatement(SQLColumns);
				ResultSet rs = ps.executeQuery();
				StringBuffer cols = new StringBuffer();
				StringBuffer vals = new StringBuffer();
				StringBuffer update = new StringBuffer();
				StringBuffer megreInsertSql=new StringBuffer();
				StringBuffer megreUpdateSql=new StringBuffer();
				StringBuffer copyModel=new StringBuffer();
				while (rs.next()) {
					String clumnName = rs.getString(1);
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
					copyModel.append("target.set").append(method).append("(").append("source.get").append(method).append("());\r");
				}
				System.out.println(cols.toString());
				System.out.println("\rinsert ---------------------");
				System.out.println(megreInsertSql.toString());
				System.out.println("\rupdate --------------------------");
				System.out.println(megreUpdateSql.toString());
				System.out.println("\rcopy --------------------------");
				System.out.println(copyModel.toString());
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
