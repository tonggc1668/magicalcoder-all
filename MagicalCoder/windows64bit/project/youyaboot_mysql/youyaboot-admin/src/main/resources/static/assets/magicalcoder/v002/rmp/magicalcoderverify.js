/**
 *  校验规则表
 *  v 0.0.1
 */
layui.config({
    base : "assets/"
}).extend({
    "magicalcoderutil" : "magicalcoder/v002/rmp/magicalcoderutil"
})
layui.define(['jquery','layer'],function(exports){
    var layer = parent.layer === undefined ? layui.layer : top.layer;
    var dangerClass = "layui-form-danger"
    var magicalDangerClass = "form-danger" //有些控件聚焦也不行
    var obj = {
        //来自layui提供的验证 直接拷贝过来 layui预先验证 如果对layedit进行required,那你会发现第一次无法自动设值就被拦截了
        layuiVerify : {
            mc_required: function (value,item) {
                var reg=/[\S]+/;
                if(!reg.test(value)){
                    return "必填项不能为空";
                }
            },
            mc_phone: function (value,item) {
                if(value && value!=''){
                    var reg=/^1\d{10}$/;
                    if(!reg.test(value)){
                        return "请输入正确的手机号";
                    }
                }
            },
            mc_email: function (value,item) {
                if(value && value!=''){
                    var reg=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                    if(!reg.test(value)){
                        return "邮箱格式不正确";
                    }
                }
            },
            mc_url: function (value,item) {
                if(value && value!=''){
                    var reg=/(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/;
                    if(!reg.test(value)){
                        return "链接格式不正确";
                    }
                }
            },
            mc_number: function(e) {
                if (!e || isNaN(e)) return "只能填写数字"
            },
            mc_date: function (value,item) {
                if(value && value!=''){
                    var reg=/^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/;
                    if(!reg.test(value)){
                        return "日期格式不正确";
                    }
                }
            },
            mc_identity:function (value,item) {
                if(value && value!=''){
                    var reg=/(^\d{15}$)|(^\d{17}(x|X|\d)$)/;
                    if(!reg.test(value)){
                        return "请输入正确的身份证号";
                    }
                }
            }
        },
        //最新扩展的验证
        magicalVerify : {
            mc_digits : function (value,item) {
                if(value && value!=''){
                    var reg=/^[-+]?\d*$/;
                    if(!reg.test(value)){
                        return "必须为整数";
                    }
                }
            },
            mc_chinese_character : function (value,item) {
                if(value && value!='') {
                    var reg = /[\u4e00-\u9fa5]+/;
                    if (!reg.test(value)) {
                        return "请使用中文汉字";
                    }
                }
            },
            mc_variable : function (value,item) {
                if (value && value != '') {
                    var reg = /[A-Za-z0-9_]+/;
                    if (!reg.test(value)) {
                        return "请使用字母数字下划线组合";
                    }
                }
            },
            magicalCoderVerify : function (value,$item) {
                if(value && value!='') {
                    if(!($item instanceof layui.jquery)){//判断是否是jquery对象
                        $item = $($item);
                    }
                    var magicalCoderVerify=$item.attr("magicalcoder-verify")
                    if(magicalCoderVerify && magicalCoderVerify!=''){
                        var verifyArr = magicalCoderVerify.split("|");
                        if(verifyArr.length>0){
                            for(var i=0;i<verifyArr.length;i++){
                                var rule = verifyArr[i];
                                if(rule!=''){
                                    var rules = rule.split("=");
                                    if(rules.length==2){
                                        var key = rules[0]
                                        var setValue = rules[1]
                                        switch (key){
                                            case 'maxValues':
                                                if(!isNaN(value) && !isNaN(setValue)){
                                                    if(parseFloat(value)>parseFloat(setValue)){
                                                        return "最大值 "+setValue;
                                                    }
                                                }
                                                break;
                                            case 'minValues':
                                                if(!isNaN(value) && !isNaN(setValue)){
                                                    if(parseFloat(value)<parseFloat(setValue)){
                                                        return "最小值 "+setValue;
                                                    }
                                                }
                                                break;
                                            case 'minLength':
                                                if(value.length < parseInt(setValue)){
                                                    return "最小长度 "+setValue;
                                                }
                                                break;
                                            case 'maxLength':
                                                if(value.length > parseInt(setValue)){
                                                    return "最大长度 "+setValue;
                                                }
                                                break;
                                            case 'definedOne':
                                            case 'definedTwo':
                                            case 'definedThree':
                                                var reg=new RegExp(setValue,'g');
                                                if (!obj.regExpMatch(reg,value)){
                                                    return "不满足 "+setValue;
                                                }
                                                break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        regExpMatch:function(reg,v){
            var arr = v.match(reg);
            if (arr){
                return arr[0]==v
            }
            return false;
        },
        //全部规则都校验
        clearDangerClass:function($item){
            //select2控件
            if($item.hasClass("magicalcoder-foreign-select2") || $item.hasClass("magicalcoder-table-single-select2")){
                //如果是select2
                var parent = $item.parent()
                if(parent){
                    parent.find(".select2-selection--single").removeClass(magicalDangerClass)
                }
            }
            //layedit控件
            else if($item.hasClass("magicalcoder-layedit")){
                //如果是select2
                var parent = $item.parent()
                if(parent){
                    parent.find(".layui-layedit").removeClass(magicalDangerClass)
                }
            }
            //select 控件
            else if($item.is("select")){
                var parent = $item.parent()
                if(parent){
                    parent.find("input.layui-unselect").removeClass(dangerClass)
                }
            }else {
                $item.removeClass(dangerClass)
            }
        },
        verify : function (value, $item) {
            var layuiVerify = this.layuiVerify;
            var magicalVerify = this.magicalVerify;
            var verifyRule = $.extend(layuiVerify,magicalVerify);
            var layVerify = $item.attr("lay-verify");
            if(layVerify){
                for(var rule in verifyRule){
                    if(('|'+layVerify+'|').indexOf('|'+rule+'|')!=-1){
                        var ruleFunc = verifyRule[rule]
                        var msg = ruleFunc(value,$item)
                        if(msg && msg.length>0){
                            layer.msg(msg,{
                                icon: 5,
                                    shift: 6
                            })
                            //select2控件
                            if($item.hasClass("magicalcoder-foreign-select2") || $item.hasClass("magicalcoder-table-single-select2")){
                                //如果是select2
                                var parent = $item.parent()
                                if(parent){
                                    var child = parent.find(".select2-selection--single")
                                    child.addClass(magicalDangerClass)
                                    child.focus()
                                }
                            }
                            //layedit控件
                            else if($item.hasClass("magicalcoder-layedit")){
                                //如果是select2
                                var parent = $item.parent()
                                if(parent){
                                    var child = parent.find(".layui-layedit")
                                    child.addClass(magicalDangerClass)
                                    child.focus()
                                }
                            }
                            //select 控件
                            else if($item.is("select")){
                                var parent = $item.parent()
                                if(parent){
                                    var child = parent.find("input.layui-unselect")
                                    child.addClass(dangerClass)
                                    child.focus()
                                }
                            }else {
                                //普通控件
                                $item.addClass(dangerClass)
                                $item.focus();
                            }
                            return false;
                        }
                    }
                }
            }
            this.clearDangerClass($item)
            return true;
        }
    }

    exports('magicalcoderverify',obj);
})
