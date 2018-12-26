package com.magicalcoder.youyaboot.core.serialize;

import com.magicalcoder.youyaboot.core.utils.StringUtil;

public class ResponseMsg {
    private static final int SUCCESS = 0;
	private boolean flag;
    private int code;
    private String desc;
    private Integer count;
    private Object data;

    public ResponseMsg() {
    	this.flag = true;
        this.code = SUCCESS;
        this.desc = "成功";
    }
    public ResponseMsg (Integer count,Object data){
        this.count = count;
        this.flag = true;
        this.code = SUCCESS;
        this.desc = "成功";
        this.data = data;
    }
    public ResponseMsg (Object data){
        this.flag = true;
        this.code = SUCCESS;
        this.desc = "成功";
        this.data = data;
    }

    public ResponseMsg(int code,String desc) {
        this.code = code;
        this.desc = desc;
        if(code == SUCCESS){
            this.flag = true;
        }else {
            this.flag = false;
        }
    }

    public ResponseMsg(KeyValuePair keyValuePair) {
    	if (keyValuePair.getKey() == SUCCESS) {
    		this.flag = true;
		} else {
			this.flag = false;
		}
        this.code = keyValuePair.getKey();
        if (StringUtil.isBlank(keyValuePair.getDescr())) {
        	this.desc = keyValuePair.getValue();
		} else {
			this.desc = keyValuePair.getDescr();
		}
    }
    

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
