package com.magicalcoder.youyaboot.model;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.math.*;
import java.io.Serializable;
import lombok.Data;
/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
@Data
public class Goods implements Serializable{

    private Long id;//主键
    private String goodsName;//商品名
    private String publishStatus;//是否发布
    private Byte goodsStatus;//商品状态
    private BigDecimal price;//价格
    private Integer storeCount;//库存
    private String shortBrief;//简介
    private String goodsDescription;//商品描述
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp createTime;//创建时间
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp updateTime;//更新时间
    private String imgSrc;//图片
    private Long goodsCategoryId;//所属类目


    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getGoodsName(){
        return goodsName;
    }
    public void setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }

    public String getPublishStatus(){
        return publishStatus;
    }
    public void setPublishStatus(String publishStatus){
        this.publishStatus = publishStatus;
    }

    public Byte getGoodsStatus(){
        return goodsStatus;
    }
    public void setGoodsStatus(Byte goodsStatus){
        this.goodsStatus = goodsStatus;
    }

    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public Integer getStoreCount(){
        return storeCount;
    }
    public void setStoreCount(Integer storeCount){
        this.storeCount = storeCount;
    }

    public String getShortBrief(){
        return shortBrief;
    }
    public void setShortBrief(String shortBrief){
        this.shortBrief = shortBrief;
    }

    public String getGoodsDescription(){
        return goodsDescription;
    }
    public void setGoodsDescription(String goodsDescription){
        this.goodsDescription = goodsDescription;
    }

    public Timestamp getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime){
        this.updateTime = updateTime;
    }

    public String getImgSrc(){
        return imgSrc;
    }
    public void setImgSrc(String imgSrc){
        this.imgSrc = imgSrc;
    }

    public Long getGoodsCategoryId(){
        return goodsCategoryId;
    }
    public void setGoodsCategoryId(Long goodsCategoryId){
        this.goodsCategoryId = goodsCategoryId;
    }
}
