package com.snail.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	private static Properties prop = new Properties();
	private static String logMailError;
	private static String logMailInfo;
	private static String logMailFlag;

	static {
		try {
			InputStream in = ConfigHelper.getResourceAsStream("/conf.properties");
			prop.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logMailError = prop.getProperty("log.mail.error");
		logMailInfo = prop.getProperty("log.mail.info");
		logMailFlag = prop.getProperty("log.mail.flag");
	}

	public static String getLogMailError() {
		return logMailError;
	}

	public static String getLogMailInfo() {
		return logMailInfo;
	}

	public static String getLogMailFlag() {
		return logMailFlag;
	}

}
