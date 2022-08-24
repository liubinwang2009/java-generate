package com.generate;

import com.generate.utils.CreateJava;

public class GenerateXYmysqlSaas {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;

			url = "jdbc:mysql://192.168.1.25:3306/prairie_fire_ods?useUnicode=true&characterEncoding=UTF-8";
			database = "prairie_fire_ods";
			username = "root";
			passWord = "Govnet1@";
//			tableNames="EXT_MOB_FOUR_CODE_KEY,EXT_MOB_LOCATION,EXT_YK_DEVICE_APP,ext_yk_device_sns,EXT_MOB_PORTRAYAL";
//			entityNames="MobFourCodeKey,MobLocation,DeviceApp,DeviceSns,MopPortrayal";
//			comment="安装APP4码,轨迹信息,YK APP列表,YK社交账号,YK设备画像";
			
			tableNames="ext_yk_app_top_install,EXT_WT_CALL,EXT_WT_INTERNET";
			entityNames="AppTopInstall,WtCall,WtInternet";
			comment="YK app首装人群,无糖话单预警,无糖网站";
			
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
