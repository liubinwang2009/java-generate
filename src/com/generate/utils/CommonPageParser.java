package com.generate.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class CommonPageParser {
	private static VelocityEngine ve;
	private static Properties properties;
	private static final String CONTENT_ENCODING = "UTF-8";
	private static final Log log = LogFactory.getLog(CommonPageParser.class);

	private static boolean isReplace = true;
	private static String templateBasePath;

	static {
		try {
			String pckPath = StringUtil.getRootPath();
			templateBasePath = pckPath + "template";
			Properties properties = new Properties();
			properties.setProperty("resource.loader", "file");
			properties.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
			properties.setProperty("file.resource.loader.path", templateBasePath);
			properties.setProperty("file.resource.loader.cache", "true");
			properties.setProperty("file.resource.loader.modificationCheckInterval", "30");
			properties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.Log4JLogChute");
			properties.setProperty("runtime.log.logsystem.log4j.logger", "org.apache.velocity");
			properties.setProperty("directive.set.null.allowed", "true");
			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.init(properties);
			ve = velocityEngine;
		} catch (Exception e) {
			log.error(e);
		}
	}

	public static void WriterPage(VelocityContext context, String templateName, String fileDirPath, String targetFile)
			throws ResourceNotFoundException, ParseErrorException, Exception {
		File templateNameFile = new File(templateBasePath +"\\"+ templateName);
		if (!templateNameFile.exists()) {
			return;
		}

		File file = new File(fileDirPath + targetFile);
		if (!file.exists()) {
			new File(file.getParent()).mkdirs();
		} else if (isReplace) {
			System.out.println("????????????" + file.getAbsolutePath());
		} else {
			System.out.println("??????????????????" + file.getAbsolutePath() + "???????????????");
			return;
		}

		Template template = ve.getTemplate(templateName, CONTENT_ENCODING);
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, CONTENT_ENCODING));
		template.merge(context, writer);
		writer.flush();
		writer.close();
		fos.close();
		System.out.println("??????????????????" + file.getAbsolutePath());
	}
}