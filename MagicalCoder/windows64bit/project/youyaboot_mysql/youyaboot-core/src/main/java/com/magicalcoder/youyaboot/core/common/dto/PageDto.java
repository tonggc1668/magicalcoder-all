package com.magicalcoder.youyaboot.core.common.dto;
/**
 * qq:799374340
 * @author hdy
 * 2013-9-6下午1:07:58
 */
public class PageDto {

	private int pageSize;//每页大小
	private int pageNumber;//第几页
	private long totalSize;//总条数
	private String orderBy;//排序

	public PageDto() {
	}

	public PageDto(int pageSize, int pageNumber, long totalSize, String orderBy) {
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.totalSize = totalSize;
		this.orderBy = orderBy;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}
