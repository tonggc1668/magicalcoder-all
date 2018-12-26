package com.magicalcoder.youyaboot.model.defined;
import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Time;
import com.magicalcoder.youyaboot.model.GoodsImg;
/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
public class GoodsImgWithGoods extends GoodsImg  implements Serializable{
        private Long id;
    private String goodsName;
    private String publishStatus;
    private Byte goodsStatus;
    private BigDecimal price;
    private Integer storeCount;
    private String shortBrief;
    private String goodsDescription;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String goodsImgSrc;
    private Long goodsCategoryId;
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
    public String getGoodsImgSrc(){
       return goodsImgSrc;
    }
    public void setGoodsImgSrc(String goodsImgSrc){
        this.goodsImgSrc = goodsImgSrc;
    }
    public Long getGoodsCategoryId(){
       return goodsCategoryId;
    }
    public void setGoodsCategoryId(Long goodsCategoryId){
        this.goodsCategoryId = goodsCategoryId;
    }

}
