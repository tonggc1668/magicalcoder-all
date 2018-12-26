package com.magicalcoder.youyaboot.core.serialize;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

@Data
public class Page {

    private static final Integer DEFAULT_PAGE_CURRENT = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 排序规则  desc ,asc
     */
    private String sortOrder = "desc";

    /**
     * 排序字段
     */
    private String sortField = null;

    /**
     * 查询的当前页
     */
    private Integer currPage ;

    /**
     * 查询的数据条目
     */
    private Integer pageSize ;

    public String getSortOrder() {
        if (StringUtils.equalsIgnoreCase(sortOrder, "desc") || StringUtils.equalsIgnoreCase(sortOrder, "asc")) {
            return sortOrder;
        } else {
            return "desc";
        }
    }

    public Integer getCurrPage(){
        if(currPage==null){
            this.currPage =DEFAULT_PAGE_CURRENT;
        }
        return this.currPage ;
    }

    public Integer getPageSize(){
        if(pageSize==null){
            this.pageSize= DEFAULT_PAGE_SIZE;
        }
        if(pageSize>DEFAULT_PAGE_SIZE){
            this.pageSize= DEFAULT_PAGE_SIZE;
        }
        return this.pageSize ;
    }

    /**
     * 获取起始值
     * @return
     */
    public Integer getStart(){
        return getPageSize()*(getCurrPage() -1) ;
    }


}
