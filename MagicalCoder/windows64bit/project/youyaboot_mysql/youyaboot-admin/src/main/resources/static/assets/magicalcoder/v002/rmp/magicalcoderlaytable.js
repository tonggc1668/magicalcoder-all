/**
 *  封装laytable 增强功能 简单配置就能处理各种各样的表格
 *  v 0.0.1
 */
layui.config({
    base : "assets/"
}).extend({
    "magicalcoderlaytools" : "magicalcoder/v002/rmp/magicalcoderlaytools"
})
layui.define(['form','layer','layedit','table','laydate','upload','magicalcoderlaytools','mc_rmp','mc_children'],function(exports){
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laytpl = layui.laytpl,
        laydate = layui.laydate,
        table = layui.table,
        mc_rmp = layui.mc_rmp,
        mc_children = layui.mc_children,
        $ = layui.jquery,
        magicalcoderlaytools = null ;
//自定义验证规则
    var obj = {
        //初始化一个表格
        initTable : function (cols,event) {
            var _this = this;
            var tableNameRest = this.config.tableNameRest;
            var moduleName = this.config.moduleName;
            var tableEvent = {
                "tool" :null,
                "sort" :null
            }
            $.extend(tableEvent,event)
            table.render({
                elem: this.config.layTable.elem,
                url : 'admin/'+tableNameRest+'/page',
                cellMinWidth : 95,
                page : true,
                height : "full-150",
                // height : 500,
                limit : 20,
                limits : [10,15,20,25],
                id : this.config.layTable.id,
                cols : cols,
                where : this._buildQueryFormParams(""),
                done:function (res,curr,count) {
                    if(res.flag){
                        mc_rmp.paintBody(moduleName,function () {
                            //禁用一些外键字段
                            mc_children.disabledTableParentArea();
                            _this.tableInitSuccessCallback()
                            _this.tableOperateEvent(tableEvent)
                        })
                    }else {
                        layer.msg(res.desc)
                    }
                }
            });
        },
        //列表页表格太小 不适合做2个控件放进去 就保存原有的输入框吧 日期这些可以做一下
        tableInitSuccessCallback : function () {
            var _this = this;
            var configData = {width:"200px",allowClear:false}
            magicalcoderlaytools.bindSingleSelect2(".magicalcoder-table-single-select2",configData,function () {_this._refreshTableFromCurrentPage()})
            magicalcoderlaytools.bindForeignSelect2(".magicalcoder-table-foreign-select2",configData,function () {_this._refreshTableFromCurrentPage()});
            magicalcoderlaytools.bindSwitchAjaxUpdate(".magicalcoder-table-layswitch",function () {_this._refreshTableFromCurrentPage()});
            magicalcoderlaytools.bindDate(".magicalcoder-table-laydate",function () {_this._refreshTableFromCurrentPage()});
            magicalcoderlaytools.bindInputText('.magicalcoder-table-text',function () {_this._refreshTableFromCurrentPage()});
            magicalcoderlaytools.bindRadio('.magicalcoder-table-radio',function () {_this._refreshTableFromCurrentPage()});
        },
        tableOperateEvent : function (tableEvent) {
            var _this = this;
            var tableDom = $(this.config.layTable.elem);
            var layFilter = tableDom.attr("lay-filter");
            //列表操作
            table.on('tool('+layFilter+')', function(obj){
                var primaryFields = tableDom.attr("data-primary-fields");//一般是表主键id
                var layEvent = obj.event,
                    data = obj.data;
                var identify = data[primaryFields]
                //回调事件
                if(tableEvent){
                    var func = tableEvent.tool
                    if(func!=null){
                        if(!func(obj,identify)){
                            return;
                        }
                    }
                }

                if(layEvent === 'edit'){ //编辑
                    _this.editOneTr(identify)
                } else if(layEvent === 'del'){ //删除
                    _this.deleteOneTr(identify)
                } else if(layEvent === 'children'){ //子表
                    _this.childrenOneTr(identify)
                }
            });
            //列表排序
            table.on('sort('+layFilter+')', function(obj){
                var safeOrderBy = ""
                if(obj.type && obj.type!=null && obj.type!=''){
                    safeOrderBy = _this._coverToSqlField(obj.field) + " " +obj.type;
                }
                //回调事件
                if(tableEvent){
                    var func = tableEvent.sort
                    if(func!=null){
                        if(!func(obj,safeOrderBy)){
                            return;
                        }
                    }
                }
                _this._refreshTableFromCurrentPage(safeOrderBy);
            });
        },
        //查看子表
        childrenOneTr : function (identify) {
            var tableName = this.config.tableName
            var title = '更多信息'
            if(!identify){
                identify = ''
            }
            var childrenPage = this.config.childrenPage
            if(!childrenPage || childrenPage==null ||  childrenPage.length<=0){
                layer.msg("未找到任何子表信息")
                return;
            }
            var content = mc_children.buildTabTemplate(tableName,identify,childrenPage);
            // content = "<div>123</div>"
            var index = layui.layer.open({
                title : title,
                type : 1,
                content : content,
                success : function(layero, index){
                    setTimeout(function(){
                        layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500)
                }
            })
            mc_children.tabClickEvent();
            layui.layer.full(index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
            $(window).on("resize",function(){
                layui.layer.full(index);
            })
        },
        //编辑某一行
        editOneTr : function (identify) {
            this._addNews(identify);
        },
        //删除一行
        deleteOneTr : function (identify) {
            var _this = this;
            var tableNameRest = this.config.tableNameRest;
            layer.confirm('确定删除此行记录？',{icon:3, title:'提示信息'},function(index){
                $.post('admin/'+tableNameRest+'/delete/'+identify,{},function (data) {
                    if(!data.flag){
                        layer.msg("删除失败:"+data.desc);
                    }else {
                        _this._refreshTableFromCurrentPage();
                        layer.close(index);
                    }
                })
            });
        },
        //批量删除
        batchDeleteTableData : function () {
            var tableNameRest = this.config.tableNameRest;
            var _this = this;
            var laytableElem = this.config.layTable.elem
            var laytableId = this.config.layTable.id
            var primaryFields = $(laytableElem).attr("data-primary-fields")
            var checkStatus = table.checkStatus(laytableId),
                data = checkStatus.data,
                ids = [];
            if(data.length > 0) {
                for (var i in data) {
                    ids.push(data[i][primaryFields]);
                }
                layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                    $.post('admin/'+tableNameRest+'/batch_delete',{ids:ids},function (data) {
                        if(!data.flag){
                            layer.msg("删除失败:"+data.desc);
                        }else {
                            _this._refreshTableFromCurrentPage();
                            layer.close(index);
                        }
                    })
                })
            }else{
                layer.msg("请选择需要删除的记录");
            }
        },

        _coverToSqlField : function(field){//java变量转sql字段 为了构造order by
            var sortMap = this.config.sortMap;
            if(sortMap){
                var sqlField = sortMap[field]
                if(sqlField && sqlField!='' && sqlField!=null){
                    return sqlField;
                }
            }
            //最后再尝试反序列化成数据库字段
            var arr = field.split("")
            var result = []
            for(var i=0;i<arr.length;i++){
                var item = arr[i];
                if(item >= 'A' && item<='Z'){
                    result.push("_");
                    result.push(item.toLowerCase());
                }else {
                    result.push(item)
                }
            }
            return result.join("");
        },
        _refreshTableFromCurrentPage : function (safeOrderBy) {//当前页 重新加载
            var whereData = this._buildQueryFormParams(safeOrderBy)
            this._startReloadTableFromStartPage(null,whereData)
        },
        _refreshTableFromPageOne : function () {//从第一页 重新加载 搜索场景使用 清空各种参数
            var whereData = this._buildQueryFormParams("")
            this._startReloadTableFromStartPage(1,whereData)
        },
        _buildQueryFormParams : function (safeOrderBy) {
            var _this = this;
            //自动封装查询区域数据
            var whereData = {}
            var queryForm = $(".security_list_query_form")
            if(queryForm.length<=0){//兼容以前老结构
                queryForm = $(".queryForm");
            }
            //因为layui 的查询有记忆功能，会缓存老的查询条件，这里把重置的条件给清空 赋值null
            var radioMemory = {}
            queryForm.find(".layui-input-inline").find(".layui-input").each(function (index,item) {
                var type = $(item).attr("type")
                var name = $(item).attr("name")
                if(name){
                    switch (type){
                        case "radio" :
                            if(!radioMemory[name]){
                                radioMemory[name] = false;
                            }
                            if($(item).is(":checked")){
                                _this._autoSetWhereData(whereData,name,item);
                                radioMemory[name] = true;
                            }
                            break;
                        default :
                            _this._autoSetWhereData(whereData,name,item);
                            break;
                    }
                }
            })
            //
            for(var radioName in radioMemory){
                if(!radioMemory[radioName]){//肯定被layui缓存的查询字段
                    whereData[radioName] = null
                }
            }

            safeOrderBy = safeOrderBy || "";//排序
            whereData.safeOrderBy = safeOrderBy
            return whereData;
        },
        _startReloadTableFromStartPage : function (startPage,whereData) {
            var reloadTableId = this.config.layTable.id
            if(startPage==null || !startPage) {//当前页 聪明的记住多少页
                table.reload(reloadTableId,{
                    where: whereData
                })
            }else {
                table.reload(reloadTableId,{
                    page: {
                        curr: startPage //重新从第 几 页开始
                    },
                    where: whereData
                })
            }
        },
        _autoSetWhereData : function (whereData,name,item) {
            var val = $.trim($(item).val())
            whereData[name] = val
        },
        _addNews : function (identify) {
            var tableName = this.config.tableName
            var title = identify ? '编辑':'添加'
            if(!identify){
                identify = ''
            }
            var index = layui.layer.open({
                title : title,
                type : 2,
                content : "admin/page/"+tableName+"/edit?identify="+identify+mc_children.buildForeignParam(),
                success : function(layero, index){
                    setTimeout(function(){
                        layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500)
                }
            })
            layui.layer.full(index);
            //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
            $(window).on("resize",function(){
                layui.layer.full(index);
            })
        },
        getMagicalcoderTools : function () {
            return magicalcoderlaytools;
        },
        init : function (config) {
            this.config = config
            return this;
        }
    }

    exports('magicalcoderlaytable',function(config){
        magicalcoderlaytools = layui.magicalcoderlaytools(config);
        return obj.init(config);
    });
})
