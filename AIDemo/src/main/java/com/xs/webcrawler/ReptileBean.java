package com.xs.webcrawler;
/**
 * 
 * @author 小帅丶
 *
 */
public class ReptileBean {
	private int status;
	private String msg;
	private Data data;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public static class Data{
		private String url;//分析后生成的临时图片地址
		private String sign;//签名值
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
		
	}
}
