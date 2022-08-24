package com.generate.utils;

import java.util.Map;

import org.apache.velocity.VelocityContext;

public class CreateJava {

	private static final String TEMPLATE_BASE = "base";

	/**
	 * 
	 * @param url
	 *            数据库连接
	 * @param username
	 *            用户名
	 * @param passWord
	 *            密码
	 * @param tableNames
	 *            表名 多个以,隔开
	 * @templateName 模板文档名称 
	 * @param pckPath
	 *            包名 路径
	 * @param comment 类注释  多个以,隔开
	 * @throws Exception
	 */
	public void makecode(String url, String username, String passWord, String database, String tableNames,
			String templateName, String pckPath, String comment) throws Exception {
		makecode(url, username, passWord, database, tableNames, "", templateName, pckPath, comment);
	}

	/**
	 * 
	 * @param url
	 *            数据库连接
	 * @param username
	 *            用户名
	 * @param passWord
	 *            密码
	 * @param tableNames
	 *            表名 多个以,隔开
	 * @param entityNames
	 *            类名 多个以,隔开 与表名一一对应 可不传，以表名命名类名
	 * @templateName 模板文档名称 
	 * @param pckPath
	 *            包名 路径
	 * @param comment 类注释  多个以,隔开
	 * @throws Exception
	 */
	public void makecode(String url, String username, String passWord, String database, String tableNames,
			String entityNames, String templateName, String pckPath, String comment) throws Exception {
		String modelPath = "\\domain\\";
		String mapperPath = "\\mapper\\";
		String sqlMapperPath = "\\mapper\\";
		String servicePath = "\\service\\";
		String testPath = "\\test\\";
		String controllerPath = "\\controller\\";
		makecode(url, username, passWord, database, tableNames, entityNames, templateName, pckPath, modelPath,
				mapperPath, sqlMapperPath, servicePath, testPath, controllerPath, comment);
	}

	/**
	 * 
	 * @param url
	 *            数据库连接
	 * @param username
	 *            用户名
	 * @param passWord
	 *            密码
	 * @param tableNames
	 *            表名 多个以,隔开
	 * @param entityNames
	 *            类名 多个以,隔开 与表名一一对应
	 * @templateName 模板文档名称 
	 * @param pckPath
	 *            包名 路径
	 * @param modelPath
	 *            model 路径
	 * @param mapperPath
	 *            mapper 路径
	 * @param sqlMapperPath
	 *            sql mapper 路径
	 * @param servicePath
	 *            service 路径
	 * @param testPath
	 *            test 路径
	 * @param controllerPath
	 *            controller 路径
	 * @param comment 类注释  多个以,隔开
	 * @throws Exception
	 */
	public void makecode(String url, String username, String passWord, String database, String tableNames,
			String entityNames, String templateName, String pckPath, String modelPath, String mapperPath,
			String sqlMapperPath, String servicePath, String testPath, String controllerPath, String comment)
			throws Exception {
		CreateBean createBean = new CreateBean();
		createBean.setConnectionInfo(url, username, passWord, database);

		String[] tableList = tableNames.split(",");
		String[] entityList = entityNames.split(",");
		String[] commentList = comment.split(",");

		for (int i = 0; i < tableList.length; i++) {
			String entity = "";
			if (entityList.length >= i + 1) {
				entity = entityList[i];
			}
			makecode(templateName, pckPath, modelPath, mapperPath, sqlMapperPath, servicePath, testPath, controllerPath,
					createBean, tableList[i].toUpperCase(), entity, commentList[i]);
		}
	}

	private void makecode(String templateName, String pckPath, String modelPath, String mapperPath,
			String sqlMapperPath, String servicePath, String testPath, String controllerPath, CreateBean createBean,
			String tableName, String className, String comment) throws Exception {
		if (StringUtil.isEmpty(className)) {
			className = createBean.getTablesNameToClassName(tableName);
		}
		String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());

