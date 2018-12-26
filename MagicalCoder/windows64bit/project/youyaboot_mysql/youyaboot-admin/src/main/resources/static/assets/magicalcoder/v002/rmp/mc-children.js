/**
 *  定义子表一些处理
 *  v 0.0.1
 */

layui.define(['jquery','laytpl','element'],function(exports){
    var $ = layui.jquery,
        laytpl = layui.laytpl;
    //从url构建外键 允许不传
    var mcForeignName = magicalcoderutil.getParameter('mcForeignName')//外键名称
    var mcForeignValue = magicalcoderutil.getParameter('mcForeignValue')//外键值

    var element = layui.element;

    var obj = {
        buildForeignParam : function () {
            if(mcForeignName!=''){
                return "&mcForeignName="+mcForeignName+"&mcForeignValue="+mcForeignValue;
            }
            return "";
        },
        _disableForeignDom : function (name,foreignSelect2Class,dataValueSet) {
            var foreignDoms = $("[name='"+name+"']")
            if(foreignDoms.length<=0){
                return;
            }
            foreignDoms.each(function (idx, item) {
                var foreignDom = $(item)
                if(foreignDom && foreignDom.hasClass(foreignSelect2Class)){//select2
                    var dataValue = foreignDom.attr("data-value") || '';
                    if(dataValue == ''){//dataValue在列表页 table内是没有值的
                        var option = '<option selected="selected" value="'+mcForeignValue+'">'+mcForeignValue+'</option>'
                        foreignDom.append(option)
                        foreignDom.val(mcForeignValue);
                        if(dataValueSet){
                            foreignDom.attr("data-value",mcForeignValue);
                        }
                    }
                }else {
                    foreignDom.val(mcForeignValue);
                }
                foreignDom.attr("disabled","disabled")
            })

        },
        //查询区域
        disabledFromQueryParentArea : function () {
            if(mcForeignName && mcForeignName!=''){
                $("button[type='reset']").hide();//重置按钮禁用
                //查询条件
                this._disableForeignDom(mcForeignName+"First","magicalcoder-foreign-select2",true)
                this._disableForeignDom(mcForeignName+"Second","magicalcoder-foreign-select2",true)
            }
        },
        //表格区域
        disabledTableParentArea : function () {
            if(mcForeignName && mcForeignName!=''){
                //查询条件
                this._disableForeignDom(mcForeignName,"magicalcoder-table-foreign-select2",false)
            }
        },
        //表单区域
        disabledFormParentArea : function () {
            if(mcForeignName && mcForeignName!=''){
                //查询条件
                $("button[type='reset']").hide();//重置按钮禁用
                this._disableForeignDom(mcForeignName,"magicalcoder-foreign-select2",true)
            }
        },
        //构造子表面板内容
        buildTabTemplate : function (tableName,identify,childrenPage) {
            var html = laytpl('<div class="layui-tab mcChildrenPage" lay-filter="tabClick">' +
                '                  <ul class="layui-tab-title">' +
                '{{# layui.each(d.childrenPage,function(index,item){  }}'+
                '                       <li {{# if(index==0){ }}class="layui-this" {{# } }}>{{ item.tabTitle }}</li>' +
                '{{#  }); }}'+
                '               </ul>' +
                '        <div class="layui-tab-content">' +
                '{{# layui.each(d.childrenPage,function(index,item){  }}'+
                '   {{# if(index==0){ }}'+
                '            <div class="layui-tab-item layui-show">' +
                '                <iframe class="childrenIframe" src="{{ item.url }}?mcForeignName={{ item.mcForeignName }}&mcForeignValue={{ d.identify }} "></iframe>' +
                '            </div>' +
                '   {{# }else{ }}'+
                '            <div class="layui-tab-item">' +
                '                <iframe class="childrenIframe" futureSrc="{{ item.url }}?mcForeignName={{ item.mcForeignName }}&mcForeignValue={{ d.identify }} "></iframe>' +
                '            </div>' +
                '   {{# } }}'+
                '{{#  }); }}'+
                '    </div></div>').render({tableName:tableName,identify:identify,childrenPage:childrenPage});
            return html;
        },
        tabClickEvent : function () {
            var clicked = {}
            element.on('tab(tabClick)',function (data) {
                if(data.index!=0){
                    var index = data.index +""
                    if(!clicked[index]){
                        clicked[index]=true
                        var frame = $(".mcChildrenPage").find('.childrenIframe').eq(index)
                        // console.log(frame.attr("futureSrc"))
                        var src = frame.attr("src")
                        if(!src || src==''){
                            frame.attr("src",frame.attr("futureSrc"))
                        }
                    }
                }
            })
        }
    }

    exports('mc_children',obj);
})
