package com.generate;

import com.generate.utils.CreateJava;

public class GenerateSmart {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;

			url = "jdbc:mysql://192.168.3.206:3306/cloud_simulation?useUnicode=true&characterEncoding=UTF-8";
			database = "cloud_simulation";
			username = "root";
            passWord = "govnet123";
			tableNames = "tb_payunion_record";
			entityNames = "PayUnionRecord";
			comment = "银行卡付款记录";
			pckPath = "com.gnt.cso";
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
