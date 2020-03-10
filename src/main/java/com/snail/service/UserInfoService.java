package com.snail.service;
import com.snail.entity.UserInfo;

/**  
 * All rights Reserved, Designed By Hantu
 * @Title:  UserInfoService.java   
 * @Package com.snail.service   
 * @Description:*描述*  
 * @author: DrogenCat   
 * @date:   Nov 7, 2018 5:10:05 PM   
 * @version V1.0 
 * @Copyright: 2018 Hantu All rights reserved. 
 * 注意：本内容仅限于上海瀚途公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface UserInfoService {
	//查询登录用户
	public UserInfo getUsers() throws Exception;
	//登录校验
	public String login(UserInfo userInfo) throws Exception;
	//添加用户
	public String addUser(UserInfo userInfo) throws Exception;
}
