/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

layui.config({
    base : "assets/"
}).extend({
    "magicalcoderlist" : "magicalcoder/v001/rmp/magicalcoderlist"
})
layui.use(['magicalcoderlist'],function(){
    var  magicalcoderlist = layui.magicalcoderlist(
    {
        tableNameRest:"sys_log_admin_operate_rest",
        tableName:"sys_log_admin_operate",
        moduleName:"sys_log_admin_operate",//sys_module的moduleName
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            userName:'user_name',tableName:'table_name',operateType:'operate_type',url:'url',createTime:'create_time'
        }
    });
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},

                {field: 'userName', title: '管理员名称', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.userName) +'" class="magicalcoder-table-text layui-input security_list_table_form_userName" name="userName" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify="" disabled="true" placeholder="请输入管理员名称"/>'
                    }
                    , sort:true
                },

                {field: 'tableName', title: '表名', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.tableName) +'" class="magicalcoder-table-text layui-input security_list_table_form_tableName" name="tableName" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify="" disabled="true" placeholder="请输入表名"/>'
                    }
                    , sort:true
                },

                {field: 'operateType', title: '操作类型', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.operateType) +'" class="magicalcoder-table-text layui-input security_list_table_form_operateType" name="operateType" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify="|minLength=0" disabled="true" placeholder="请输入操作类型"/>'
                    }
                    , sort:true
                },

                {field: 'url', title: '链接', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.url) +'" class="magicalcoder-table-text layui-input security_list_table_form_url" name="url" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify="" disabled="true" placeholder="请输入链接"/>'
                    }
                    , sort:true
                },
                {field: 'createTime', title: '创建时间', align:'center', minWidth:250, templet :"#createTimeTemplate",sort:true},
        {title: '操作', minWidth:250, templet:'#newsListOperateTemplate',fixed:"right",align:"center"}
    ]];
    //表格中的一些事件
    var tableEvent = {
        "tool":function (obj,roleId) {//操作部分的按钮
            return true;//不阻止事件继续执行封装的事件
        },
        "sort":function (obj,safeOrderBy) {//排序
            return true;//不阻止事件继续执行封装的事件
        }
    }
    magicalcoderlist.bindTableArea(cols,tableEvent)
    magicalcoderlist.bindAreaEvents();
})
