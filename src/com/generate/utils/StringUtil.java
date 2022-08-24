package com.generate.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;

public class StringUtil {
	private static StringUtil instance = null;

	public static final String escapeForIntro(String string) {
		String str = string;
		str = replace(str, "\r\n", "<br>");
		str = replace(str, "\n", "<br>");
		str = replace(str, "'", "\\'");
		return replace(str, "\r", "");
	}

	public static String getNotNullStr(Object objValue) {
		return objValue == null ? "" : objValue.toString();
	}

	public static String getNotNullStr(String strValue) {
		return strValue == null ? "" : strValue.trim();
	}

	public static String fillZero(String str, int size) {
		String result;
		if (str.length() < size) {
			char[] s = new char[size - str.length()];
			for (int i = 0; i < size - str.length(); i++) {
				s[i] = '0';
			}
			result = new String(s) + str;
		} else {
			result = str;
		}
		return result;
	}

	public static String[] getStrArryByString(String str1) {
		if (str1.indexOf("\t") > 0) {
			for (int i = 0; i < str1.length(); i++) {
				if (str1.substring(i, i + 1).equals("\t")) {
					str1 = str1.substring(0, i) + " " + str1.substring(i + 1, str1.length());
				}
			}
		}
		StringTokenizer stringTokenizer = new StringTokenizer(str1, "\r\n");
		String[] strId = new String[stringTokenizer.countTokens()];
		int i = 0;
		while (stringTokenizer.hasMoreTokens()) {
			strId[i] = stringTokenizer.nextToken();
			i++;
		}
		return strId;
	}

	public static boolean isValid(String inStr) {
		if (inStr == null) {
			return false;
		}
		if (inStr.equals("")) {
			return false;
		}
		if (inStr.equals("null")) {
			return false;
		}

		return true;
	}

	public static boolean checkNotNull(String str) {
		boolean flag = false;

		if ((str != null) && (str.trim().length() != 0))
			flag = true;
		return flag;
	}

	public static String getStrSpace(int spaceNum) {
		return getStrWithSameElement(spaceNum, " ");
	}

