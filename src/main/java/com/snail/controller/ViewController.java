package com.snail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
 * All rights Reserved, Designed By Hantu
 * @Title:  ViewController.java   
 * @Package com.snail.controller   
 * @Description:*视图跳转*  
 * @author: DrogenCat   
 * @date:   Nov 9, 2018 1:56:07 PM   
 * @version V1.0 
 * @Copyright: 2018 Hantu All rights reserved. 
 * 注意：本内容仅限于上海瀚途公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Controller
public class ViewController {
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

}
