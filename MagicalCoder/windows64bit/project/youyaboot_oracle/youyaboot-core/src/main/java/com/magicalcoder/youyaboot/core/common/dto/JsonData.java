package com.magicalcoder.youyaboot.core.common.dto;

/**
 * qq:799374340
 *
 * @author hdy 2013-7-15下午5:18:58
 */
public class JsonData {
	private Integer code = 0;// 默认值
	private String errorCode;//异常码
	private String message;
	private Object info;
	private String jsonp;
	private String encode;
	private boolean writeNull;//是否输出null
	public static class Builder{
		private Integer code = 0;// 默认值
		private String errorCode;
		private String message;
		private Object info;
		private String jsonp;
		private String encode;
		private boolean writeNull;
		public Builder(Object info){
			this.info = info;
		}

		public Builder code(Integer code){
			this.code = code;
			return this;
		}
		public Builder errorCode(String errorCode){
			this.errorCode = errorCode;
			return this;
		}
		public Builder message(String message){
			this.message = message;
			return this;
		}
		public Builder jsoup(String jsonp){
			this.jsonp = jsonp;
			return this;
		}

		public Builder encode(String encode){
			this.encode = encode;
			return this;
		}
		public Builder writeNull(boolean writeNull){
			this.writeNull = writeNull;
			return this;
		}
		public JsonData build(){
			return new JsonData(this);
		}
	}

	public JsonData(Builder builder) {
		this.code = builder.code;
		this.jsonp = builder.jsonp;
		this.errorCode = builder.errorCode;
		this.message = builder.message;
		this.info = builder.info;
		this.encode = builder.encode;
		this.writeNull = builder.writeNull;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public String getJsonp() {
		if(jsonp!=null){
			if(jsonp.contains("<") || jsonp.contains(">") || jsonp.contains("&")){
				return jsonp.replace("<","&lt;").replace(">","&gt;").replace("&","&amp;");
			}
		}
		return jsonp;
	}

	public void setJsonp(String jsonp) {
		this.jsonp = jsonp;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public boolean isWriteNull() {
		return writeNull;
	}

	public void setWriteNull(boolean writeNull) {
		this.writeNull = writeNull;
	}
}
