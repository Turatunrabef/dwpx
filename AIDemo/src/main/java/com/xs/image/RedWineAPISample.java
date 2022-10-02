package com.xs.image;

import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.xs.common.BDAccessToken;
import com.xs.pojo.image.RedWineBean;
import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.util.baidu.HttpUtil;

/**
 * 红酒识别
 * @author 小帅丶
 *
 */
public class RedWineAPISample {
	//红酒识别接口地址
	public static final String REDWINE_URL = "https://aip.baidubce.com/rest/2.0/image-classify/v1/redwine";
	
	public static void main(String[] args) throws Exception {
//		String accessToken = BDAccessToken.getAuth("", "");
		String accessToken = "自己应用获取的accesstoken";
		String imagePath = "G:/testimg/redwine02.jpg";
		String result = getRedWineResult(imagePath , accessToken);
		System.out.println(result);
	}
	/**
	 * 红酒识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return 字符串
	 * @throws Exception
	 */
	public static String getRedWineResult(String imagePath,String accessToken) throws Exception{
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
        String imgStr = Base64Util.encode(imgData);
		String param = "image=" + URLEncoder.encode(imgStr,"UTF-8");
        // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
		String result = HttpUtil.post(REDWINE_URL, accessToken, param);
        return result;
	}
	/**
	 * 红酒识别Demo
	 * @param imagePath
	 * @param accessToken
	 * @return RedWineBean
	 * @throws Exception
	 */
	public static RedWineBean getRedWineBean(String imagePath,String accessToken) throws Exception{
		String result = getRedWineResult(imagePath, accessToken);
		RedWineBean bean = JSON.parseObject(result,RedWineBean.class);
        return bean;
	}
}
