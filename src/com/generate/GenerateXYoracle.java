package com.generate;

import com.generate.utils.CreateJava;

public class GenerateXYoracle {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;

			url = "jdbc:oracle:thin:@192.168.1.250:1521:pt";
			database = "antifraud";
			username = "antifraud";
            passWord = "antifraud";
			//tableNames = "ANTI_APPANALY_SERVER,ANTI_FRAUD_APKINFO,ANTI_FRAUD_APKINFO_TYPE,ANTI_APPANALY_3TH_SERVICE,SANDBOX_CAP_NETWORK,EXT_SPT_WARN,HZ_GWRY_SUMMARY,ANTI_FRAUD_YPR";
			//entityNames = "AntiFraudDomain,AntiFraudApkinfo,AntiFraudApkinfoType,AntiAppanaly3thService,SandboxCapNetwork,SptWarn,GwrySummary,AntiFraudYpr";
			//comment = "分析结果-APP/域名,涉诈APK,涉诈APK关联,App分析第三方服务,模拟器网络数据包,省平台预警,高危人员,签收信息";
			
//			tableNames = "ANTI_FRAUD_YPR,ANTI_FRAUD_YPR_AUDIT,ANTI_APPANALY_SERVER,ANTI_FRAUD_TECH_LOG";
//			entityNames = "AntiFraudYpr,AntiFraudYprAudit,AntiFraudDomain,AntiFraudTechLog";
//			comment = "技术员签收信息,技术员审核,分析结果-APP/域名,技术员操作日志";
			
//			tableNames = "ANTI_FRAUD_TECH_LOG,SYS_CONFIG,JZ_CASE_BASIC";
//			entityNames = "AntiFraudTechLog,SysConfig,CaseBasic";
//			tableNames = "ADS_CARD_RECORD_BASE,ADS_CLUE_CARD_INFO,ADS_BREAK_CARD_PERSON,ADS_BANK_SITE";
//			entityNames = "CardRecordBase,ClueCardInfo,BreakCardPerson,BankSite";
//			comment = "登记基本信息,两卡管控预警信息,断卡人员,网点";
//			
//			tableNames = "ADS_BASE_PERSON,ADS_BASE_PERSON_LABEL,ADS_BASE_PERSON_TURN,ADS_BASE_PERSON_GWRY,HZ_GWRY_SUMMARY";
//			entityNames = "BasePerson,BasePersonLabel,BasePersonTurn,BasePersonGwry,GwrySummary";
//			comment = "人员,人员标签,人员移交,人员与易受害骗人,易受骗人群";
			
//			tableNames = "ADS_PUBLICITY_TASK,ADS_PUBLICITY_TASK_PERSON,ADS_PUBLICITY_TASK_FEEDBACK,ADS_EASY_FRAUD_FEEDBACK";
//			entityNames = "PublicityTask,PublicityTaskPerson,PublicityTaskFeedback,EasyFraudFeedback";
//			comment = "反诈宣传任务,反诈宣传任务督导人员,反诈宣传任务反馈,易受骗人群反馈";
			
			
			tableNames = "ADS_WARN_CALL_RECORD";
			entityNames = "WarnCallRecord";
			comment = "预警劝阻电话记录";
			pckPath = "com.gnt.antifraud";
			templateName ="base";

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
