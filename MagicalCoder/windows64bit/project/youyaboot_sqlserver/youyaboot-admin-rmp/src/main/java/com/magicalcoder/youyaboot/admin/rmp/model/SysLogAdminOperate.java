package com.magicalcoder.youyaboot.admin.rmp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

public class SysLogAdminOperate implements Serializable{

    private Long id;//主键
    private Long adminUserId;//管理员
    private String userName;//管理员名称
    private String tableName;//表名
    private String operateType;//操作类型
    private String url;//链接
    private String primaryIdValue;//主键值
    private String formBody;//提交内容
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;//创建时间

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Long getAdminUserId(){
        return adminUserId;
    }
    public void setAdminUserId(Long adminUserId){
        this.adminUserId = adminUserId;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getTableName(){
        return tableName;
    }
    public void setTableName(String tableName){
        this.tableName = tableName;
    }
    public String getOperateType(){
        return operateType;
    }
    public void setOperateType(String operateType){
        this.operateType = operateType;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getPrimaryIdValue(){
        return primaryIdValue;
    }
    public void setPrimaryIdValue(String primaryIdValue){
        this.primaryIdValue = primaryIdValue;
    }
    public String getFormBody(){
        return formBody;
    }
    public void setFormBody(String formBody){
        this.formBody = formBody;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

}
