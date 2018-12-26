/**
 *  封装所有场所关于select2的用法
 * v 0.0.1
 * https://select2.org/data-sources/ajax
 */
layui.config({
    base : "assets/"
}).extend({
    "select2":"magicalcoder/v002/rmp/select2",
    "magicalcoderverify" : "magicalcoder/v002/rmp/magicalcoderverify"
})
layui.define(['form','layer','laydate','table','laytpl','select2','magicalcoderverify'],function(exports){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        magicalcoderverify = layui.magicalcoderverify,
        $ = layui.jquery;

    var obj = {
        //表格内的普通单选按钮自动提交
        bindTableSingleSelect2 : function (domId,configData,selectChangeFailCallback) {
            configData = configData || {width:"150px",allowClear:false}
            var placeholder = $(domId).attr("placeholder") || '搜索'
            $(domId).select2({placeholder:placeholder,width:configData.width,allowClear:configData.allowClear});
            this._bindTableSelect2AjaxChangeValueEvent(domId,selectChangeFailCallback)
        },

        //寻找所有控件 自动注册 https://select2.org/data-sources/ajax
        bindForeignSelect2ByDomId : function (domId,configData,selectChangeFailCallback) {
            configData = configData || {allowClear:true,width:"150px"}
            this._buildForeignSelect2(domId,configData)
            this._tryToAjaxSetContent(domId)
            this._bindTableSelect2AjaxChangeValueEvent(domId,selectChangeFailCallback)
        },
        _buildForeignSelect2 : function (domId,configData) {
            var _this = this;
            var placeholder = $(domId).attr("placeholder") || '搜索'
            $(domId).select2({
                allowClear : configData.allowClear || true,//必须设置placeholder 否则报错
                placeholder: placeholder,
                width : configData.width || "150px",
                delay : 500,//等待500ms才触发
                ajax : {
                    url:function (params) {
                        var dataUrl = $(this).attr("data-url");
                        var dataId = $(this).attr("data-id");
                        var fields = $(this).attr("data-text-fields");
                        var extra = {dataId : dataId,fields:fields}
                        params.extra = extra
                        return dataUrl;
                    },
                    data : function (params) {//查询参数
                        //params能拿到搜索框数据
                        var keyword = params.term
                        if(keyword && keyword!=''){
                            return {"keyword":keyword,"limit":20}
                        }else {
                            return {"limit":20}
                        }
                    },
                    processResults : function (data,params,item) {
                        if(data.flag){
                            var dataId = params.extra.dataId;
                            var fields = params.extra.fields;
                            var list = data.data;
                            var result = _this._buildSelect2IdTextData(list,dataId,fields)
                            return {"results":result}
                        }else {
                            layer.msg("获取数据失败:"+data.desc)
                        }
                        return {"results":[]}
                    }
                }
            });
        },
        //需要重置默认值 当data-value存在时才会尝试
        _tryToAjaxSetContent : function (domId) {
            var _this = this;
            //不确定domId的元素数量
            $(domId).each(function(idx,item){
                //缓存优化
                var selectDom = $(item)
                var value = selectDom.attr("data-value");//决定是否进行初始化 编辑页使用
                if(value && value!=''){
                    var dataUrl = selectDom.attr("data-url");
                    var fields = selectDom.attr("data-text-fields");
                    var dataId = selectDom.attr("data-id");
                    $.getJSON(dataUrl,{uniqueField:dataId,uniqueValue:value},function (data) {
                        if(data.flag){
                            var list = data.data;
                            var result = _this._buildSelect2IdTextData(list,dataId,fields)
                            if(result.length>0){
                                var item = result[0]
                                var option = new Option(item.text,item.id, true, true);
                                selectDom.append(option);
                                //.trigger('change');
                                /*selectDom.trigger({
                                    type: 'select2:select',
                                    params: {
                                        data: item
                                    }
                                });*/
                            }
                        }
                    })
                }
            })

        },
        _buildSelect2IdTextData : function (list,dataId,fields) {
            var results = []
            if(list){
                for(var j =0;j<list.length;j++){
                    var item = list[j];
                    var id = item[dataId]
                    var textArr = []
                    if(fields!=''){
                        var arr = fields.split(",");
                        for(var k=0;k<arr.length;k++){
                            textArr.push(item[arr[k]])
                        }
                    }else {
                        textArr.push(id)
                    }
                    results.push({id:id,text:textArr.join("||")})
                }
            }
            return results;
        },
        //表格中select2改变值 触发事件
        _bindTableSelect2AjaxChangeValueEvent : function (select2DomId,selectChangeFailCallback) {
            var select2 = $(select2DomId)
            var tableNameRest = this.config.tableNameRest;
            select2.on('select2:select', function (e) {
                var value = e.params.data.id;
                var _t = $(this)
                var identify = _t.attr("data-identify")
                if(identify && identify!=''){
                    var name = _t.attr("name")
                    var index = layer.msg('修改中，请稍候',{icon: 16,time:true,shade:0.8});
                    var postData = {}//必须设置主键
                    postData[name] = value;
                    if(!magicalcoderverify.verify(value,_t)){
                        return;
                    }
                    $.post('admin/'+tableNameRest+'/update/'+identify,postData,function (data) {
                        layer.close(index);
                        if(data.flag){
                            layer.msg("修改成功！");
                        }else {
                            layer.msg("修改失败:"+data.desc);
                            if(selectChangeFailCallback){
                                selectChangeFailCallback()
                            }
                        }
                    })
                }
            });
        },
        init : function (config) {
            this.config = {
                tableNameRest:config.tableNameRest,
                tableName:config.tableName
            }
            return this;
        }
    }

    exports('magicalcoderselect2',function(config){ return obj.init(config)});
})