		// 创建根目录 可在后面加src
		String rootPath = StringUtil.getRootPath();

		String model = modelPath + className + ".java";
		String modelDO = modelPath + "\\dto\\" + className + "DTO.java";
		String modelVO = modelPath + "\\vo\\" + className + "VO.java";
		String modelQuery = modelPath + "\\query\\" + className + "Query.java";
		String modelEditQuery = modelPath + "\\query\\" + className + "EditQuery.java";
		String sqlMapper = sqlMapperPath + className + "Mapper.xml";
		String mapper = mapperPath + className + "Mapper.java";
		String service = servicePath + className + "Service.java";
		String serviceImpl = servicePath + "\\impl\\" + className + "ServiceImpl.java";
		String test = testPath + "Test" + className + ".java";
		String controller = controllerPath + className + "Controller.java";
		String facade = "\\facade\\" + className + "Facade.java";
		String facadeImpl = "\\facade\\impl\\" + className + "FacadeImpl.java";
		String dubboTest = "\\test\\Dubbo" + className + "Test.java";
		String entityComment = "\\comment\\" + className + "Comment.txt";

		VelocityContext context = new VelocityContext();
		context.put("className", className);
		context.put("lowerName", lowerName);
		context.put("tableName", tableName);
		context.put("package", pckPath);
		context.put("comment", comment);
		try {
			context.put("feilds", createBean.getBeanFeilds(tableName));
			context.put("Vofeilds", createBean.getBeanVOFeilds(tableName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Map sqlMap = createBean.getAutoCreateSql(tableName);
			context.put("columnDatas", createBean.getColumnDatas(tableName));
			context.put("fieldDatas", createBean.getColumnDatas(tableName));
			context.put("SQL", sqlMap);
			context.put("setDefaultValue", createBean.getSetDefaultValue(lowerName, tableName));
			context.put("entityPropComment", createBean.getPropComment(tableName));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		String buildpath = rootPath + pckPath.replace(".", "/");

		if (templateName == null) {
			templateName = TEMPLATE_BASE;
		}

		CommonPageParser.WriterPage(context, templateName + "\\TempModel.java.tpl", buildpath, model);
		CommonPageParser.WriterPage(context, templateName + "\\TempModelEntity.java.tpl", buildpath, model);
		CommonPageParser.WriterPage(context, templateName + "\\TempModelDTO.java.tpl", buildpath, modelDO);
		CommonPageParser.WriterPage(context, templateName + "\\TempModelVO.java.tpl", buildpath, modelVO);
		CommonPageParser.WriterPage(context, templateName + "\\TempModelQuery.java.tpl", buildpath, modelQuery);
		CommonPageParser.WriterPage(context, templateName + "\\TempModelEditQuery.java.tpl", buildpath, modelEditQuery);
		CommonPageParser.WriterPage(context, templateName + "\\TempMapper.java.tpl", buildpath, mapper);
		CommonPageParser.WriterPage(context, templateName + "\\TempService.java.tpl", buildpath, service);
		CommonPageParser.WriterPage(context, templateName + "\\TempServiceImpl.java.tpl", buildpath, serviceImpl);
		CommonPageParser.WriterPage(context, templateName + "\\TempMapper.xml.tpl", buildpath, sqlMapper);
		CommonPageParser.WriterPage(context, templateName + "\\TempTest.java.tpl", buildpath, test);
		CommonPageParser.WriterPage(context, templateName + "\\TempController.java.tpl", buildpath, controller);
		CommonPageParser.WriterPage(context, templateName + "\\TempFacade.java.tpl", buildpath, facade);
		CommonPageParser.WriterPage(context, templateName + "\\TempFacadeImpl.java.tpl", buildpath, facadeImpl);
		CommonPageParser.WriterPage(context, templateName + "\\TempDubboTest.java.tpl", buildpath, dubboTest);
		CommonPageParser.WriterPage(context, templateName + "\\TempEntityComment.txt.tpl", buildpath, entityComment);
	}
}