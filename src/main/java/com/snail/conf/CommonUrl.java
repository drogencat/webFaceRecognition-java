package com.snail.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**  
 * All rights Reserved, Designed By Hantu
 * @Title:  CommonUrl.java   
 * @Package com.snail.conf   
 * @Description:*xml配置url*  
 * @author: DrogenCat   
 * @date:   Nov 15, 2018 8:54:16 AM   
 * @version V1.0 
 * @Copyright: 2018 Hantu All rights reserved. 
 * 注意：本内容仅限于上海瀚途公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Component
@PropertySource(value = "classpath:application.properties")
public class CommonUrl {
	@Value("${fileUpload.url}")
	private String fileUpLoadURL;

	public String getFileUpLoadURL() {
		return fileUpLoadURL;
	}

	public void setFileUpLoadURL(String fileUpLoadURL) {
		this.fileUpLoadURL = fileUpLoadURL;
	}
	
	
	
	
}
