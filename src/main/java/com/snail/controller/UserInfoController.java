package com.snail.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.snail.common.GsonUtil;
import com.snail.common.ResultCode;
import com.snail.common.ResultDto;
import com.snail.conf.CommonUrl;
import com.snail.conf.FaceSearch;
import com.snail.entity.UserInfo;
import com.snail.service.UserInfoService;
import com.snail.util.AppLoger;
import com.snail.util.DaoSupport;
import com.snail.util.PhotoPack;
@CrossOrigin
@Controller
@RequestMapping("user")
public class UserInfoController extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	PhotoPack photoPack;
	@Autowired
	private DaoSupport dao;

	@Autowired
	private CommonUrl CommonUrl;
	@ResponseBody
	@RequestMapping("/getUsers")
	public  String  index() throws Exception{
		UserInfo users = userInfoService.getUsers();
		return GsonUtil.toJson(users);
	}	
	//账号密码登录验证
	@ResponseBody
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(@RequestBody UserInfo userInfo) throws Exception {
		String login = userInfoService.login(userInfo);
		return login;
	}
	//注册
	@ResponseBody
	@RequestMapping(value="/registered",method = RequestMethod.POST)
	public String registered(@RequestBody UserInfo userInfo) throws Exception{
		String addUser = userInfoService.addUser(userInfo);
		return addUser;
	}
	//人脸登录识别-百度云
	@ResponseBody
	@RequestMapping("/certificationIdentity")
	public String certificationIdentity() {
		ResultDto rd = new ResultDto();
		
		String search = FaceSearch.search(CommonUrl.getFileUpLoadURL()+"COMMONIMG.png");
		JSONObject jsonObject = (JSONObject)JSONObject.parse(search);
		Integer errorCode = (Integer)jsonObject.get("error_code");//百度云识别回执代码 0 成功
		String errorMsg = (String)jsonObject.get("error_msg");//百度云识别代回执信息
		Map<Object, Object> map = new HashMap<Object, Object>();
		if (errorCode==0) {
			//识别成功，解析json
//			{
//				"error_code": 0,
//				"error_msg": "SUCCESS",
//				"log_id": 1368654426169648122,
//				"timestamp": 1542616964,
//				"cached": 0,
//				"result": {
//					"face_token": "a3801df62e938f7c9bc45c59c2bc34e5",
//					"user_list": [{
//						"group_id": "cmt",
//						"user_id": "2",
//						"user_info": "龙猫",
//						"score": 91.784690856934
//					}]
//				}
//			}
			String resultJson = jsonObject.get("result").toString();
			JSONObject result = (JSONObject)JSONObject.parse(resultJson);
			String userList = result.get("user_list").toString();
			JSONArray userInfo = (JSONArray)JSONObject.parseArray(userList);
			JSONObject userInfoIndex = (JSONObject)userInfo.get(0);
			Integer userID =  Integer.valueOf(userInfoIndex.get("user_id").toString());
			String userName = userInfoIndex.get("user_info").toString();
			String score = userInfoIndex.get("score").toString();
			map.put("errorCode", errorCode);
			map.put("errorsg", errorMsg);
			map.put("userID", userID);
			map.put("userName", userName);
			map.put("score", score);
			rd.setData(map);
			rd.setResult(ResultCode.RESULT_REASON_SUCCESS);
			rd.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
			return GsonUtil.toJson(rd);
		}else {
			map.put("errorCode", errorCode);
			map.put("errorsg", errorMsg);
			rd.setResult(ResultCode.FACE.NO_SUIT_FACE);
			rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
			return GsonUtil.toJson(rd);
		}
	}
	//人脸注册-拉取本机摄像头
	@RequestMapping(value="/faceRegistered")
	@ResponseBody
	public String faceRegistered(String userName) throws IOException {
		ResultDto rd = new ResultDto();
		try {
			photoPack.registered(userName);
			rd.setResult(ResultCode.RESULT_REASON_SUCCESS);
			rd.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
			return GsonUtil.toJson(rd);
		} catch (Exception e) {
			// TODO: handle exception
			rd.setResult(ResultCode.RESULT_REASON_FAILED);
			rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
			AppLoger.logInfo(ResultCode.COMMON.SYSTEM_ERROR+e);
			return GsonUtil.toJson(rd);
		}	
    }
	
	//人脸登录-拉取本机摄像头
		@RequestMapping(value="/faceLogin")
		@ResponseBody
		public String faceLogin(String userName) throws IOException {
			ResultDto rd = new ResultDto();
			try {
				System.out.println(userName);
				photoPack.registered(userName);
				rd.setResult(ResultCode.RESULT_REASON_SUCCESS);
				rd.setResultCode(ResultCode.RESULT_CODE_SUCCESS);
				return GsonUtil.toJson(rd);
			} catch (Exception e) {
				// TODO: handle exception
				rd.setResult(ResultCode.RESULT_REASON_FAILED);
				rd.setResultCode(ResultCode.RESULT_CODE_FAILED);
				AppLoger.logInfo(ResultCode.COMMON.SYSTEM_ERROR+e);
				return GsonUtil.toJson(rd);
			}	
	    }
		@RequestMapping("test")
		@ResponseBody
		public Map<Object, Object> test() throws Exception{
			Map<Object, Object> map = (Map<Object, Object>)dao.findForObject("userDao.getUser", null);
			return map;
			
		}
	}