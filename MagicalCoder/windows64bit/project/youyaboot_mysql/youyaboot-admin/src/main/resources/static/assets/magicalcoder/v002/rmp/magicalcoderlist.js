/**
 * 为了操作简单，代码整洁
 * 封装了大量youyaboot列表页的操作逻辑，很可能您改了界面的任何一个地方 都会造成功能失常
 * 对于一些接口取名 接口入参规范 必须遵从代码生成规则
 * 如果您需要自定义大量操作，建议您拷贝一份作为新的文件，不要依赖此默认的处理方式
 * v 0.0.1
 */
layui.config({
    base : "assets/"
}).extend({
    "magicalcoderlaytable" : "magicalcoder/v002/rmp/magicalcoderlaytable"
})
layui.define(['form','layer','laydate','table','laytpl','magicalcoderlaytable','mc_children'],function(exports){
    var $ = layui.jquery,
        form = layui.form,
        mc_children = layui.mc_children,
        magicalcoderlaytable = null,
        magicalcoderlaytools = null ;

    var obj = {
        escapeHTML: function(a){//layui/table.js已经被改动 无法顺利升级
            if(!a || a==null){
                return '';
            }
            a = "" + a;
            return a.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&apos;");
        },
        bindQueryArea : function () {
            mc_children.disabledFromQueryParentArea();
            magicalcoderlaytools.bindDate(".magicalcoder-laydate");
            //绑定外键查询
            magicalcoderlaytools.bindForeignSelect2(".magicalcoder-foreign-select2",{width:"resolve",allowClear:true});
        },
        /**
         *
         * @param cols laytable的入参
         * @param tableEvent 被封装的一些事件 tool sort
         */
        bindTableArea : function (cols,tableEvent) {
            magicalcoderlaytable.initTable(cols,tableEvent)
        },
        bindAreaEvents : function () {
            //搜索
            $(".search_btn").click(function(){

                magicalcoderlaytable._refreshTableFromPageOne();
            });
            //重置
            $("button[type='reset']").click(function(){
                $(".magicalcoder-foreign-select2").val(null).trigger('change');
                /*if($(".security_list_query_form").length>0){
                    $(".security_list_query_form").find("input[type='radio']").each(function (idx, item) {
                        $(item).prop("checked",false);
                        form.render('radio');
                    })
                }*/
                return true;
            });
            $(".addNews_btn").click(function(){
                magicalcoderlaytable._addNews();
            })
            //批量删除
            $(".delAll_btn").click(function(){
                magicalcoderlaytable.batchDeleteTableData();
            })
        },
        init : function (config) {
            this.config = config
            return this;
        }
    }

    exports('magicalcoderlist',function(config){
        magicalcoderlaytable = layui.magicalcoderlaytable(config);
        magicalcoderlaytools = magicalcoderlaytable.getMagicalcoderTools();
        return obj.init(config)
    });
})
