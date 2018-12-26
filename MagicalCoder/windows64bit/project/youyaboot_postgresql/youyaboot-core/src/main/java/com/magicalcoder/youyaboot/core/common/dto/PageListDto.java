package com.magicalcoder.youyaboot.core.common.dto;

import java.util.List;

/**
 * qq:799374340
 * @author hdy
 * 2013-9-6下午2:26:24
 */
public class PageListDto {

	private Long totalSize;//总记录数
	private List<?> records;//一页数据
	public PageListDto (Long totalSize,List<?> records){
		this.totalSize = totalSize;
		this.records = records;
	}
	public Long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}
	public List<?> getRecords() {
		return records;
	}
	public void setRecords(List<?> records) {
		this.records = records;
	}
	
}
