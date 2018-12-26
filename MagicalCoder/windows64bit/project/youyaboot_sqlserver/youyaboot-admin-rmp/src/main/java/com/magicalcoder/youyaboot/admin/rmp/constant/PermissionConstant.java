package com.magicalcoder.youyaboot.admin.rmp.constant;

public interface PermissionConstant {

    String CODE_PREFIX = "code_";

    String ADMIN_PREFIX = "/admin/";//将要被权限系统拦截的前缀
    String LOGIN_FORM_URL = "/login";//登陆地址


    enum Platform implements PermissionConstant{
        BACKEND("backend"),FRONT("front"),FRONT_BACKEND("front_backend");//双端共有配置
        private String value;
        Platform(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        public static Platform build(String value){
            if(BACKEND.value.equals(value)){
                return BACKEND;
            }else if(FRONT.value.equals(value)){
                return FRONT;
            }else if(FRONT_BACKEND.value.equals(value)){
                return FRONT_BACKEND;
            }
            return null;
        }
    }
    enum FrontAction implements PermissionConstant{
        SHOW("show"),DISABLED("disabled");
        private String value;
        FrontAction(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
        public static FrontAction build(String value){
            if(SHOW.value.equals(value)){
                return SHOW;
            }else if(DISABLED.value.equals(value)){
                return DISABLED;
            }
            return null;
        }
    }

}
