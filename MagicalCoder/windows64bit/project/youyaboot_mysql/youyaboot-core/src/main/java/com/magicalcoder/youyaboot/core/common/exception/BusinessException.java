package com.magicalcoder.youyaboot.core.common.exception;


import com.magicalcoder.youyaboot.core.serialize.KeyValuePair;
import com.magicalcoder.youyaboot.core.utils.StringUtil;

/**
 * 业务异常 直接抛
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	/**
	 * 错误代码
	 */
	private int errorCode = -1;
	private String desc;

	/**
	 * 业务异常 直接抛出 不用再一层层代码返回处理了
	 * @param keyValuePair
	 */
	public BusinessException(KeyValuePair keyValuePair){
		super("");
		if(keyValuePair!=null){
			this.errorCode = keyValuePair.getKey();
			if(StringUtil.isBlank(keyValuePair.getDescr())){
				desc = keyValuePair.getValue();
			}else {
				desc = keyValuePair.getDescr();
			}
		}
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public String getDesc() {
		return desc;
	}

	/**
	 * 异常堆栈增加错误代码和绑定变量
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("业务异常[").append(this.errorCode).append("]");
		sb.append(this.desc);
//		sb.append(super.toString());
		return sb.toString();
	}

	@Override
	public String getMessage() {
		return toString();
	}
}
