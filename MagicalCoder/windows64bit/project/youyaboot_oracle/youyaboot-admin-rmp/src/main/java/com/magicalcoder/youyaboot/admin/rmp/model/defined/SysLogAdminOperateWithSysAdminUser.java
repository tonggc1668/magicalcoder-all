package com.magicalcoder.youyaboot.admin.rmp.model.defined;

import com.magicalcoder.youyaboot.admin.rmp.model.SysLogAdminOperate;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
public class SysLogAdminOperateWithSysAdminUser extends SysLogAdminOperate  implements Serializable{
        private Long sysAdminUserId;
    private String username;
    private String password;
    private String realName;
    private String email;
    private String telephone;
    private String mobilePhone;
    private String address;
    private Timestamp sysAdminUserCreateTime;
    private Timestamp updateTime;
    private Long roleId;
    private String accountNonExpired;
    private String accountNonLocked;
    private String credentialsNonExpired;
    private String enabled;
    public Long getSysAdminUserId(){
       return sysAdminUserId;
    }
    public void setSysAdminUserId(Long sysAdminUserId){
        this.sysAdminUserId = sysAdminUserId;
    }
    public String getUsername(){
       return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
       return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getRealName(){
       return realName;
    }
    public void setRealName(String realName){
        this.realName = realName;
    }
    public String getEmail(){
       return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getTelephone(){
       return telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getMobilePhone(){
       return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone){
        this.mobilePhone = mobilePhone;
    }
    public String getAddress(){
       return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public Timestamp getSysAdminUserCreateTime(){
       return sysAdminUserCreateTime;
    }
    public void setSysAdminUserCreateTime(Timestamp sysAdminUserCreateTime){
        this.sysAdminUserCreateTime = sysAdminUserCreateTime;
    }
    public Timestamp getUpdateTime(){
       return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime){
        this.updateTime = updateTime;
    }
    public Long getRoleId(){
       return roleId;
    }
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }
    public String getAccountNonExpired(){
       return accountNonExpired;
    }
    public void setAccountNonExpired(String accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }
    public String getAccountNonLocked(){
       return accountNonLocked;
    }
    public void setAccountNonLocked(String accountNonLocked){
        this.accountNonLocked = accountNonLocked;
    }
    public String getCredentialsNonExpired(){
       return credentialsNonExpired;
    }
    public void setCredentialsNonExpired(String credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public String getEnabled(){
       return enabled;
    }
    public void setEnabled(String enabled){
        this.enabled = enabled;
    }

}
