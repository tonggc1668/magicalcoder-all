/**
 *  结合layui的所有控件 统一处理
 *  v 0.0.1
 */
layui.config({
    base : "assets/"
}).extend({
    "magicalcoderselect2" : "magicalcoder/v001/rmp/magicalcoderselect2",
    "mc_rmp" : "magicalcoder/v001/rmp/mc-rmp",
    "mc_children" : "magicalcoder/v001/rmp/mc-children"
})
layui.define(['form','layer','layedit','laydate','upload','magicalcoderselect2','magicalcoderverify','magicalcoderutil','slider','colorpicker','rate','code'],function(exports){
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        slider = layui.slider,
        rate = layui.rate,
        colorpicker = layui.colorpicker,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery,
        magicalcoderverify = layui.magicalcoderverify,
        magicalcoderutil = layui.magicalcoderutil,
        magicalcoderselect2 = null ;
//自定义验证规则
    var obj = {
        bindRadio : function (elem,clickFailCallback) {
            var tableNameRest = this.config.tableNameRest;
            $(elem).each(function (index, item) {
                var name = $(item).attr("name")//由于radio name不能重复 所以用_+行号来区分
                var arr = name.split("_");
                name = arr[0]
                var id = $(item).attr("id")
                var filter = $(item).attr("lay-filter")
                var select = name ? name : id
                form.on('radio('+filter+')', function(domData){
                    var _t = $(this)
                    var identify = _t.attr("data-identify")
                    if(identify && identify!=''){//ajax更新结果
                        var value = domData.elem.value;
                        var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                        var postData = {}//必须设置主键
                        postData[select] = value;
                        if(!magicalcoderverify.verify(value,$(item))){
                            return;
                        }
                        $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                            layer.close(index);
                            if(data.flag){
                                layer.msg("修改成功！");
                            }else {
                                layer.msg("修改失败:"+data.desc);
                                if(clickFailCallback){
                                    clickFailCallback()
                                }
                            }
                            form.render('radio');//这一步必须执行 否则改了不生效
                        })
                    }
                })
            })
        },
        //普通输入框 输入完成离开事件
        bindInputText : function (inputElem,blurFailCallback) {
            var tableNameRest = this.config.tableNameRest;
            $(inputElem).each(function (index,item) {//必须1个个绑定 不支持单利
                var originValue = ''
                $(item).focus(function () {
                    originValue =$(this).val()
                })
                $(item).blur(function () {
                    var newValue = $(this).val()
                    if(originValue != newValue){//需要更新
                        var identify = $(item).attr("data-identify")
                        if(identify && identify!=''){
                            var name = $(item).attr("name")
                            var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                            var postData = {}
                            postData[name] = newValue;
                            if(!magicalcoderverify.verify(newValue,$(item))){
                                return;
                            }
                            $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                                layer.close(index);
                                if(data.flag){
                                    layer.msg("修改成功！");
                                }else {
                                    layer.msg("修改失败:"+data.desc);
                                    $(item).val(originValue)//还原
                                }
                            })
                        }
                    }
                })
            })
        },
        //綁定日期控件
        bindDate : function (elem,doneFailCallback) {
            var tableNameRest = this.config.tableNameRest;
            var _t = this;
            $(elem).each(function (index,item) {//必须1个个绑定 不支持单利
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {}
                $.extend(true,formConfig,_t.config.form);
                var renderConfig = {}
                if(formConfig[name]){
                    renderConfig = formConfig[name].renderConfig
                }else {//可能是查询区域
                    var realName = null;
                    var dataName = $(item).attr("data-name")
                    if(dataName && dataName.length>0){
                        realName = dataName
                    }else {//老代码兼容
                        if(name.lastIndexOf("First")!=-1){
                            realName = name.substring(0,name.lastIndexOf("First"))
                        }
                        if(name.lastIndexOf("Second")!=-1){
                            realName = name.substring(0,name.lastIndexOf("Second"))
                        }
                    }
                    if(realName!=null){
                        if(formConfig[realName]){
                            renderConfig = formConfig[realName].renderConfig
                        }
                    }
                }
                var select = id ? "#"+id : "[name='"+name+"']"//列表页有id 所以不会批量绑定
                var identify = $(item).attr("data-identify")
                var value = $(item).val()
                if(value && value!=''){//智能点 默认给当前时间做初始值
                    var reg=/^(\d{4})[-\/].*/;
                    if(!reg.test(value)){//非日期字符串格式 尝试解析字符串
                        try {
                            if(value.toUpperCase()=='CURRENT_TIMESTAMP'){
                                value = new Date()
                            }else {
                                value = eval(value)
                            }
                        }catch (e){
                            value = ''
                        }
                    }
                }
                $.extend(renderConfig,{
                    elem : renderConfig['elem'] || select,
                    type : renderConfig['type'] || 'datetime',
                    value : renderConfig['value']|| value,
                    format : renderConfig['format'] || 'yyyy-MM-dd HH:mm:ss',
                    trigger : renderConfig['trigger'] || 'click',
                    done : renderConfig['done'] || function (value,date,endDate) {
                        if(identify && identify!=''){
                            var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                            var postData = {}//必须设置主键
                            postData[name] = value;
                            if(!magicalcoderverify.verify(value,$(item))){
                                return;
                            }
                            $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                                layer.close(index);
                                if(data.flag){
                                    layer.msg("修改成功！");
                                }else {
                                    layer.msg("修改失败！"+data.desc);
                                    if(doneFailCallback){
                                        doneFailCallback()
                                    }
                                }
                            })
                        }
                    }
                })
                laydate.render(renderConfig);
            })
        },
        //绑定文件上传组件
        bindUpload : function (elem,doneFailCallback) {
            var _t = this;
            $(elem).each(function (index,item) {
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {};
                $.extend(true,formConfig,_t.config.form);//深拷贝
                var renderConfig = formConfig[name] ? formConfig[name].renderConfig : {}
                var select = name ? name : id
                $.extend(renderConfig,{
                    elem: renderConfig['elem'] || '.a_'+select,
                    url: renderConfig['url'] || 'admin/common_file_rest/upload',
                    method: renderConfig['method'] || "post",
                    accept: renderConfig['accept'] || "file",//允许上传普通文件 ，去掉则只支持图片类型
                    before: renderConfig['before'] || function (obj) {
                        layer.load()
                    },
                    done: renderConfig['done'] || function(res, index, upload){
                        layer.closeAll('loading')
                        if(res.flag){
                            var src = res.data.src;
                            $("input[name='"+select+"']").val(src);
                            $(".img_"+select).attr("src",src);
                        }else {
                            layer.alert("上传失败:"+res.desc)
                            if(doneFailCallback){
                                doneFailCallback();
                            }
                        }
                    },
                    error: renderConfig['error'] || function(index, upload){
                        layer.closeAll('loading')
                        layer.alert("上传失败，请重试")
                        if(doneFailCallback){
                            doneFailCallback();
                        }
                    }
                })
                upload.render(renderConfig);
            })
        },
        bindSingleSelect2 : function (singleSelect2,select2ConfigData,selectChangeFailCallback) {
            magicalcoderselect2.bindTableSingleSelect2(singleSelect2,select2ConfigData,selectChangeFailCallback);
        },
        bindForeignSelect2 : function (foreignSelect2,select2ConfigData,selectChangeFailCallback) {
            magicalcoderselect2.bindForeignSelect2ByDomId(foreignSelect2,select2ConfigData,selectChangeFailCallback);
        },
        bindSwitchAjaxUpdate : function (elem,doneFailCallback) {//表格中出现也得用 form.on(switch)监听 因为表格暂时没这个处理方法
            var tableNameRest = this.config.tableNameRest;
            $(elem).each(function (index, item) {
                var name = $(item).attr("name")
                var id = $(item).attr("id")
                var filter = $(item).attr("lay-filter")
                var select = name ? name : id
                form.on('switch('+filter+')', function(domData){
                    var _t = $(this)
                    var identify = _t.attr("data-identify")
                    if(identify && identify!=''){//ajax更新结果
                        var targetChecked = domData.elem.checked;
                        var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                        var postData = {}//必须设置主键
                        var value = targetChecked?1:0
                        postData[select] = value;
                        if(!magicalcoderverify.verify(value,$(item))){
                            return;
                        }
                        $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                            layer.close(index);
                            if(data.flag){
                                _t.prop("checked", targetChecked);
                                layer.msg("修改成功！");
                            }else {
                                layer.msg("修改失败:"+data.desc);
                                _t.prop("checked", !targetChecked);
                            }
                            form.render('checkbox');//这一步必须执行 否则改了不生效
                        })
                    }
                })
            })
        },
        //绑定富文本 并返回校验同步框 供表单提交使用
        bindEdit : function (elem) {
            var formVerifyEditData = {}
            $(elem).each(function (index,item) {
                var id = $(item).attr("id")
                //创建一个编辑器
                var editIndex = layedit.build(id,{
                    height : 300,
                    uploadImage : {
                        type : "post",
                        url : "admin/common_file_rest/upload"
                    }
                });
                //require 校验富文本编辑框时 同步一下隐藏的textarea值 只有在这里同步才生效 VerifyEdit 参见edit.html lay-verify
                formVerifyEditData[id+"VerifyEdit"] = function(val){
                    layedit.sync(editIndex);
                }
            })
            return formVerifyEditData;
        },
        //绑定滑块 不建议在列表页使用
        bindSlider : function (elem) {
            var _t = this
            $(elem).each(function (index,item) {//必须1个个绑定 不支持单利
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {};
                $.extend(true,formConfig,_t.config.form);//深拷贝
                var renderConfig = formConfig[name] ? formConfig[name].renderConfig : {}
                var select = id ? "#"+id : "[name='"+name+"Slider']"//列表页有id 所以不会批量绑定

                $.extend(renderConfig,{
                    elem : renderConfig['elem'] || select,
                    value : renderConfig['value']|| $(item).val(),
                    change : renderConfig['change'] || function (value) {
                        $(item).val(value)
                    }
                })
                slider.render(renderConfig);
            })
        },
        //颜色选择器
        bindColorPicker : function (elem,doneFailCallback) {
            var tableNameRest = this.config.tableNameRest;
            var _t = this;
            $(elem).each(function (index,item) {//必须1个个绑定 不支持单利
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {}
                $.extend(true,formConfig,_t.config.form);
                var renderConfig = formConfig[name] ? formConfig[name].renderConfig : {}
                var select = id ? "#"+id+'ColorPicker' : "[name='"+name+"ColorPicker']"//列表页有id 所以不会批量绑定
                var identify = $(item).attr("data-identify")
                var value = $(item).val()
                $.extend(renderConfig,{
                    elem : renderConfig['elem'] || select,
                    format : renderConfig['format']|| 'hex',
                    color : renderConfig['color']|| value,
                    done : renderConfig['done'] || function (color) {
                        $(item).val(color)
                        if(identify && identify!=''){
                            var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                            var postData = {}//必须设置主键
                            postData[name] = color;
                            if(!magicalcoderverify.verify(color,$(item))){
                                return;
                            }
                            $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                                layer.close(index);
                                if(data.flag){
                                    layer.msg("修改成功！");
                                }else {
                                    layer.msg("修改失败！"+data.desc);
                                    if(doneFailCallback){
                                        doneFailCallback()
                                    }
                                }
                            })
                        }
                    }
                })
                colorpicker.render(renderConfig);
            })
        },
        //评分
        bindRate : function (elem,doneFailCallback) {
            var tableNameRest = this.config.tableNameRest;
            var _t = this;
            $(elem).each(function (index,item) {//必须1个个绑定 不支持单利
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {}
                $.extend(true,formConfig,_t.config.form);
                var renderConfig = formConfig[name] ? formConfig[name].renderConfig : {}
                var select = id ? "#"+id+'Rate' : "[name='"+name+"Rate']"//列表页有id 所以不会批量绑定
                var identify = $(item).attr("data-identify")
                var value = $(item).val()
                $.extend(renderConfig,{
                    elem : renderConfig['elem'] || select,
                    length : renderConfig['length']|| 5,
                    value : renderConfig['value']|| value,
                    theme : renderConfig['theme']|| '#FFB800',
                    half : renderConfig['half']|| false,
                    text : renderConfig['text']|| false,
                    readonly : renderConfig['readonly']|| false,
                    choose : renderConfig['choose'] || function (val) {
                        $(item).val(val)
                        if(identify && identify!=''){
                            var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                            var postData = {}//必须设置主键
                            postData[name] = val;
                            if(!magicalcoderverify.verify(val,$(item))){
                                return;
                            }
                            $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                                layer.close(index);
                                if(data.flag){
                                    layer.msg("修改成功！");
                                }else {
                                    layer.msg("修改失败！"+data.desc);
                                    if(doneFailCallback){
                                        doneFailCallback()
                                    }
                                }
                            })
                        }
                    }
                })
                rate.render(renderConfig);
            })
        },
        //代码修饰器
        bindCode : function (elem,doneFailCallback) {
            var _t = this;
            $(elem).each(function (index,item) {//必须1个个绑定 不支持单利
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                var formConfig = {}
                $.extend(true,formConfig,_t.config.form);
                var renderConfig = formConfig[name] ? formConfig[name].renderConfig : {}
                var select = id ? "#"+id+'Code' : "[name='"+name+"Code']"//列表页有id 所以不会批量绑定
                var value = $(item).val()
                var elem = renderConfig['elem'] || select
                $(elem).html(value);
                $.extend(renderConfig,{
                    elem : elem,
                    title : renderConfig['title']|| '',
                    height : renderConfig['height']|| '200px',
                    encode : renderConfig['encode']|| true,
                    skin : renderConfig['skin']|| 'notepad',
                    about : renderConfig['about']|| false
                })
                layui.code(renderConfig);
            })
        },
        getMagicalcoderSelect2 : function () {
            return magicalcoderselect2;
        },
        init : function (config) {
            this.config = config
            return this;
        }
    }

    exports('magicalcoderlaytools',function(config){
        magicalcoderselect2 = layui.magicalcoderselect2(config);
        return obj.init(config);
    });
})
