package com.magicalcoder.youyaboot.admin;

/**
 * author:hedy
 * date:2018/9/13
 * function:
 */
public class Test {
    public static void main(String[] args) {
      String s = "/admin/goods_rest/save?createtime=2018-09-26 15:13:46&file=&goodscategoryid=&goodsdescription=<div>名称</div>&goodsname=#245b24&goodsstatus=0&id=37&imgsrc=&price=4&publishstatus=0&shortbrief=1&storecount=10&updatetime=";
      String b = "/admin/goods_rest/(update|save)[\\s\\S]*";
        System.out.println(s.matches(b));
    }
}
