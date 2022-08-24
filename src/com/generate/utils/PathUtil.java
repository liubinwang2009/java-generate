package com.generate.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class PathUtil {
	private static Logger log = Logger.getLogger(PathUtil.class);

	public static String getBasePath() {
		String str = Constant.BASE_URL;
		if (StringUtils.isNotBlank(str)) {
			try {
				return URLDecoder.decode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				log.error("获取根路径异常：", e);
			}
		}
		return str;
	}

	public static String getBasePath(String patth) {
		return getBasePath() + patth;
	}

	public static String typeUrl(Long typeId) {
		return getBasePath() + "type/" + typeId;
	}

	public static String listUrl(Integer pageId) {
		if ((pageId != null) && (pageId.intValue() < 1)) {
			pageId = Integer.valueOf(1);
		}
		return getBasePath() + "list/" + pageId;
	}

	public static String listTypeUrl(Long typeId, Integer pageId) {
		String url = null;
		if ((typeId != null) && (typeId.longValue() > 0L))
			url = typeUrl(typeId) + "/" + pageId;
		else {
			url = listUrl(pageId);
		}
		return url;
	}

	public static String getUploadThumbnailPath(String path, String cropArea) {
		if (StringUtils.isBlank(path)) {
			return "";
		}
		String postfix = path.substring(path.lastIndexOf("."), path.length());
		String temPath = path.substring(0, path.lastIndexOf("."));
		String newName = "." + cropArea + postfix;
		String newPath = temPath + newName;

		return newPath;
	}

	public static void main(String[] args) {
		String file = "E:\\Soft\\办公软件\\通行证\\100.jpg";
		getUploadThumbnailPath(file, "50_50");
	}

	public static String getWbUrl(Long id) {
		return getBasePath() + "view/" + id + ".shtml";
	}

	public static String getHeaderPath() {
		return "/header.shtml";
	}

	public static String getHeaderTempPath() {
		return "/header.shtml";
	}

	public static String getFooterPath() {
		return "/footer.shtml";
	}

	public static String getFooterTempPath() {
		return "/footer.shtml";
	}

	public static String getIndexPath() {
		return "/index.shtml";
	}

	public static String getIndexTempPath() {
		return "/index.shtml";
	}
}