package com.generate;

import com.generate.utils.CreateJava;

public class Generate {

	public static void main(String[] args) {
		CreateJava generator = new CreateJava();
		try {
			String url = null, database = null, username = null, passWord = null, tableNames = null, entityNames = null,
					pckPath = null, templateName = null, comment = null;
			// mysql
			// String url =
			// "jdbc:mysql://192.168.3.206:3306/lbjr?useUnicode=true&characterEncoding=UTF-8";
			// String username = "root";
			// String passWord = "govnet123";
			// String database = "lbjr";
			// String tableNames =
			// "buy_fp_detail,buy_fp_person,user_info,card_blotters";
			// String entityNames =
			// "BuyFpDetail,BuyFpPerson,UserInfo,CardBlotters";
			// String pckPath = "com.govnet.hlwjr";

			// oracle
			// String url = "jdbc:oracle:thin:@222.83.251.210:1521:isdata";
			// String username = "wctest";
			// String passWord = "wctest";
			// String tableNames = "TB_WORK_TYPE";
			// String entityNames = "WorkType";
			// String pckPath = "com.govnet.worktype";

			// ali oracle
			// String url = "jdbc:oracle:thin:@192.168.101.32:1521:orcl";
			// String username = "WCPT";
			// String passWord = "utanra_i98p4biba51";
			// String tableNames = "tb_message_notice,tb_message_user";
			// String entityNames = "MessageNotice,MessageUser";
			// String pckPath = "com.cy.weicha.operate";
			// String database = "orcl";

//			url = "jdbc:oracle:thin:@192.168.1.2:1521:orclutf8";
//			database = "orclutf8";
//			username = "gvt";
//			passWord = "govnet123";
//			tableNames = "t_cis_pro_node,T_CIS_PRO_CLUE,T_CIS_PRO_NODE_RELATION";
//			entityNames = "Node,Clue,NodeRelation";
//			pckPath = "com.govnet.series";

			url = "jdbc:mysql://192.168.3.206:3306/manage?useUnicode=true&characterEncoding=UTF-8";
			database = "manage";
			username = "root";
            passWord = "govnet123";
			tableNames = "tb_flow,tb_flow_node,tb_flow_line,tb_approve,tb_approve_audit";
			entityNames = "Flow,FlowNode,FlowLine,Approve,ApproveAudit";
			comment = "流程,流程节点,流程线,审批,审批详情";
			pckPath = "com.govnet.manage";

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
