package com.generate;

import com.generate.utils.CreateJava;

public class GenerateContent {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;

			url = "jdbc:mysql://116.62.167.158:3306/content?useUnicode=true&characterEncoding=UTF-8";
			database = "content";
			username = "root";
            passWord = "$%(*&@kOPez";
			tableNames = "police_travel";
			entityNames = "PoliceTravel";
			comment = "差旅";
			pckPath = "com.qwrt.police.content";
			templateName ="wzsh";

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