	public static String getStrWithSameElement(int num, String element) {
		if (num <= 0) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append(element);
		}
		return sb.toString();
	}

	public static String getFillString(String strIn, int totalLength, boolean isRightAlign) {
		int spaceLength = 0;
		String spaces = null;
		String strOut = null;

		if (strIn == null) {
			strIn = "";
		}

		spaceLength = totalLength - strIn.length();

		if (spaceLength < 0) {
			spaceLength = 0;
		}
		spaces = getStrSpace(spaceLength);

		if (isRightAlign) {
			strOut = spaces + strIn;
		} else {
			strOut = strIn + spaces;
		}

		return strOut;
	}

	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		t.printStackTrace(pw);
		return sw.toString();
	}

	public static String getStrByUpperFirstChar(String str) {
		try {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		} catch (Exception localException) {
		}
		return "";
	}

	public static String getStrByLowerFirstChar(String str) {
		try {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		} catch (Exception localException) {
		}
		return "";
	}

	public static int getStrToInt(String strValue) {
		if (strValue == null) {
			return 0;
		}
		int iValue = 0;
		try {
			iValue = new Integer(strValue.trim()).intValue();
		} catch (Exception localException) {
			iValue = 0;
		}
		return iValue;
	}

	public static double getStrToDouble(String strValue) {
		if (strValue == null) {
			return 0.0D;
		}
		double dValue = 0.0D;
		try {
			dValue = Double.parseDouble(strValue.trim());
		} catch (Exception localException) {
			dValue = 0.0D;
		}
		return dValue;
	}

	public static short getStrToShort(String strValue) {
		if (strValue == null) {
			return 0;
		}
		short iValue = 0;
		try {
			iValue = new Short(strValue.trim()).shortValue();
		} catch (Exception localException) {
			iValue = 0;
		}
		return iValue;
	}

	public static long getStrToLong(String strValue) {
		if (strValue == null) {
			return 0L;
		}
		long lValue = 0L;
		try {
			lValue = new Long(strValue.trim()).longValue();
		} catch (Exception localException) {
			lValue = 0L;
		}
		return lValue;
	}

	public static String toLengthForEn(String str, int length) {
		if (str != null) {
			if (str.length() <= length) {
				return str;
			}

			str = str.substring(0, length - 2);
			str = str + "..";
			return str;
		}

		return "";
	}

	public static String toLengthForIntroduce(String str, int length) {
		str = delTag(str);

		byte[] strByte = str.getBytes();
		int byteLength = strByte.length;

		StringBuffer buff = new StringBuffer();
		if (byteLength > length * 2) {
			char[] charArray = str.toCharArray();
			int resultLength = 0;
			for (int i = 0; i < charArray.length; i++) {
				resultLength += String.valueOf(charArray[i]).getBytes().length;
				if (resultLength > length * 2) {
					break;
				}
				buff.append(charArray[i]);
			}

			buff.append("..");
			str = buff.toString();
		}

		str = replace(str, "\"", "\\\"");
		str = replace(str, "，", ",");
		return str;
	}

	public static String delTag(String str) {
		str = str + "<>";
		StringBuffer buff = new StringBuffer();
		int start = 0;
		int end = 0;

		while (str.length() > 0) {
			start = str.indexOf("<");
			end = str.indexOf(">");
			if (start > 0) {
				buff.append(str.substring(0, start));
			}
			if ((end > 0) && (end <= str.length())) {
				str = str.substring(end + 1, str.length());
			} else {
				str = "";
			}
		}

		String result = buff.toString();

		while (result.startsWith(" ")) {
			result = result.substring(result.indexOf(" ") + 1, result.length());
		}

		return result;
	}

	public static final String replace(String line, String oldString, String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	public static String Replace(String source, String oldString, String newString) {
		if (source == null) {
			return null;
		}
		StringBuffer output = new StringBuffer();
		int lengOfsource = source.length();
		int lengOfold = oldString.length();
		int posStart = 0;
		int pos = 0;
		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengOfold;
		}
		if (posStart < lengOfsource) {
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	public static String toHtml(String s) {
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		s = Replace(s, "\n", "<br>");

		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "%", "％");

		return s;
	}

	public static String unHtml(String s) {
		s = Replace(s, "<br>", "\n");

		return s;
	}

	public static String toHtmlBack(String s) {
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");

		return s;
	}

	public static String unHtmlBack(String s) {
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&gt;", ">");
		s = Replace(s, "    ", "\t");
		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&amp;", "&");
		s = Replace(s, "&#39;", "'");
		s = Replace(s, "&#92;", "\\");
		s = Replace(s, "％", "%");
		return s;
	}

	public static boolean containsChinese(String str) throws UnsupportedEncodingException {
		if (str.length() < str.getBytes().length) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		return "".equals(str.trim());
	}

	public static String[] split(String str1, String str2) {
		return StringUtils.split(str1, str2);
	}

	public static List<String> StringToList(String value, String exp) {
		List resultList = new ArrayList();
		String[] vals = split(value, exp);
		for (String val : vals) {
			resultList.add(val);
		}
		return resultList;
	}

	public static String arrayToString(String[] arrs) {
		StringBuffer result = new StringBuffer("");
		if ((arrs != null) && (arrs.length > 0)) {
			for (int i = 0; i < arrs.length; i++) {
				if (!result.toString().equals("")) {
					result.append(",");
				}
				if ((arrs[i] != null) && (!"".equals(arrs[i].trim()))) {
					result.append(arrs[i]);
				}
			}
		}
		return result.toString();
	}

	public static String left(String str, int length) {
		return StringUtils.left(str, length);
	}

	public static String replaceHuiche(String str) {
		String after = str.replaceAll("\r\n", "");
		return after;
	}

	public static String truncateStr(String str, int len) {
		if ((str != null) && (!"".equalsIgnoreCase(str))) {
			String[] strs = str.split(" ");
			StringBuffer buff = new StringBuffer();
			if (strs.length > 0) {
				for (int i = 0; i < strs.length; i++) {
					StringBuffer temp = new StringBuffer();
					while (strs[i].length() > len) {
						temp.append(strs[i].substring(0, len) + "<BR>");
						strs[i] = strs[i].substring(len);
					}
					temp.append(strs[i]);
					buff.append(temp.toString() + " ");
				}
			}

			return buff.toString();
		}

		return "";
	}

	public static String truncateStr2(String str, int len) {
		if ((str != null) && (!"".equalsIgnoreCase(str)) && (len != 0)) {
			String[] strs = str.split(" ");

			StringBuffer buff = new StringBuffer();
			for (int i = 0; i < strs.length; i++) {
				StringBuffer temp = new StringBuffer();
				String tempstr = "";
				while (strs[i].length() > len) {
					tempstr = strs[i].substring(0, len);
					tempstr = tempstr.replaceAll(" ", "&nbsp; ");
					tempstr = tempstr.replaceAll("<", "&lt; ");
					tempstr = tempstr.replaceAll("\n", "<br> ").replaceAll("\"", "&quot; ").replaceAll("'", "&#39; ");
					tempstr = tempstr + "<br>";
					temp.append(tempstr);

					strs[i] = strs[i].substring(len);
				}
				tempstr = strs[i];
				tempstr = tempstr.replaceAll(" ", "&nbsp; ");
				tempstr = tempstr.replaceAll("<", "&lt; ");
				tempstr = tempstr.replaceAll("\n", "<br> ").replaceAll("\"", "&quot; ").replaceAll("'", "&#39; ");

				temp.append(tempstr);
				buff.append(temp.toString() + " ");
			}

			if (buff.length() > 0) {
				return buff.toString().substring(0, buff.length() - 1);
			}
			return str;
		}

		return "";
	}

	public static String unicodeToGB(String l_S_Source) throws UnsupportedEncodingException {
		String l_S_Desc = "";
		if ((l_S_Source != null) && (!l_S_Source.trim().equals(""))) {
			byte[] l_b_Proc = l_S_Source.getBytes("GBK");
			l_S_Desc = new String(l_b_Proc, "ISO8859_1");
		}
		return l_S_Desc;
	}

	public static String GBToUnicode(String l_S_Source) throws UnsupportedEncodingException {
		String l_S_Desc = "";
		if ((l_S_Source != null) && (!l_S_Source.trim().equals(""))) {
			byte[] l_b_Proc = l_S_Source.getBytes("ISO8859_1");
			l_S_Desc = new String(l_b_Proc, "GBK");
		}
		return l_S_Desc;
	}

	public static String javaScriptStringEnc(String s) {
		int ln = s.length();
		for (int i = 0; i < ln; i++) {
			char c = s.charAt(i);
			if ((c == '"') || (c == '\'') || (c == '\\') || (c == '>') || (c < ' ')) {
				StringBuffer b = new StringBuffer(ln + 4);
				b.append(s.substring(0, i));
				while (true) {
					if (c == '"')
						b.append("\\\"");
					else if (c == '\'')
						b.append("\\'");
					else if (c == '\\')
						b.append("\\\\");
					else if (c == '>')
						b.append("\\>");
					else if (c < ' ') {
						if (c == '\n') {
							b.append("\\n");
						} else if (c == '\r') {
							b.append("\\r");
						} else if (c == '\f') {
							b.append("\\f");
						} else if (c == '\b') {
							b.append("\\b");
						} else if (c == '\t') {
							b.append("\\t");
						} else {
							b.append("\\x");
							int x = c / '\020';
							b.append((char) (x < 10 ? x + 48 : x - 10 + 65));
							x = c & 0xF;
							b.append((char) (x < 10 ? x + 48 : x - 10 + 65));
						}
					} else
						b.append(c);

					i++;
					if (i >= ln) {
						return b.toString();
					}
					c = s.charAt(i);
				}
			}
		}
		return s;
	}

	public static synchronized StringUtil getInstance() {
		if (instance == null) {
			instance = new StringUtil();
		}
		return instance;
	}

	public static String trimContinuousSpace(String src) {
		return src.replaceAll("\\s+", " ");
	}

	public static String replace(String src, String target, String rWith, int maxCount) {
		return StringUtils.replace(src, target, rWith, maxCount);
	}

	public static boolean equals(String str1, String str2) {
		return StringUtils.equals(str1, str2);
	}

	public static boolean isAlphanumeric(String str) {
		return StringUtils.isAlphanumeric(str);
	}

	public static boolean isNumeric(String str) {
		return StringUtils.isNumeric(str);
	}

	public static String[] stripAll(String[] strs) {
		return StringUtils.stripAll(strs);
	}

	public static String toFloatNumber(String num) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(Double.parseDouble(num));
	}

	public static String toFloatNumber(Double num, int accuracy) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(accuracy);
		nf.setMinimumFractionDigits(accuracy);
		return nf.format(num);
	}

	public static String wcsUnescape(String str) {
		str = str.replace("#lt;", "<");
		str = str.replace("#gt;", ">");
		str = str.replace("#quot;", "\"");
		str = str.replace("#amp;amp;", "&");
		str = str.replace("#amp;", "&");
		str = str.replace("#039;", "'");
		return str;
	}

	public static int getByteLength(String str) {
		if (str == null) {
			return 0;
		}
		return str.getBytes().length;
	}

	public static String getByteStr(String str, int limitLen) {
		StringBuffer sb = new StringBuffer();
		char[] chars = getNotNullStr(str).toCharArray();
		int len = 0;
		for (char c : chars) {
			len += getByteLength(String.valueOf(c));
			if (len <= limitLen) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String subStr(String content, Integer length, String padding) throws UnsupportedEncodingException {
		String str = "";
		int paddSize = StringUtils.isBlank(padding) ? 0 : padding.length();

		if ((StringUtils.isBlank(content)) || (content.length() <= length.intValue())) {
			return content;
		}
		str = content.substring(0, length.intValue() - paddSize);
		if (StringUtils.isNotBlank(padding)) {
			str = str + padding;
		}
		return str;
	}

	public static String getColumns(String str) {
		StringBuffer sb = new StringBuffer();

		if (str != null) {
			String[] strs = str.split(",");
			for (int i = 0; i < strs.length; i++) {
				sb.append("\"").append(strs[i]).append("\"");
				if (i != strs.length - 1) {
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}

	public static String getColumnsE(String str) {
		StringBuffer sb = new StringBuffer();

		if (str != null) {
			String[] strs = str.split(",");
			for (int i = 0; i < strs.length; i++) {
				if (strs[i] != null) {
					String[] strse = strs[i].split("=");
					sb.append("\"").append(strse[0]).append("\"").append("=").append(strse[1]);
				}

				if (i != strs.length - 1) {
					sb.append(",");
				}
			}
		}

		return sb.toString();
	}

	public static String getRootPath() throws Exception {
		File directory = new File("");

		return directory.getAbsolutePath() + "\\";
	}

	public static String column2java(String source, String exp) throws Exception {
		String[] strs = (String[]) null;
		int length = 0;
		if (source.contains(exp)) {
			strs = source.split(exp);
			length = strs.length;
		}

		String java = source.toLowerCase();

		switch (length) {
		case 2:
			java = strs[0].toLowerCase() + getStrByUpperFirstChar(strs[1].toLowerCase());
			break;
		case 3:
			java = strs[0].toLowerCase() + getStrByUpperFirstChar(strs[1].toLowerCase())
					+ getStrByUpperFirstChar(strs[2].toLowerCase());
			break;
		case 4:
			java = strs[0].toLowerCase() + getStrByUpperFirstChar(strs[1].toLowerCase())
					+ getStrByUpperFirstChar(strs[2].toLowerCase()) + getStrByUpperFirstChar(strs[3].toLowerCase());
			break;
		}

		return java;
	}
}