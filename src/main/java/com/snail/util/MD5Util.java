package com.snail.util;
 
import java.util.Random;
import org.apache.commons.codec.binary.Hex;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
 
/**
 * MD5工具类，加盐
 * @author daniel
 * @email 
 * @time 2016-6-11 下午7:57:36
 */
public class MD5Util {
 
	/**
	 * 普通MD5
	 * @author daniel
	 * @time 2016-6-11 下午8:00:28
	 * @param inStr
	 * @return
	 */
	public static String MD5(String input) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return "check jdk";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = input.toCharArray();
		byte[] byteArray = new byte[charArray.length];
 
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
 
	 
	 
	 
	/**
	 * 加盐MD5
	 * @author daniel
	 * @time 2016-6-11 下午8:45:04
	 * @param password
	 * @return
	 */
		public static String generate(String password) {
			Random r = new Random();
	 		StringBuilder sb = new StringBuilder(16);
	 		sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
	 		int len = sb.length();
	 		if (len < 16) {
	 			for (int i = 0; i < 16 - len; i++) {
	 				sb.append("0");
	 			}
	 		}
	 		String salt = sb.toString();
	 		password = md5Hex(password + salt);
	 		char[] cs = new char[48];
	 		for (int i = 0; i < 48; i += 3) {
	 			cs[i] = password.charAt(i / 3 * 2);
	 			char c = salt.charAt(i / 3);
	 			cs[i + 1] = c;
	 			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
	 		}
			return new String(cs);
		}
		/**
		 * 校验加盐后是否和原文一致
		 * @author daniel
		 * @time 2016-6-11 下午8:45:39
		 * @param password
		 * @param md5
		 * @return
		 */
		public static boolean verify(String password, String md5) {
	 		char[] cs1 = new char[32];
			char[] cs2 = new char[16];
			for (int i = 0; i < 48; i += 3) {
				cs1[i / 3 * 2] = md5.charAt(i);
				cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
				cs2[i / 3] = md5.charAt(i + 1);
			}
			String salt = new String(cs2);
			return md5Hex(password + salt).equals(new String(cs1));
		}
		/**
		 * 获取十六进制字符串形式的MD5摘要
		 */
		private static String md5Hex(String src) {
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				byte[] bs = md5.digest(src.getBytes());
				return new String(new Hex().encode(bs));
			} catch (Exception e) {
				return null;
			}
		}
	  public static void main(String[] args) {
		String md5Hex = md5Hex("longmao");
		System.out.println(md5Hex);
		String generate = generate("longmao");
		System.out.println(generate);
		boolean verify = verify("longmao","871d4aa89568c8886e94d33d008357c0a107782448d26c07");
		System.out.println(verify);
		
	}
	 
	 
}

