package com.generate;

import com.generate.utils.CreateJava;

public class GenerateWzsh {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;

			url = "jdbc:mysql://192.168.3.206:3306/shcedb?useUnicode=true&characterEncoding=UTF-8";
			database = "shcedb";
			username = "root";
            passWord = "govnet123";
			tableNames = "clew_info,clew_against_person,code_dictionary,feedback_team,clew_feedback,clew_tag_map,sys_parameter,sys_log";
			entityNames = "ClewInfo,ClewAgainstPerson,CodeDict,FeedbackTeam,ClewFeedback,ClewTag,SysParameter,SysLog";
			comment = "线索,被举报人,字典,线索反馈参与人,线索反馈,线索标注,系统属性,系统日志";
			pckPath = "com.govnet.crime";
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
