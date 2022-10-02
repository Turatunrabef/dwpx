package com.xs.pojo.imagecensor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;


/**
 * @author 小帅丶
 *
 */
public class VideoUserDefinedBean {
	public static void main(String[] args) throws UnsupportedEncodingException {
		VideoUserDefinedBean bean = new VideoUserDefinedBean();
		bean.setAppid(11800067);
//		bean.setImgUrls(URLEncoder.encode("https://otc-ggyx.oss-cn-shenzhen.aliyuncs.com/2018-10-12/a000000001539341854348.jpg","UTF-8"));
		 List<String> scenes = new ArrayList<String>();
		 scenes.add(0, "antiporn");
		 scenes.add(0, "terror");
		 scenes.add(0, "politician");
		 scenes.add(0, "public");
		 scenes.add(0, "watermark");
		 scenes.add(0, "disgust");
		 scenes.add(0, "logo");
		 bean.setScenes(scenes);
		 ExtTag extTag = new ExtTag();
		 extTag.setVid(1);
		 bean.setExtTag(extTag);
		 bean.setImgUrls("{\"vid\":1}");
		 System.out.println(JSONObject.toJSON(bean));
	}
	private int appid;
	private String imgUrls;
	private ExtTag extTag;
	private List<String> scenes;
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public String getImgUrls() {
		return imgUrls;
	}
	public void setImgUrls(String imgUrls) {
		this.imgUrls = imgUrls;
	}
	public List<String> getScenes() {
		return scenes;
	}
	public void setScenes(List<String> scenes) {
		this.scenes = scenes;
	}
	
	public ExtTag getExtTag() {
		return extTag;
	}
	public void setExtTag(ExtTag extTag) {
		this.extTag = extTag;
	}

	public static class ExtTag{
		private int vid;
		public int getVid() {
			return vid;
		}

		public void setVid(int vid) {
			this.vid = vid;
		}

	}
}
