package com.generate;

import com.generate.utils.CreateJava;

public class GenerateXYmysqlOds {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;

			url = "jdbc:mysql://192.168.1.25:3306/prairie_fire_ods?useUnicode=true&characterEncoding=UTF-8";
			database = "prairie_fire_ods";
			username = "root";
			passWord = "Govnet1@";
			tableNames = "EXT_YK_APP,EXT_YK_WIFI_COLLISION,EXT_YK_WIFI,EXT_YK_IP,EXT_YK_SNS,EXT_YK_BLOCK_APP,EXT_YK_WIFI_COLLISION_OFFLINE,EXT_YK_DEVICE_APP";
			entityNames = "YkApp,YkWifiCollision,YkWifi,YkIp,YkSns,YkBlockApp,YkWifiCollisionOffline,YkDeviceApp";
			comment = "yk App,WIFI伴随人员,yk WIFI,yk IP查询,yk社交账号,yk阻断APP,yk WIFI同网关系人,yk设备APP";
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
