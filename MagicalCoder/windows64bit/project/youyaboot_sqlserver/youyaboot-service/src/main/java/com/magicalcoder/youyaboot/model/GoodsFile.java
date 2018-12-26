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
public class GoodsFile implements Serializable{

    private Long fileId;//主键
    private String fileSrc;//文件地址
    private Long goodsId;//所属商品


    public Long getFileId(){
        return fileId;
    }
    public void setFileId(Long fileId){
        this.fileId = fileId;
    }

    public String getFileSrc(){
        return fileSrc;
    }
    public void setFileSrc(String fileSrc){
        this.fileSrc = fileSrc;
    }

    public Long getGoodsId(){
        return goodsId;
    }
    public void setGoodsId(Long goodsId){
        this.goodsId = goodsId;
    }
}
