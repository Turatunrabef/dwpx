package com.xs.youtu.ocr;

import com.xs.util.baidu.Base64Util;
import com.xs.util.baidu.FileUtil;
import com.xs.youtu.common.YouTuAPIContants;
import com.xs.youtu.common.YouTuAppContants;
import com.xs.youtu.util.HttpUtil4YouTu;
import com.xs.youtu.util.YouTuSign;

public class IDCardOCRSample {
	public static void main(String[] args) throws Exception {
		String imagePath = "G:/testimg/idcard.jpg";
		String result = getIDCardOCR(imagePath);
		System.out.println(result);
	}
	/**
	 * 返回字符串
	 * @param imagePath 图片路径
	 * @return
	 * @throws Exception
	 */
	public static String getIDCardOCR(String imagePath) throws Exception{
		//获取SIGN值
		String sign = YouTuSign.getSign(YouTuAppContants.userQQ,
				YouTuAppContants.AppID, YouTuAppContants.SecretID,
				YouTuAppContants.SecretKey);
		byte[] imgData = FileUtil.readFileByBytes(imagePath);
		String image = Base64Util.encode(imgData);
		//card_type 2自动识别正反
		String params = "{\"app_id\":\""+YouTuAppContants.AppID+"\",\"image\":\""+image+"\",\"card_type\":2}";
		String result = HttpUtil4YouTu.post(YouTuAPIContants.IDCARD_COCR, params, sign);
		return result;
	}
}
