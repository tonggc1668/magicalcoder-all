/**
 *  工具类
 *  v 0.0.1
 */

layui.define(['jquery'],function(exports){
    var obj = {
        //获取浏览器入参
        getParameter:function (name) {
            var query = window.location.search.substring(1);
            if(query!=''){
                var vars = query.split("&");
                for (var i=0;i<vars.length;i++) {
                    var pair = vars[i].split("=");
                    if(pair.length=2){
                        if(pair[0] == name){return pair[1];}
                    }
                }
            }
            return null;
        }
    }

    exports('magicalcoderutil',obj);
})
