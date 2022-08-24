package com.generate;

import com.generate.utils.CreateJava;

public class GenerateCeye {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {

			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;
			// mysql
			url = "jdbc:mysql://192.168.3.201:3306/biz_risk_data_1.0?useUnicode=true&characterEncoding=UTF-8";
			username = "biz_risk";
			passWord = "123456";
			database = "biz_risk_data_1.0";
			// tableNames =
			// "t_sw_event,t_sw_clue,t_sw_group,t_sw_event_allot,t_sw_group_event,t_sw_event_person,t_sw_group_person,t_sw_clue_person,t_common_file";
			// entityNames =
			// "SwEvent,SwClue,SwGroup,SwEventAllot,SwGroupEvent,SwEventPerson,SwGroupPerson,SwCluePerson,CommonFile";
			// comment = "事件,线索,群体,事件下发,群体事件关联,事件人员关联,群体人员关联,线索人员关联,文件";
			// tableNames="base_code,t_sw_event,t_sw_event_deal";
			// entityNames="BaseCode,SwEvent,SwEventDeal";
			// comment="字典,事件,事件处置";
			// tableNames =
			// "T_PERSON_BASIC_INFO,t_sw_clue,t_sw_group_clue,t_pdw_jwd";
			// entityNames = "PersonBasicInfo,SwClue,SwGroupClue,PdwJwd";
			// comment = "人员,线索,群体线索关联,省市经纬度";
			// tableNames =
			// "t_sw_event_deal,t_sw_clue,t_sw_clue_alarm,t_sw_clue_event";
			// entityNames = "SwEventDeal,SwClue,SwClueAlarm,SwClueEvent";
			// comment = "事件处置,线索,线索预警,线索事件关联";

			tableNames = "t_car_basic_info,t_car_relation,t_company_info,T_CAR_CONTROL";
			entityNames = "CarBasicInfo,CarRelation,CompanyInfo,CarControl";
			comment = "重点车辆基础信息,车辆关联,公司信息,车辆管控";
			pckPath = "com.govnet.risk";
			templateName = "ceye";

			String modelPath = "\\entity\\";
			String mapperPath = "\\dao\\";
			String sqlMapperPath = "\\mapper\\";
			String servicePath = "\\service\\";
			String testPath = "\\test\\";
			String controllerPath = "\\web\\";
			generator.makecode(url, username, passWord, database, tableNames, entityNames, templateName, pckPath,
					modelPath, mapperPath, sqlMapperPath, servicePath, testPath, controllerPath, comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
