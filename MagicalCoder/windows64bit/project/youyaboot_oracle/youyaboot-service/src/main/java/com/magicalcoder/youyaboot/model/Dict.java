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
public class Dict implements Serializable{

    private Long id;//主键
    private String dictCategory;//字典大类
    private String code;//编码
    private String name;//名称
    private String dictDescription;//描述
    private Long parentId;//所属父类
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp createTime;//创建时间
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp updateTime;//更新时间
    private Byte orderNo;//序号


    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getDictCategory(){
        return dictCategory;
    }
    public void setDictCategory(String dictCategory){
        this.dictCategory = dictCategory;
    }

    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDictDescription(){
        return dictDescription;
    }
    public void setDictDescription(String dictDescription){
        this.dictDescription = dictDescription;
    }

    public Long getParentId(){
        return parentId;
    }
    public void setParentId(Long parentId){
        this.parentId = parentId;
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

    public Byte getOrderNo(){
        return orderNo;
    }
    public void setOrderNo(Byte orderNo){
        this.orderNo = orderNo;
    }
}
