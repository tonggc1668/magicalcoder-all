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
        tableNameRest:"sys_role_module_permission_rest",
        tableName:"sys_role_module_permission",
        moduleName:"sys_role_module_permission",//sys_module的moduleName
        layTable : {
            //表格内容
            elem : '#newsList',
            id : "newsListTable"
            },
            //排序跟数据库实际字段名的映射
        sortMap:{
            id:'id',roleId:'role_id',moduleId:'module_id',permissionId:'permission_id'
        }
    });
    //绑定所有查询区域控件
    magicalcoderlist.bindQueryArea();
    //表格
    var cols = [[
        {type: "checkbox", fixed:"left", width:50},
                {field: 'id', title: '主键', minWidth:100, align:"center",sort:true},
                {field: 'roleId', title: '角色', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.roleId || d.roleId==null) ? '' : d.roleId
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_roleId" lay-ignore="true"  name="roleId" data-identify="'+d.id+'" data-value="" data-url="admin/sys_role_rest/search" data-id="id" data-text-fields="roleName" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入角色">'+option+'</select>'
                    },sort:true
                },
                {field: 'moduleId', title: '模块', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.moduleId || d.moduleId==null) ? '' : d.moduleId
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_moduleId" lay-ignore="true"  name="moduleId" data-identify="'+d.id+'" data-value="" data-url="admin/sys_module_rest/search" data-id="id" data-text-fields="moduleTitle" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入模块">'+option+'</select>'
                    },sort:true
                },
                {field: 'permissionId', title: '权限', align:'center', minWidth:250, templet:function (d) {
                    var value = (!d.permissionId || d.permissionId==null) ? '' : d.permissionId
                    var option = '<option selected="selected" value="'+value+'">'+value+'</option>'
                    return '<select class="magicalcoder-table-foreign-select2 layui-input security_list_table_form_permissionId" lay-ignore="true"  name="permissionId" data-identify="'+d.id+'" data-value="" data-url="admin/sys_permission_rest/search" data-id="id" data-text-fields="permissionName" lay-verify="magicalCoderVerify" magicalcoder-verify=""  placeholder="请输入权限">'+option+'</select>'
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
