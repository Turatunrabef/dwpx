package com.xs.pojo.image;
/**
 * 红酒识别bean对象
 * @author 小帅丶
 *
 */
public class RedWineBean {
	//请求标识码，随机数，唯一
	private String log_id;
	//识别结果
	private Result result;
	
	public static class Result{
		//红酒标签名称
		private String redwine;

		public String getRedwine() {
			return redwine;
		}

		public void setRedwine(String redwine) {
			this.redwine = redwine;
		}
	}

	public String getLog_id() {
		return log_id;
	}

	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
