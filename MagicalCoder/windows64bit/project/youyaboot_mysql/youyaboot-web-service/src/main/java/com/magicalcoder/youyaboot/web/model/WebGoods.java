package com.magicalcoder.youyaboot.web.model;

import java.io.Serializable;
/**
自由的实体 会映射到WebGoodsMapper.xml中
*/
public class WebGoods implements Serializable{

    private Long id;//主键

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
