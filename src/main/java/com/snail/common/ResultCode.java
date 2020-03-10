package com.snail.common;


/**
 * API返回相关错误码
 * @author zhaofh 2016年10月20日
 * 
 */
public class ResultCode {
	
	public static final int RESULT_CODE_SUCCESS = 1;
	public static final int RESULT_CODE_FAILED = 0;
	public static final String RESULT_REASON_SUCCESS = "success";
	public static final String RESULT_REASON_FAILED = "failed";
	public static final String RESULT_REASON_ERROR ="{\"result\":\"falied\",\"resultCode\":0}";	
	public static final String RESULT_MESSKEY_ERROR ="{\"result\":\"密钥错误\",\"resultCode\":0}";
	public static final String RESULT_UNKNOW = "未知错误，请重试";

	/**
	 * 账户相关错误码
	 * @author zhaofh 2016年11月17日
	 */
	public static class ACCOUNT{
		public static final String ACCOUNT_PWD_ISNULL = "请检验输入框是否已经完善！";
		public static final String USERNAME_REPEAT="用户名已被占用，请更换用户名";
		public static final String PASSWORD_ERROR="密码长度为6～15位，请重新输入";
		public static final String REMARK_ERROR="备注长度超出50字限制，请重新输入";
		/**用户不存在*/
		public static final String USER_NOT_EXIST = "用户信息有误";
		public static final String USER_NOT_EXIST_REGISTER = "用户尚未注册";
		/**密码错误*/
		public static final String PWD_IS_ERROR = "密码错误";
		
		public static final String PASSWORD_IS_ERROR = "新密码和旧密码一致";
		
		/**session里面已有用户*/
		public static final String UPTO_BAIDU_FAIL = "人脸注册失败，请重试";
	}
	/**
	 * 渠道人员
	 *
	 */
	
	/**
	 * 工单
	 *
	 */
	public static class FACE{
		public static final String NO_SUIT_FACE = "人脸识别失败，请重试！";

	}
	/**
	 * 渠道人员
	 * @author xwl
	 *
	 */
	public static class CHANNELUSER{
		public static final String PARAM_VOIL_MOBILE = "手机号码格式有误，请检查";
		public static final String PARAM_MOBILE = "保存失败，手机号格式有误！";
		public static final String PARAM_NAME="姓名有误，请核对";
		public static final String CHANNELUSER_RESULT_NULL="未查询到任何数据！";

		
	}
	/**
	 *
	 * @author xwl
	 *
	 */
	public static class GOODS{
		public static final String GOODS_IS_NOT_EXIST = "未查询到任何数据";
		
	}
	/**
	 *  短讯发送
	 * @author xwl
	 *
	 */
	public static class COMMON{
		public static final String SYSTEM_ERROR = "系统异常，请重试！";
		
	}

}
