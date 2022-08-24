package com.generate;

import com.generate.utils.CreateJava;

public class GenerateXYmysql {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;

			url = "jdbc:mysql://192.168.1.25:3306/prairie_fire_ads?useUnicode=true&characterEncoding=UTF-8";
			database = "prairie_fire_ads";
			username = "root";
			passWord = "Govnet1@";
			tableNames = "saas_analysis,saas_analysis_detail";
			entityNames = "SaasAnalysis,SaasAnalysisDetail";
			comment = "联网分析,联网分析详情";
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
