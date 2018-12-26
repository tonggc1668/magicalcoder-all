package com.magicalcoder.youyaboot.core.serialize;

import lombok.Data;

import java.util.List;

@Data
public class PageData<T> {
  private Long totalCount;//总记录数
  private List<T> pageData;//当前页需要的数据

    public PageData() {
    }

    public PageData(Long totalCount, List<T> pageData) {
        this.totalCount = totalCount;
        this.pageData = pageData;
    }
    public PageData(Integer totalCount, List<T> pageData) {
        if(totalCount==null){
            totalCount = 0;
        }
        this.totalCount = Long.valueOf(totalCount);
        this.pageData = pageData;
    }

}
