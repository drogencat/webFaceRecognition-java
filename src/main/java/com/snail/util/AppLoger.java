package com.snail.util;

import org.slf4j.LoggerFactory;
public class AppLoger {
	   public final static org.slf4j.Logger logger = LoggerFactory.getLogger(AppLoger.class);
	   public static void logInfo(String msg) {
		   logger.info(msg);
		   
	   }
	   public static void logError(String msg) {
		   logger.error(msg);
	   }
}
