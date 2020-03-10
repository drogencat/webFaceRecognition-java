package com.snail.conf;


import java.util.*;

import com.snail.common.GsonUtil;
import com.snail.util.Base64Util;
import com.snail.util.FileUtil;
import com.snail.util.HttpUtil;

/**
* 人脸搜索
*/
public class FaceSearch {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String search(String img) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
        	//base64转换
        	// 本地文件路径
            String filePath = img;
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imgStr);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "cmt");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");
            map.put("max_user_num", 1);
            String param = GsonUtil.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   
}