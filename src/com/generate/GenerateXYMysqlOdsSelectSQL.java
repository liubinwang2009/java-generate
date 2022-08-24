package com.generate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.generate.utils.Constant;
import com.generate.utils.CreateBean;
import com.generate.utils.StringUtil;

public class GenerateXYMysqlOdsSelectSQL {

	public static void main(String[] args) {
		CreateBean bean = new CreateBean();
		String tableName = "ext_spt_warn";
		String database = "anti";
		String[] tableNames = tableName.split(",");
		try {

			bean.setConnectionInfo(
					"jdbc:mysql://192.168.1.200:9910/anti?useUnicode=true&characterEncoding=UTF-8", "root",
					"123456", database);
			Connection con = bean.getConnection();
			for (String tbName : tableNames) {
				String SQLColumns = "SELECT column_name,data_type,column_comment,CHARACTER_MAXIMUM_LENGTH from information_schema.COLUMNS t  where t.TABLE_NAME='"
						+ tbName + "' and t.TABLE_SCHEMA='" + database + "'";
				PreparedStatement ps = con.prepareStatement(SQLColumns);
				ResultSet rs = ps.executeQuery();
				StringBuffer cols = new StringBuffer();
				while (rs.next()) {
					String clumnName = rs.getString(1);	
					String comment = rs.getString(3);	
					if ("INSERT_TIME".equalsIgnoreCase(clumnName)) {
						continue;
					}
					if (cols.length() > 0) {
						cols.append(",");
					}
					cols.append(clumnName).append(" '").append(comment).append("'");
				}
			
				System.out.println(cols.toString());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
