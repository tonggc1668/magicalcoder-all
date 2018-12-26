package com.magicalcoder.youyaboot.core.serialize;

public class JsonBuilder {
	private Integer code = 0;// 默认值
	private String desc;//提示信息
	private Object data;//返回结果
	private String callback;//
	private String encode;//编码
	private Boolean writeNull;//json是否输出null
	private Boolean flag;
	public static class Builder{
		private Integer code = 0;// 默认值
		private String desc;
		private Object data;
		private String callback;
		private String encode;
		private Boolean writeNull = true;
		private Boolean flag;
		public Builder(Object data){
			this.data = data;
		}

		public Builder code(Integer code){
			this.code = code;
			return this;
		}

		public Builder desc(String desc){
			this.desc = desc;
			return this;
		}
		public Builder callback(String callback){
			this.callback = callback;
			return this;
		}

		public Builder encode(String encode){
			this.encode = encode;
			return this;
		}
		public Builder writeNull(Boolean writeNull){
			this.writeNull = writeNull;
			return this;
		}
		public Builder flag(Boolean flag){
			this.flag = flag;
			return this;
		}
		public JsonBuilder build(){
			return new JsonBuilder(this);
		}
	}

	public JsonBuilder(Builder builder) {
		this.code = builder.code;
		this.callback = builder.callback;
		this.desc = builder.desc;
		this.data = builder.data;
		this.encode = builder.encode;
		this.writeNull = builder.writeNull;
		this.flag = builder.flag;
	}

	public String getCallback() {
		if(callback!=null){
			if(callback.contains("<") || callback.contains(">") || callback.contains("&")){
				return callback.replace("<","&lt;").replace(">","&gt;").replace("&","&amp;");
			}
		}
		return callback;
	}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public Boolean getWriteNull() {
        return writeNull;
    }

    public void setWriteNull(Boolean writeNull) {
        this.writeNull = writeNull;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
