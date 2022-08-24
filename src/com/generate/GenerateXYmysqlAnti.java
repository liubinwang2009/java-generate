package com.generate;

import com.generate.utils.CreateJava;

public class GenerateXYmysqlAnti {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;

			url = "jdbc:mysql://192.168.1.200:9910/anti?useUnicode=true&characterEncoding=UTF-8";
			database = "anti";
			username = "root";
			passWord = "123456";
//			tableNames = "SAAS_DOMAIN_EXTRA,SAAS_IP_EXTRA,SAAS_3TH_CO,SAAS_ENTITY_APK,dws_device_wifi_ip_history,ads_judge_case_var_ref";
//			entityNames = "SaasDomainExtra,SaasIpExtra,Saas3thCo,SaasEntityApk,DeviceWifiIpHistory,JudeCaseVarRef";
//			comment = "域名扩展,IP扩展,第三文扩展信息,APK扩展信息,设备wifi/ip链接历史记录,案件关联信息";
//			tableNames="ext_sf_person_address,anti_fraud_app_analy,EXT_MOB_FOUR_CODE_KEY,ext_alarm_ck_zk,saas_app_static,saas_app_clue_static,DWS_ENTITY_APK,SAAS_ANALYSIS_DETAIL";
//			entityNames="SfPersonAddress,AntiFraudAppAnaly,MobFourCodeKey,AlarmCkZk,SaasAppStatic,SaasAppClueStatic,DwsEntityApk,AnalysisDetail";
//			comment="顺丰人员地址,APP分析记录,安装APP4码,预警常口暂口翻译,要素APP统计,要素APP线索统计,APK扩展,APP要素详情";
//			tableNames = "ANTI_APPANALY_SERVER,SANDBOX_DOMAIN_EXTRA,SANDBOX_FILING_EXTRA,SANDBOX_IP_EXTRA,SANDBOX_PARSE_CDN,SANDBOX_REMOTE_3TH_CO,SANDBOX_HTTPS_CERTIFICATE,SANDBOX_CERT_EXTRA,SANDBOX_WEB_FINGERPRINT,SANDBOX_REMOTE_EXTRA,SANDBOX_FILE";
//			entityNames = "AntiFraudDomain,SandboxDomainExtra,SandboxFilingExtra,SandboxIpExtra,SandboxParseCdn,SandboxRemote3thCo,SandboxHttpsCertificate,SandboxCertExtra,SandboxWebFingerprint,SandboxRemoteExtra,SandboxFile";
//			comment = "智能分析结果--应用服务器信息,域名扩展信息,备案信息,IP扩展信息,CDN信息,服务器面板,HTTPS证书,证书信息拓展,WEB指纹,SSH信息,非检材的文件";
			tableNames = "saas_analysis_static_01,saas_analysis_static_detail_01,dic_city_county";
			entityNames = "SaasAnalysisStatic,SaasAnalysisStaticDetail,DicCityCounty";
			comment = "要素分析统计,要素分析统计详情,城市区县";
		
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
