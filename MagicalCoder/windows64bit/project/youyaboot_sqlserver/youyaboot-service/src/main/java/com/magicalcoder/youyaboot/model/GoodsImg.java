package com.magicalcoder.youyaboot.model;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.*;
import java.io.Serializable;
import lombok.Data;
/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
@Data
public class GoodsImg implements Serializable{

    private Long imgId;//主键
    private String imgSrc;//图片地址
    private Long goodsId;//所属商品


    public Long getImgId(){
        return imgId;
    }
    public void setImgId(Long imgId){
        this.imgId = imgId;
    }

    public String getImgSrc(){
        return imgSrc;
    }
    public void setImgSrc(String imgSrc){
        this.imgSrc = imgSrc;
    }

    public Long getGoodsId(){
        return goodsId;
    }
    public void setGoodsId(Long goodsId){
        this.goodsId = goodsId;
    }
}
