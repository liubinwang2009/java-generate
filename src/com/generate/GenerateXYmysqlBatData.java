package com.generate;

import com.generate.utils.CreateJava;

public class GenerateXYmysqlBatData {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;

			url = "jdbc:mysql://192.168.1.200:9910/bat_base_data_v1.6?useUnicode=true&characterEncoding=UTF-8";
			database = "bat_base_data_v1.6";
			username = "root";
			passWord = "123456";
			tableNames = "anti_service_company_conf";
			entityNames = "AntiServiceCompanyConf";
			comment = "公司服务配置";
			pckPath = "com.gnt.antifraud";
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
