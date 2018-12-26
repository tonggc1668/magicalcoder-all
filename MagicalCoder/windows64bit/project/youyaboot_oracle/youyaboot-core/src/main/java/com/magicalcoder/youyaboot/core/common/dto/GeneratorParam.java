package com.magicalcoder.youyaboot.core.common.dto;

import java.io.Serializable;

/**
 * qq:799374340
 * @author hdy
 * 2013-7-16上午11:43:37 通用参数
 */
public class GeneratorParam implements Serializable{

	private static final long serialVersionUID = 1L;

	private String format;//返回值格式化成什么参数 json xml 

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
}
