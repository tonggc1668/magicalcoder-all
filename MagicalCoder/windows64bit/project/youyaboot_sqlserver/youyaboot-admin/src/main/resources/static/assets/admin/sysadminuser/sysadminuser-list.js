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
        tableNameRest:"sys_admin_user_rest",
        tableName:"sys_admin_user",
        moduleName:"sys_admin_user",//sys_module的moduleName
        childrenPage:[
            {
                tabTitle:"系统日志",
                url:"admin/page/sys_log_admin_operate/list",
                mcForeignName:"adminUserId"
            }        ],
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            username:'username',realName:'real_name',createTime:'create_time',updateTime:'update_time',roleId:'role_id',enabled:'enabled'
        }
    });
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},

                {field: 'username', title: '用户名', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.username) +'" class="magicalcoder-table-text layui-input security_list_table_form_username" name="username" data-identify="'+d.id+'" lay-verify="magicalCoderVerify|mc_required" magicalcoder-verify=""  placeholder="请输入用户名"/>'
                    }
                    , sort:true
                },

                {field: 'realName', title: '真名', minWidth:200,templet:function (d) {
                        return '<input type="text" value="'+ magicalcoderlist.escapeHTML(d.realName) +'" class="magicalcoder-table-text layui-input security_list_table_form_realName" name="realName" data-identify="'+d.id+'" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入真名"/>'
                    }
                    , sort:true
                },

                {field: 'enabled', title: '是否可用',minWidth:150, align:'center', templet:function(d){
                    var checked = (d.enabled+''=='true' || d.enabled+'' == '1') ? 'checked' : '';
                    return '<input type="checkbox" class="magicalcoder-table-layswitch security_list_table_form_enabled" data-identify="'+d.id+'" name="enabled"  lay-filter="enabledFilter" lay-skin="switch" lay-text="是|否"  '+checked+' lay-verify="magicalCoderVerify" magicalcoder-verify="|minLength=0" />'
                    }
                    ,sort:true
                },
                {field: 'createTime', title: '创建时间', align:'center', minWidth:250, templet :"#createTimeTemplate",sort:true},
                {field: 'updateTime', title: '更新时间', align:'center', minWidth:250, templet :"#updateTimeTemplate",sort:true},
                {field: 'roleId', title: '角色', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.roleId || d.roleId==null) ? '' : d.roleId
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_roleId" lay-ignore="true"  name="roleId" data-identify="'+d.id+'" data-value="" data-url="admin/sys_role_rest/search" data-id="id" data-text-fields="roleName" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入角色">'+option+'</select>'
                    },sort:true
                },
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
