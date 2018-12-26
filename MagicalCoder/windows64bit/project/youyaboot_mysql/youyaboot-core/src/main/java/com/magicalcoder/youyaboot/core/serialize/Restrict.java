package com.magicalcoder.youyaboot.core.serialize;

/**
 * author: www.magicalcoder.com
 * date:2018/5/14
 * function:
 */
public class Restrict {
    private String servletPathReg;//url正则
    private String strategy;//策略 no ip user ip_user
    private int ipMaxReqTimes;//ip最大请求次数
    private int ipExpireSeconds;//ip失效时间
    private int userMaxReqTimes;//user最大请求次数
    private int userExpireSeconds;//user失效时间
    public String getServletPathReg() {
        return servletPathReg;
    }

    public void setServletPathReg(String servletPathReg) {
        this.servletPathReg = servletPathReg;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public int getIpMaxReqTimes() {
        return ipMaxReqTimes;
    }

    public void setIpMaxReqTimes(int ipMaxReqTimes) {
        this.ipMaxReqTimes = ipMaxReqTimes;
    }

    public int getIpExpireSeconds() {
        return ipExpireSeconds;
    }

    public void setIpExpireSeconds(int ipExpireSeconds) {
        this.ipExpireSeconds = ipExpireSeconds;
    }

    public int getUserMaxReqTimes() {
        return userMaxReqTimes;
    }

    public void setUserMaxReqTimes(int userMaxReqTimes) {
        this.userMaxReqTimes = userMaxReqTimes;
    }

    public int getUserExpireSeconds() {
        return userExpireSeconds;
    }

    public void setUserExpireSeconds(int userExpireSeconds) {
        this.userExpireSeconds = userExpireSeconds;
    }
}
