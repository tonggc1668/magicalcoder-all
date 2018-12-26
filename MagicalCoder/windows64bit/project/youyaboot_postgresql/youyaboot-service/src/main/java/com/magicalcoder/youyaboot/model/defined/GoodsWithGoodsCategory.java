package com.magicalcoder.youyaboot.model.defined;
import java.util.Date;
import java.math.BigDecimal;
import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Time;
import com.magicalcoder.youyaboot.model.Goods;
/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
public class GoodsWithGoodsCategory extends Goods  implements Serializable{
        private Long goodsCategoryId;
    private String name;
    private String keyword;
    public Long getGoodsCategoryId(){
       return goodsCategoryId;
    }
    public void setGoodsCategoryId(Long goodsCategoryId){
        this.goodsCategoryId = goodsCategoryId;
    }
    public String getName(){
       return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getKeyword(){
       return keyword;
    }
    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

}