package com.snail.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.StringUtils;
import com.snail.common.GsonUtil;
import com.snail.common.ResultCode;
import com.snail.common.ResultDto;
import com.snail.conf.AuthService;
import com.snail.conf.CommonUrl;
import com.snail.conf.FaceAdd;
import com.snail.entity.UserInfo;
import com.snail.service.UserInfoService;
import com.snail.util.AppLoger;
import com.snail.util.DaoSupport;
import com.snail.util.MD5Util;
/**  
 * All rights Reserved, Designed By Hantu
 * @Title:  UserInfoImpl.java   
 * @Package com.snail.serviceImpl   
 * @Description:*用户信息实现*  
 * @author: DrogenCat   
 * @date:   Nov 7, 2018 5:11:42 PM   
 * @version V1.0 
 * @Copyright: 2018 Hantu All rights reserved. 
 * 注意：本内容仅限于上海瀚途公司内部传阅，禁止外泄以及用于其他的商业目
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private DaoSupport dao;
	@Autowired
	private CommonUrl commonUrl;
	public UserInfo getUsers() throws Exception {
		// TODO Auto-generated method stub
		UserInfo list = (UserInfo)dao.findForObject("userDao.getUsersList", null);
		return list;
	}
	public String login(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		ResultDto rd = new ResultDto();
		UserInfo realUser = (UserInfo)dao.findForObject("userDao.getUserAccount", userInfo.getUserName());
		//参数为空
		if(StringUtils.isNullOrEmpty(userInfo.getUserName())||StringUtils.isNullOrEmpty(userInfo.getPassword())) {
			rd.setResult(ResultCode.ACCOUNT.ACCOUNT_PWD_ISNULL);
			rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
			return GsonUtil.toJson(rd);
		}
		//用户未注册
		if (realUser==null) {
			rd.setResult(ResultCode.ACCOUNT.USER_NOT_EXIST_REGISTER);
			rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
			return GsonUtil.toJson(rd);
		}
		//用户存在，对比md5
			//密码错误
		if (!MD5Util.verify(userInfo.getPassword(), realUser.getPassword())) {
			rd.setResult(ResultCode.ACCOUNT.PWD_IS_ERROR);
			rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
			return GsonUtil.toJson(rd);
		}else {
			//登陆成功
			rd.setResult(ResultCode.RESULT_REASON_SUCCESS);
			rd.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
			return GsonUtil.toJson(rd);	
		}
		
		
	}
	//添加用户
	public String addUser(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		ResultDto rd = new ResultDto();
		try {
			//验证空字段
			if (StringUtils.isNullOrEmpty(userInfo.getNickName())||StringUtils.isNullOrEmpty(userInfo.getUserName())||StringUtils.isNullOrEmpty(userInfo.getPassword())) {
				rd.setResult(ResultCode.ACCOUNT.ACCOUNT_PWD_ISNULL);
				rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
				return GsonUtil.toJson(rd);
			}
			//验证密码长度
			if (userInfo.getPassword().length()<6) {
				rd.setResult(ResultCode.ACCOUNT.PASSWORD_ERROR);
				rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
				return GsonUtil.toJson(rd);
			}
			//校验数据库对比userName，禁止DoubleKey
			Integer count = (Integer)dao.findForObject("userDao.getUserByUserName", userInfo.getUserName());
			if (null!=count && count>0) {
				rd.setResult(ResultCode.ACCOUNT.USERNAME_REPEAT);
				rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
				return GsonUtil.toJson(rd);
			}
			//加密password
			userInfo.setPassword(MD5Util.generate(userInfo.getPassword()));
			//储存百度云+本地db
			Integer nextUserID = (Integer)dao.findForObject("userDao.getNextValue", null);
			//获取百度云token
			String auth = AuthService.getAuth();
			//调用百度云添加图片库（注册）
			String add = FaceAdd.add(String.valueOf(nextUserID), userInfo.getUserName(), auth, commonUrl.getFileUpLoadURL()+userInfo.getUserName()+".png");
			JSONObject jsonObject=(JSONObject)JSONObject.parse(add);
			int parseInt = Integer.parseInt(jsonObject.get("error_code").toString());
			if (parseInt!=0) {
				rd.setResult(ResultCode.ACCOUNT.UPTO_BAIDU_FAIL);
				rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
				return GsonUtil.toJson(rd);
			}else {
				long time = new Date().getTime()/1000l;
				userInfo.setCreateDate((int) time);
				//添加数据至数据库
				userInfo.setFaceIdentity(userInfo.getUserName()+".png");
				dao.save("userDao.addUser", userInfo);
				if (userInfo.getUserID()>0) {
					rd.setResult(ResultCode.RESULT_REASON_SUCCESS);
					rd.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
					return GsonUtil.toJson(rd);
				}else {
					rd.setResult(ResultCode.RESULT_REASON_FAILED);
					rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
					return GsonUtil.toJson(rd);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			rd.setResult(ResultCode.COMMON.SYSTEM_ERROR);
			rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
			AppLoger.logInfo(ResultCode.COMMON.SYSTEM_ERROR+e);
			return GsonUtil.toJson(rd);
		}
	}
	
}
