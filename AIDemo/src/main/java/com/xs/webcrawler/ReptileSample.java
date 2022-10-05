package com.xs.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;

/**
 * 爬取移动端百度网页中  测测面相功能相关数据
 * 参考書劍Python代码  地址:https://github.com/chenguanyou/BaiduSerchImgApi 实现Java语言的调用
 * 需要一定的HTTP HTML结构相关知识 
 * 本代码使用额外的jar为JSOUP https://jsoup.org/
 * @author 小帅丶
 *
 */
public class ReptileSample {
	
	public static void main(String[] args) throws Exception {
		String image = "G:/testimg/aidemo.jpg";
		ReptileBean bean = getApi(image,true);
		System.out.println(getYanZhi(bean.getData().getSign()));
	}
	/**
	 * 上传图片获取SIGN
	 * @param image 图片本地路径
	 * @return String
	 * @throws Exception
	 */
	public static String getApi(String image) throws Exception{
		String result = ReptileHttpUtil.uploadFile(Config.ROOT_URL, image);
		return result;
	}
	/**
	 * 上传图片获取SIGN
	 * @param image 图片本地路径
	 * @param isBean true false都可以 只是为了区别一个返回字符串 一个返回对象
	 * @return @see ReptileBean
	 * @throws Exception
	 */
	public static ReptileBean getApi(String image,boolean isBean) throws Exception{
		String result = getApi(image);
		System.out.println(result);
		ReptileBean bean = JSON.parseObject(result, ReptileBean.class);
		return bean;
	}
	/**
	 * 爬取颜值面部相关内容
	 * @param sign 签名值
	 * @return String
	 * @throws Exception
	 */
	public static String getYanZhi(String sign) throws Exception{
		String result = ReptileHttpUtil.getGeneralUrl(Config.YANZHI_URL.replace("SIGN", sign), "");
		String YanZhiResult = "";
		Document doc = Jsoup.parse(result);
		//获取脸部标签
		String tagFace = "";
		Elements tagFaceelements = doc.select("#graph-c-face-booth > div.graph-grid-result-body > div > section.graph-c-face-booth-panel > div.graph-c-face-booth-panel-tag-wrapper.graph-flexbox > div > a");
		if(tagFaceelements==null||tagFaceelements.size()<=0){
			tagFace =  "Not Found";
		}else{
			for (int i = 0; i < tagFaceelements.size(); i++) {
				tagFace += tagFaceelements.get(i).text()+"\t";
			}
		}
		YanZhiResult += "脸部标签:"+tagFace +"\n";
		
		//获取面部描述
		String descFace = "";
		Elements descFaceelements = doc.select("#graph-c-face-booth > div.graph-grid-result-body > div >section.graph-c-face-booth-desc");
		if(descFaceelements==null||descFaceelements.size()<=0){
			descFace =  "Not Found";
		}else{
			for (int i = 0; i < descFaceelements.size(); i++) {
				descFace += descFaceelements.get(i).text()+"\t";
			}
			if( descFace.contains("点击查看")){
				descFace = descFace.substring(0, descFace.indexOf("点击查看"));
			}
		}
		
		YanZhiResult += "面部描述:"+descFace +"\n";
		
		//获取颜值平分
		String YanZhiScore = "";
		Elements YanZhiScoreelements = doc.select("#graph-c-summary > div.graph-grid-result-title > a > div.graph-c-summary-title-wrapper > div.graph-c-summary-title.graph-w-line-clamp1");
		if(YanZhiScoreelements==null||YanZhiScoreelements.size()<=0){
			YanZhiScore =  "Not Found";
		}else{
			YanZhiScore = YanZhiScoreelements.get(0).text();
		}
		YanZhiResult += "颜值评分:"+YanZhiScore+"\n";
		
		// 获取面相评分 拿不到数据
//		String contentFace = "";
//		Elements contentFaceelements = doc.select("#graph-c-summary > article > section > div");
//		if(contentFaceelements==null||contentFaceelements.size()<=0){
//			contentFace =  "Not Found";
//		}else{
//			for (int i = 0; i < contentFaceelements.size(); i++) {
//				contentFace += contentFaceelements.get(i).text()+"\t";
//			}
//		}
//		YanZhiResult += "面相评分:"+contentFace+"\n";
		
		// 获取面相内容
		String FaceContent = "";
		Elements FaceContentelements = doc.select("#graph-c-summary > div.graph-grid-result-body.graph-grid-result-body-withmore > a > div > p");
		if(FaceContentelements==null||FaceContentelements.size()<=0){
			FaceContent =  "Not Found";
		}else{
			FaceContent = FaceContentelements.get(0).text();
		}
		YanZhiResult += "面相内容:"+FaceContent+"\n";
		
		// 获取颜值标签
		String FaceTag = "";
		Elements FaceTagelements = doc.select("#graph-c-summary > div.graph-grid-result-title > a > div.graph-c-summary-title-wrapper > div.graph-c-summary-title-mark > span");
		if(FaceTagelements==null||FaceTagelements.size()<=0){
			FaceTag =  "Not Found";
		}else{
			FaceTag = FaceTagelements.get(0).text();
		}
		YanZhiResult += "颜值标签:"+FaceTag+"\n";
		
		// 获取颜值气质
		String FaceQiZhi = "";
		Elements FaceQiZhielements = doc.select("#graph-c-summary > div.graph-grid-result-body.graph-grid-result-body-withmore > div > a > p");
		if(FaceQiZhielements==null||FaceQiZhielements.size()<=0){
			FaceQiZhi =  "Not Found";
		}else{
			FaceQiZhi = FaceQiZhielements.get(0).text();
		}
		YanZhiResult += "颜值气质:"+FaceQiZhi+"\n";
		
		// 获取颜值面部 拿不到数据
//		String FaceYanZhi = "";
//		Elements FaceYanZhielements = doc.select("#graph-c-summary > div.graph-grid-result-body.graph-grid-result-body-withmore > div > div > a");
//		if(FaceYanZhielements==null||FaceYanZhielements.size()<=0){
//			FaceYanZhi =  "Not Found";
//		}else{
//			FaceYanZhi = FaceYanZhielements.get(0).text();
//		}
//		YanZhiResult += "颜值面部:"+FaceYanZhi+"\n";
		return YanZhiResult;
	}
}
