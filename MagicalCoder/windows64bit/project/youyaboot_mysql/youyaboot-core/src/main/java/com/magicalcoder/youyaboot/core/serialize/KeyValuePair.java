package com.magicalcoder.youyaboot.core.serialize;

import org.apache.commons.lang.StringUtils;

public class KeyValuePair implements Comparable<KeyValuePair>{
    private int key;
    private String value;
    private String descr;

    public KeyValuePair(int key, String value, String descr) {
        this.key = key;
        this.value = value;
        this.descr = descr;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Override
	public int compareTo(KeyValuePair other){
        if (other.getKey() == this.getKey() && StringUtils.equals(this.getValue(), other.getValue())) {
            return 0;
        } else if(other.getKey()==this.getKey()){
            return 1;
        }
        return -1;
    }
}
