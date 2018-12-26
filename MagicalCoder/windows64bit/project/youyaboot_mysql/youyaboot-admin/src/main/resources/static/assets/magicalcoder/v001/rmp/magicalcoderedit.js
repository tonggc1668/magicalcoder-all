/**
 *  详情页统一处理
 *  v 0.0.1
 */
layui.config({
    base : "assets/"
}).extend({
    "magicalcoderlaytools" : "magicalcoder/v001/rmp/magicalcoderlaytools"
})
layui.define(['form','layer','layedit','laydate','upload','magicalcoderlaytools','magicalcoderverify','magicalcoderutil','mc_rmp','mc_children'],function(exports){
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery,
        magicalcoderverify = layui.magicalcoderverify,
        magicalcoderutil = layui.magicalcoderutil,
        mc_rmp = layui.mc_rmp,
        mc_children = layui.mc_children,
        magicalcoderlaytools = null ;
//自定义验证规则
    var obj = {
        bindFormArea : function () {
            this._initAreaValue();
        },
        //初始化表单
        _initAreaValue : function () {
            var _this = this;
            var tableNameRest = this.config.tableNameRest;
            var moduleName = this.config.moduleName;
            var identify = this.config.identify;
            if(identify!=null && identify!=''){//编辑
                $.getJSON('admin/'+tableNameRest+"/get/"+identify,{},function (data) {
                    if(data.flag){
                        var entity = data.data
                        if(entity && entity!=null){
                            _this._autoSetValue(entity)
                            mc_children.disabledFormParentArea();
                            mc_rmp.paintBody(moduleName,function () {
                                _this._bindFormDom()
                            })
                        }else {
                            layer.msg("此条记录已被删除,请重新查询！");
                        }
                    }else {
                        layer.msg("查询失败:"+data.desc);
                    }

                })
            }else {//新增
                mc_children.disabledFormParentArea();
                mc_rmp.paintBody(moduleName,function () {
                    _this._bindFormDom()
                    $("button[type='reset']").removeClass('layui-hide');
                })
            }
        },
        _autoSetValue : function (entityData) {
            var body = $("body");
            if(entityData){
                for(var key in entityData){
                    var value = entityData[key]
                    if(value != null){
                        var input = body.find("[name='"+key+"']");
                        if(!input){
                            continue;
                        }
                        var laySkin = input.attr("lay-skin")
                        if(laySkin && laySkin == 'switch'){//忽略开关
                            continue;
                        }
                        //外键select2
                        var className = input.attr("class")
                        if(className && className.indexOf("magicalcoder-foreign-select2")!=-1){
                            input.attr("data-value",value);
                            continue;
                        }
                        //设置radio值
                        var type = input.attr("type")
                        if(type && type=='radio' && value && value!=''){
                            body.find("[name='"+key+"'][value='" + value + "']").prop("checked", "checked");
                            continue
                        }
                        //如果有图片或者文件则自动设置图片
                        var inputImg = body.find(".img_"+key);
                        if(inputImg){
                            inputImg.attr("src",value);
                        }
                        //其他正常表单
                        input.val(value);
                    }
                }
                //开关单独处理
                body.find("input[lay-skin='switch']").each(function (index,item) {
                    var name = $(item).attr("name")
                    var check = (entityData[name]+''=='true' || entityData[name]+''=='1') ? true:false
                    $(item).prop("checked",check);
                })
                form.render();
            }
        },
        _bindFormDom : function () {
            //寻找所有日期控件 自动注册
            magicalcoderlaytools.bindDate(".magicalcoder-laydate");
            //寻找所有图片上传
            magicalcoderlaytools.bindUpload(".magicalcoder-layupload");
            //外键下拉
            $(".magicalcoder-foreign-select2").each(function (index,item) {
                var id = $(item).attr("id")
                var name = $(item).attr("name")
                if(id){
                    magicalcoderlaytools.bindForeignSelect2("#"+id,{width:"100%"});
                }else {
                    magicalcoderlaytools.bindForeignSelect2("[name='"+name+"']",{width:"100%"});
                }
            })
            //寻找所有的富文本控件
            var editValidFormData = magicalcoderlaytools.bindEdit(".magicalcoder-layedit");
            for(var key in editValidFormData){//拷贝一下
                this.config.formVerifyEditData[key] = editValidFormData[key]
            }
            form.verify(this.config.formVerifyEditData)//必须保证formVerifyEditData有富文本校验key
            //寻找滑块
            magicalcoderlaytools.bindSlider(".magicalcoder-slider");
            //寻找颜色选择器
            magicalcoderlaytools.bindColorPicker(".magicalcoder-color-picker");
            //寻找评星
            magicalcoderlaytools.bindRate(".magicalcoder-rate");
            //寻找代码修饰器
            magicalcoderlaytools.bindCode(".magicalcoder-code");
        },
        //表单提交事件
        submitEvents : function (submitBeforeCallBack) {
            $("button[type='reset']").click(function(){
                $(".magicalcoder-foreign-select2").val(null).trigger('change');
                return true;
            });
            var tableNameRest = this.config.tableNameRest
            //表单校验 在_bindFormDom方法
            form.on("submit(save)",function(data){
                // 实际使用时的提交信息
                var postData = data.field;
                //开关单独处理
                $('body').find("input[lay-skin='switch']").each(function (index,item) {
                    var name = $(item).attr("name")
                    var field = data.field
                    postData[name] = field[name] ? ((field[name]+''=='on' || field[name]+'' == 'true')  ? 1 : 0):0;
                })
                //表单校验
                for(var name in postData){//清理上次的不通过样式
                    var dom = $("[name='"+name+"']")
                    magicalcoderverify.clearDangerClass(dom)
                }
                //校验
                for(var name in postData){
                    var dom = $("[name='"+name+"']")
                    var value = postData[name]
                    if(!magicalcoderverify.verify(value,dom)){
                        return false;
                    }
                }
                if(submitBeforeCallBack && submitBeforeCallBack!=null){//提交前事件
                    var success = submitBeforeCallBack(postData);
                    if(!success){
                        return false;
                    }
                }
                //弹出loading
                var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
                $.post('admin/'+tableNameRest+'/save',postData,function(res){
                    top.layer.close(index);
                    if(res.flag){
                        parent.$(".search_btn").click()
                        // layer.closeAll("iframe");
                        //不明白为啥要-1才能获取当前层 很奇怪
                        var pidx = parent.layui.layer.index
                        parent.layui.layer.close(pidx-1);
                    }else {
                        top.layer.msg("保存失败:"+res.desc);
                    }
                })
                return false;
            })
        },
        init : function (config) {
            //从url构建外键 允许不传
            var identify = magicalcoderutil.getParameter('identify')//主键
            this.config = config;
            this.config.identify = identify;
            return this;
        }
    }

    exports('magicalcoderedit',function(config){
        magicalcoderlaytools = layui.magicalcoderlaytools(config);
        return obj.init(config);
    });
})
