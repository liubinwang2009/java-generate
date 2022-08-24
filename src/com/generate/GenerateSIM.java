package com.generate;

import com.generate.utils.CreateJava;

public class GenerateSIM {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;

			url = "jdbc:mysql://localhost:3306/smart-portal2?useUnicode=true&characterEncoding=UTF-8";
			database = "smart-portal2";
			username = "root";
			passWord = "root";
			tableNames = "t_app_system,t_app_system_function,t_category,t_attachment,t_sys_log,t_sys_admin_log";
			entityNames = "AppSystem,AppSystemFunction,Category,Attachment,SysLog,SysAdminLog";
			comment = "应用系统,应用功能,应用系统类型,附件,系统日志,管理日志";
			pckPath = "com.govnet.module.admin";
			templateName = "wzsh";

			String modelPath = "\\domain\\";
			String mapperPath = "\\dao\\";
			String sqlMapperPath = "\\mapper\\";
			String servicePath = "\\service\\";
			String testPath = "\\test\\";
			String controllerPath = "\\controller\\";

			generator.makecode(url, username, passWord, database, tableNames, entityNames, templateName, pckPath,
					modelPath, mapperPath, sqlMapperPath, servicePath, testPath, controllerPath, comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
