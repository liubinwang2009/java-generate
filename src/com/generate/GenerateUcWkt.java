package com.generate;

import com.generate.utils.CreateJava;

public class GenerateUcWkt {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;

			url = "jdbc:mysql://localhost:3306/keycloak_uc_test?useUnicode=true&characterEncoding=UTF-8";
			database = "keycloak_uc_test";
			username = "root";
			passWord = "root";
			tableNames = "t_user_info,t_role,t_client_role";
			entityNames = "UserInfo,Role,ClientRole";
			comment = "用户信息,角色,客户端角色";
			pckPath = "com.govnet.uc";
			templateName = "base";

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
