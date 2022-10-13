package com.xs.nlp.generaltrans;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.xs.tencent.sign.MD5;
/**
 * 使用JSOUP调用百度翻译接口
 * @author 小帅丶
 *
 */
public class JSOUPAPISample {
	public static void main(String[] args) throws Exception {
		String result = content("hello");
		System.out.println(result);
	}
	/**
	 * 
	 * @param english 英文内容
	 * @return
	 * @throws Exception
	 */
	public static String content(String english) throws Exception{
		String salt = String.valueOf(System.currentTimeMillis());
		String APPID = "APPID";
		String MY ="密钥";
		
		Connection connection = Jsoup.connect("http://api.fanyi.baidu.com/api/trans/vip/translate").ignoreContentType(true);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("q", english);
		map.put("from", "en");
		map.put("to", "zh");
		map.put("appid", APPID);
		map.put("salt", salt);
		String src = APPID + english + salt + MY; // 加密前的原文
		System.out.println(src);
		map.put("sign", MD5.getMD5(src).toLowerCase());
		//参数填充
		connection.data(map);
		//发送GET请求
		Document doc = connection.get();
		
		System.out.println(doc.toString());
		return doc.toString();
	}
}
