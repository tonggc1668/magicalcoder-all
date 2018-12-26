package com.magicalcoder.youyaboot.core.common.dto;

/**
 * qq:799374340
 *
 * @author hdy 2013-7-15下午5:18:58
 */
public class AjaxData {

	private String code = "0";// 默认值
	private String message;
	private Object result;
	private String jsonp;
	public AjaxData(String code, String message, Object result) {
		this.code = code;
		this.message = message;
		setResult(result);
	}

	public AjaxData(String message, Object result) {
		this.message = message;
		setResult(result);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		if (result != null)
			this.result = result;
	}

	public String getJsonp() {
		return jsonp;
	}

	public void setJsonp(String jsonp) {
		this.jsonp = jsonp;
	}


}
