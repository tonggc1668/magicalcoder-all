/**
 *  前端权限模块控制中心
 *  v 0.0.1
 */

layui.define(['jquery'],function(exports){
    var $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer
    var obj = {
        //根据二级菜单名称来重绘页面权限模块
        paintBody : function (moduleName,callback) {
            var _this = this;
            $.getJSON("admin/rest_rmp/get_permission_list?moduleName="+moduleName,{date:new Date().getTime()},function (data) {
                if(data.flag){
                    //先把需要权限控制的进行隐藏
                    if(data.data){
                        var sysPermissionDtoList = data.data.sysPermissionDtoList
                        //预设元素隐藏
                        if(sysPermissionDtoList!=null && sysPermissionDtoList.length>0){
                            for(var i=0; i<sysPermissionDtoList.length;i++){
                                var permission = sysPermissionDtoList[i]
                                if(permission && permission!=null){
                                    var frontDom = permission.frontDom
                                    if(frontDom!=null && frontDom!=''){
                                        frontDom = frontDom.replace("，",",")
                                        var frontDomArr = frontDom.split(",")
                                        for(var k=0;k<frontDomArr.length;k++) {//逗号分割了多个节点
                                            if (!permission.hasPermission) {//没权限的都隐藏起来吧
                                                $(frontDomArr[k]).addClass("security-hide")//不用layui-hide避免跟layui冲突 藕合一起
                                            } else {//有权限的统一不加隐藏 判断下禁用逻辑
                                                var frontAction = permission.frontAction;
                                                if (frontAction && frontAction != null && frontAction != '') {
                                                    if (frontAction == 'disabled') {
                                                        $(frontDomArr[k]).find('input').attr("disabled", "disabled");
                                                        $(frontDomArr[k]).find('select').attr("disabled", "disabled");
                                                        $(frontDomArr[k]).find('select').css("pointer-events","none");
                                                        $(frontDomArr[k]).find('textarea').attr("disabled", "disabled");
                                                        $(frontDomArr[k]).find('button').attr("disabled", "disabled");
                                                        $(frontDomArr[k]).find('a').css("pointer-events","none");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }else {
                    layer.alert("获取此模块下的权限失败:"+data.desc)
                }
                if(callback){
                    callback()
                }
                $("body").removeClass("layui-hide")
            })
        }
    }

    exports('mc_rmp',obj);
})
